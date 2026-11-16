"""Copy decompiled NeoForge sources into a Fabric project, install the neocompat shim and rewrite imports.
Usage: python neoconvert.py <decomp_dir> <project_dir> <base_package> [files_to_skip...]
"""
import os, re, shutil, sys

decomp, proj, base_pkg = sys.argv[1], sys.argv[2], sys.argv[3]
skip = set(sys.argv[4:])
here = os.path.dirname(os.path.abspath(__file__))
compat_pkg = base_pkg + '.neocompat'
java_root = os.path.join(proj, 'src', 'main', 'java')
res_root = os.path.join(proj, 'src', 'main', 'resources')
os.makedirs(java_root, exist_ok=True)
os.makedirs(res_root, exist_ok=True)

# resources
for d in ('assets', 'data'):
    src = os.path.join(decomp, d)
    if os.path.isdir(src):
        shutil.copytree(src, os.path.join(res_root, d), dirs_exist_ok=True)

# shim
shim_dir = os.path.join(java_root, *compat_pkg.split('.'))
os.makedirs(shim_dir, exist_ok=True)
for f in os.listdir(os.path.join(here, 'neocompat')):
    s = open(os.path.join(here, 'neocompat', f), encoding='utf-8').read().replace('__PKG__', compat_pkg)
    open(os.path.join(shim_dir, f), 'w', encoding='utf-8').write(s)

R = [
    (r'import net\.neoforged\.neoforge\.registries\.DeferredRegister\.(Items|Blocks);', r'import %s.DeferredRegister.\1;' % compat_pkg),
    (r'import net\.neoforged\.neoforge\.registries\.(DeferredRegister|DeferredHolder|DeferredItem|DeferredBlock);', r'import %s.\1;' % compat_pkg),
    (r'import net\.neoforged\.neoforge\.registries\.NeoForgeRegistries\.Keys;\n', ''),
    (r'import net\.neoforged\.neoforge\.fluids\.BaseFlowingFluid\.(\w+);', r'import %s.BaseFlowingFluid.\1;' % compat_pkg),
    (r'import net\.neoforged\.neoforge\.fluids\.BaseFlowingFluid;', r'import %s.BaseFlowingFluid;' % compat_pkg),
    (r'import net\.neoforged\.neoforge\.fluids\.FluidType\.Properties;', r'import %s.FluidType.Properties;' % compat_pkg),
    (r'import net\.neoforged\.neoforge\.fluids\.FluidType;', r'import %s.FluidType;' % compat_pkg),
    (r'net\.neoforged\.neoforge\.fluids\.BaseFlowingFluid\.', compat_pkg + '.BaseFlowingFluid.'),
    (r'import net\.neoforged\.neoforge\.common\.SoundActions;\n', ''),
    (r'SoundActions\.\w+', 'null'),
    (r'DeferredRegister\.create\(Keys\.FLUID_TYPES, ', 'DeferredRegister.createUnbacked('),
    (r'import net\.neoforged\.bus\.api\.IEventBus;', 'import java.lang.Object;'),
    (r'\bIEventBus\b', 'Object'),
    (r'CreativeModeTab\.builder\(\)', 'net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup.builder()'),
    (r'\s*\.withTabsBefore\((?:[^()]|\([^()]*\))*\)', ''),
]

for root, _, files in os.walk(decomp):
    for f in files:
        if not f.endswith('.java') or f in skip:
            continue
        src = os.path.join(root, f)
        rel = os.path.relpath(src, decomp)
        s = open(src, encoding='utf-8').read()
        for a, b in R:
            s = re.sub(a, b, s)
        dst = os.path.join(java_root, rel)
        os.makedirs(os.path.dirname(dst), exist_ok=True)
        open(dst, 'w', encoding='utf-8').write(s)
        left = re.findall(r'net\.neoforged[\w.]*', s)
        if left:
            print('REMAINING', rel, sorted(set(left)))
print('done')
