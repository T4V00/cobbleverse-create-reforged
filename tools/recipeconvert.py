"""Convert Create 6 (NeoForge) recipe JSON to the format Create Reforged (Fabric) reads.
- snake_case keys -> camelCase (processing_time, heat_requirement, transitional_item, keep_held_item)
- fluid amounts mB -> droplets (x81), NeoForge fluid ingredient types -> Fabric form
- item results {"item": {"id", "count"}} -> {"id", "count"}; fluid results {"id", "amount"} -> {"fluid", "amount"}
- "neoforge:conditions" -> "fabric:load_conditions"
Usage: python recipeconvert.py <data_dir> [<data_dir>...]
"""
import json, os, sys

KEYS = {'processing_time': 'processingTime', 'heat_requirement': 'heatRequirement',
        'transitional_item': 'transitionalItem', 'keep_held_item': 'keepHeldItem'}
DROPLETS = 81


def is_fluid_ingredient(o):
    return isinstance(o, dict) and (o.get('type') in ('fluid_stack', 'neoforge:single', 'neoforge:tag', 'neoforge:fluid', 'neoforge:simple')
                                    or ('amount' in o and ('fluid' in o or 'fluidTag' in o)))


def conv_fluid_ingredient(o):
    amount = int(o.get('amount', 1000)) * DROPLETS
    if 'fluid' in o:
        return {'amount': amount, 'fluid': o['fluid']}
    if 'fluidTag' in o:
        return {'amount': amount, 'fluidTag': o['fluidTag']}
    if 'tag' in o:
        return {'amount': amount, 'fluidTag': o['tag']}
    raise ValueError('unknown fluid ingredient %r' % o)


def conv_result(o, fluid_ids):
    if not isinstance(o, dict):
        return o
    if isinstance(o.get('item'), dict):
        inner = o.pop('item')
        o = {**inner, **o}
    if 'amount' in o and 'count' not in o and ('id' in o or 'fluid' in o):
        fid = o.pop('id', None) or o.pop('fluid')
        o = {'amount': int(o.pop('amount')) * DROPLETS, 'fluid': fid, **o}
    return o


def conv(node, recipe_type=None):
    if isinstance(node, list):
        return [conv(x, recipe_type) for x in node]
    if not isinstance(node, dict):
        return node
    out = {}
    rtype = node.get('type', recipe_type)
    for k, v in node.items():
        k2 = KEYS.get(k, k)
        if k == 'neoforge:conditions':
            mods = [c.get('modid') for c in v if c.get('type') == 'neoforge:mod_loaded']
            out['fabric:load_conditions'] = [{'condition': 'fabric:all_mods_loaded', 'values': mods}]
            continue
        if k == 'ingredients' and isinstance(v, list):
            out[k2] = [conv_fluid_ingredient(i) if is_fluid_ingredient(i) else conv(i, rtype) for i in v]
        elif k == 'results' and isinstance(v, list):
            out[k2] = [conv_result(conv(r, rtype), None) for r in v]
        elif k == 'sequence' and isinstance(v, list):
            out[k2] = [conv(step) for step in v]
        else:
            out[k2] = conv(v, rtype)
    return out


changed = 0
for d in sys.argv[1:]:
    for root, _, files in os.walk(d):
        for f in files:
            if not f.endswith('.json'):
                continue
            p = os.path.join(root, f)
            try:
                j = json.load(open(p, encoding='utf-8'))
            except Exception as e:
                print('SKIP (bad json)', p, e)
                continue
            if not isinstance(j, dict):
                continue
            if not (str(j.get('type', '')).startswith('create:') or 'neoforge:conditions' in j):
                continue
            n = conv(j)
            if n != j:
                json.dump(n, open(p, 'w', encoding='utf-8'), indent=2)
                changed += 1
print('converted files:', changed)
