# Cobbleverse x Create Reforged (FABRIC)

Play Create and Create Aeronautics inside the COBBLEVERSE modpack, on Fabric 1.21.1, without crashes.

## Notices

**Made with AI.** This mod was built with heavy help from an AI assistant (Claude), so it is "vibe-coded". It was tested in a real COBBLEVERSE world, both single player and LAN, but it is a beta. Back up your worlds before using it.

**Not affiliated.** This mod is a bridge between two projects that are not mine:

- The unofficial Fabric ports **Create Reforged** and **Create Aeronautics Reforged**, by sanufsoii.
- The **COBBLEVERSE** modpack, by LUMYVERSE.

I am not affiliated with either of them, nor with Create, Create Aeronautics or Cobblemon. I am not trying to take credit from anyone or to make money. The only goal is to make Create and Cobblemon work well together. All credit for Create, Create Aeronautics, the Fabric ports and the modpack belongs to their authors. Please do not report problems with this mod to them; use this project's issue tracker instead.

## Installation

1. Install the [COBBLEVERSE](https://modrinth.com/modpack/cobbleverse) modpack (Fabric, Minecraft 1.21.1).
2. Add these mods to the `mods` folder:
   - [Create Reforged [Unofficial]](https://www.curseforge.com/minecraft/mc-mods/create-reforged-unofficial)
   - [Create Aeronautics [Unofficial]](https://www.curseforge.com/minecraft/mc-mods/create-aeronautics-unofficial) and its dependency Sable
   - This mod
3. **Disable ScalableLux.** COBBLEVERSE ships `ScalableLux-0.1.0.1+fabric.d0d58ab-all.jar`, and Sable (required by Create Aeronautics) refuses to load with it. In the CurseForge app, open the modpack's mod list and turn ScalableLux off, or rename the file in `mods` so it ends in `.disabled`. If you skip this, the game stops at launch with "Mod 'Sable' is incompatible with any version of mod 'ScalableLux'".
4. Put the official Create jar and the official Create Aeronautics jar in the `resourcepacks` folder. The two ports read their textures from those jars; see their pages for details.
5. Launch the game.

## What it fixes

| Problem without this mod | Cause | What this mod does |
|---|---|---|
| The game freezes on the loading screen and all resource packs get disabled | Porting Lib (inside Sophisticated Backpacks and Storage) and porting_lib_ufo (inside Create Reforged) add the same methods to Minecraft's `Transformation` class, and Java refuses to run them | Provides one shared implementation of those methods, so both libraries work |
| You are kicked from your world a few seconds after joining ("ticking exception on your player") | porting_lib_ufo asks every entity for its size while the entity is still being created; Cobblemon's Pokemon are not ready at that point and crash on every spawn | Gives Pokemon a safe default size until they finish loading |
| Create Aeronautics Reforged crashes at startup (`ClassCastException: ArrayList cannot be cast to AnnotationNode`) | Some of its mixin annotations are stored in a format that MixinExtras cannot read | Repairs those annotations in memory before MixinExtras reads them. The Aeronautics jar is not modified |

## Included content

Fabric ports of NeoForge addons, adapted to Create Reforged. Each one keeps its original license and credit:

| Mod | What it adds | Original author | License |
|---|---|---|---|
| [Create: Threaded Trains](https://modrinth.com/mod/create-threaded-trains) | Runs train logic on its own thread for better performance | MrJulsen | GPL-3.0 |
| [Create Cobblestone](https://modrinth.com/mod/create-cobblestone) | Mechanical Generator that makes cobblestone, stone, basalt and more from rotation | MrStickyPiston | CC0-1.0 |
| [Create: Cobblemon Balls Overhaul](https://modrinth.com/mod/create-cobblemon-balls-overhaul) | Create production chains for Poke Balls: lids, mechanisms, tumblestone coatings | xKcinnay | MIT |
| [Create Cobblemon Potion](https://modrinth.com/mod/create-cobblemon-potion) | Cobblemon medicines as Create fluids, with healing pools | Junto (buggzd) | MIT |
| Aviator's Goggles in an Accessories slot | Wear the Aeronautics goggles in the head accessory slot; they count as Create goggles. New code, idea from [Create Aeronautics x Curios API Compat](https://modrinth.com/mod/create-aeronautics-x-curios-api-compat) by titammods | - | MIT |

The NeoForge Create recipes of these mods were converted to the format Create Reforged reads (field names, and fluid amounts from mB to droplets).

## Also compatible with

These mods cannot be bundled because their licenses do not allow redistribution, but they can be installed alongside:

- [Cobblemore Library](https://modrinth.com/mod/cobblemore-library) (Fabric). Requires [Pommel](https://modrinth.com/mod/pommel-held-item-models).
- [Cobblemon: Create Industries](https://modrinth.com/mod/cobblemon-create-industries). It has no Fabric 1.21.1 version yet; if its author releases one, it should work with this setup.

## Not included

| Mod | Reason |
|---|---|
| Create Better FPS | It optimizes Create 6's renderer, which Create Reforged does not use |
| Create Dynamic Light | It needs Sodium Dynamic Lights, which conflicts with the Sodium version in COBBLEVERSE |

## Source code and license

All source code is on [GitHub](https://github.com/T4V00/cobbleverse-create-reforged). The fixes are under the MIT license; each port keeps the license of the mod it came from.

---

# Español

Juega Create y Create Aeronautics dentro del modpack COBBLEVERSE, en Fabric 1.21.1, sin crasheos.

## Avisos

**Hecho con IA.** Este mod se hizo con mucha ayuda de un asistente de IA (Claude). Se probó en un mundo real de COBBLEVERSE, en un jugador y en LAN, pero es una beta. Haz copia de tus mundos antes de usarlo.

**Sin afiliación.** Este mod une dos proyectos que no son míos: los ports no oficiales a Fabric Create Reforged y Create Aeronautics Reforged (de sanufsoii) y el modpack COBBLEVERSE (de LUMYVERSE). No tengo afiliación con ninguno de ellos, ni con Create, Create Aeronautics o Cobblemon. No busco quitarle mérito a nadie ni ganar dinero, solo facilitar que Create y Cobblemon funcionen juntos. Todo el crédito es de sus autores. No reportes problemas de este mod a ellos; usa el apartado de issues de este proyecto.

## Instalación

1. Instala el modpack COBBLEVERSE.
2. Añade a `mods`: Create Reforged [Unofficial], Create Aeronautics [Unofficial] (con su dependencia Sable) y este mod.
3. **Desactiva ScalableLux.** COBBLEVERSE trae `ScalableLux-0.1.0.1+fabric.d0d58ab-all.jar` y Sable (necesario para Create Aeronautics) no carga con él. Desactívalo en la lista de mods del modpack en la app de CurseForge, o renombra el archivo en `mods` para que termine en `.disabled`. Si no lo haces, el juego se detiene al abrir con el mensaje "Mod 'Sable' is incompatible with any version of mod 'ScalableLux'".
4. Pon el jar oficial de Create y el de Create Aeronautics en `resourcepacks`.
5. Abre el juego.

## Qué arregla

- El congelamiento en la pantalla de carga causado por dos copias de Porting Lib.
- La expulsión del mundo cuando aparecen Pokémon.
- El crash de Create Aeronautics Reforged al arrancar, sin modificar su jar.

## Qué incluye

Ports a Fabric, con crédito a sus autores: Create Threaded Trains, Create Cobblestone, Create: Cobblemon Balls Overhaul, Create Cobblemon Potion y las Aviator's Goggles en un slot de Accessories.

## También compatible con

Cobblemore Library (requiere Pommel). Cobblemon: Create Industries todavía no tiene versión Fabric.
