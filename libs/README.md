# libs/

The mods this project compiles against are not on a public Maven, so they are not committed. Put these jars here
(the same files you have in your COBBLEVERSE `mods` folder):

- `create-fabric-6.0.10.2-1.21.1.jar` (Create Reforged)
- `aeronautics-fabric-1.3.2-3-1.21.1.jar` (Create Aeronautics Reforged)
- `sable-fabric-1.21.1-2.0.6.jar`
- `Cobblemon-fabric-1.7.3+1.21.1.jar`
- `accessories-fabric-1.1.0-beta.53+1.21.1.jar`, `accessories_compat_layer-fabric-0.1.12+1.21.1.jar`
- `architectury-13.0.8-fabric.jar`, `fabric-language-kotlin-1.13.13+kotlin.2.4.10.jar`
- `sodium-fabric-0.8.12+mc1.21.1.jar`, `iris-fabric-1.8.14-beta.1+mc1.21.1.jar`
- `RoughlyEnoughItems-16.0.799-fabric.jar`, `cloth-config-15.0.140-fabric.jar` (REI plugin)

Then extract each jar's nested libraries next to it (needed for porting_lib_ufo, Registrate, Flywheel, Ponder...):

```bash
for j in *.jar; do unzip -q -o "$j" 'META-INF/jars/*' -d "nested_${j%.jar}"; done
```
