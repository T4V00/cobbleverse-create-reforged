package net.xkcinnay.createmonballsoverhaul;

import net.fabricmc.api.ModInitializer;
import net.xkcinnay.createmonballsoverhaul.block.ModBlocks;
import net.xkcinnay.createmonballsoverhaul.fluid.ModFluidTypes;
import net.xkcinnay.createmonballsoverhaul.fluid.ModFluids;
import net.xkcinnay.createmonballsoverhaul.item.ModCreativeModeTabs;
import net.xkcinnay.createmonballsoverhaul.item.ModItems;

/** Fabric entrypoint (unofficial port). Registration order: fluids, blocks, items, tabs. */
public class CreateCobblemonBallsOverhaul implements ModInitializer {
   public static final String MOD_ID = "createmonballsoverhaul";

   @Override
   public void onInitialize() {
      ModFluidTypes.register(null);
      ModFluids.register(null);
      ModBlocks.register(null);
      ModItems.register(null);
      ModCreativeModeTabs.register(null);
   }
}
