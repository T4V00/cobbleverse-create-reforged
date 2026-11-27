# Cobbleverse x Create Reforged + Industries Beta

A Fabric 1.21.1 mod that makes [Create Reforged](https://www.curseforge.com/minecraft/mc-mods/create-reforged-unofficial) and [Create Aeronautics Reforged](https://www.curseforge.com/minecraft/mc-mods/create-aeronautics-unofficial) (unofficial Fabric ports of Create
and Create Aeronautics by sanufsoii) work inside the **COBBLEVERSE** Cobblemon modpack, and bundles Fabric ports of a few Create and
Cobblemon addons.

> **Made with AI.** Built with heavy help from an AI assistant (Claude). Tested in a real COBBLEVERSE world, but it is
> a beta: back up your worlds.
>
> **Not affiliated** with Create, Create Aeronautics, Create Reforged, Create Aeronautics Reforged, Cobblemon or
> COBBLEVERSE. Not trying to take credit or make money: the only goal is to make Create and Cobblemon work together.

The full player-facing description (requirements, fixes, credits) is in **[CURSEFORGE.html](CURSEFORGE.html)**, the exact HTML published on CurseForge.

**Tested and working with COBBLEVERSE 1.8.0** (Cobblemon 1.8.4, Fabric Loader 0.19.5).

**Important:** disable `ScalableLux` (shipped with COBBLEVERSE) before launching. Sable, required by Create Aeronautics
Reforged, is incompatible with it and the game will refuse to start.

## What's in this repository

```
core/                     The mod itself: compatibility fixes + jar-in-jar of the ports
  ...mixin/TransformationFixMixin.java   Porting Lib vs porting_lib_ufo "Conflicting default methods" fix
  ...mixin/PokemonEntityFixMixin.java    Pokemon spawn NPE (player kicked by Neruina) fix
  ...core/RedirectAtFixExtension.java    Lets older Fabric Loaders (MixinExtras 0.5.0 to 0.5.4) read mixins built with the newer annotation format
  ...rei/                                REI plugin: 13 Create machine categories + sequenced assembly (Reforged only ships JEI)
ports/                    Fabric ports, each keeps its original license (THIRD_PARTY_NOTICES.md)
tools/                    Scripts used for the ports (NeoForge->Fabric source helper, Create recipe converter, jar tools)
```

## Building

1. Install JDK 21.
2. Put the dependency jars in `libs/` (see [libs/README.md](libs/README.md)).
3. Run:

```bash
./gradlew :core:build
```

The finished mod is `core/build/libs/cobbleverse-create-reforged-1.1.3-beta.jar` (the ports are nested inside it).

## Tools

- `tools/recipeconvert.py` converts Create 6 NeoForge recipe JSON to the format Create Reforged reads
  (`processing_time` → `processingTime`, `transitional_item` → `transitionalItem`, fluid amounts mB → droplets ×81, etc.).
- `tools/neoconvert.py` + `tools/neocompat/` copy decompiled NeoForge sources into a Fabric project and swap
  `DeferredRegister`, `BaseFlowingFluid` and `FluidType` for small Fabric stand-ins.
- `tools/FixAt.java` is the offline version of the Aeronautics annotation fix (patches a jar on disk). Not needed when
  this mod is installed.
- `tools/assemble.py` builds a jar-in-jar bundle by hand.

## License

Code in `core/`, `tools/` and `ports/goggles-accessories/`: MIT. Other ports: their original licenses
(see [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md)).
