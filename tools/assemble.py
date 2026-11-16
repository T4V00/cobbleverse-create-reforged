"""Build the final single jar: core mod + nested (jar-in-jar) ports and dependencies.
Usage: python assemble.py <core.jar> <out.jar> <nested1.jar> [<nested2.jar> ...]
"""
import json, os, sys, zipfile

core, out, nested = sys.argv[1], sys.argv[2], sys.argv[3:]
with zipfile.ZipFile(core) as zin, zipfile.ZipFile(out + '.tmp', 'w', zipfile.ZIP_DEFLATED) as zout:
    meta = None
    for item in zin.infolist():
        data = zin.read(item.filename)
        if item.filename == 'fabric.mod.json':
            meta = json.loads(data)
            continue
        zout.writestr(item, data)
    meta['jars'] = []
    for n in nested:
        name = 'META-INF/jars/' + os.path.basename(n)
        with zipfile.ZipFile(n) as z:
            if 'fabric.mod.json' not in z.namelist():
                raise SystemExit('not a Fabric mod: ' + n)
        # stored, not deflated: Fabric reads nested jars directly
        zout.write(n, name, compress_type=zipfile.ZIP_STORED)
        meta['jars'].append({'file': name})
    zout.writestr('fabric.mod.json', json.dumps(meta, indent=2, ensure_ascii=False))
if os.path.exists(out):
    os.remove(out)
os.rename(out + '.tmp', out)
print('built', out, 'with', len(nested), 'nested jars')
