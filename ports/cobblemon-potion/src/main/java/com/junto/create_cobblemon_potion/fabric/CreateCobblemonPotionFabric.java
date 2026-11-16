package com.junto.create_cobblemon_potion.fabric;

import com.junto.create_cobblemon_potion.core.registry.BlockRegistry;
import com.junto.create_cobblemon_potion.core.registry.CreativeTabRegistry;
import com.junto.create_cobblemon_potion.core.registry.FluidRegistry;
import com.junto.create_cobblemon_potion.core.registry.FluidTypeRegistry;
import com.junto.create_cobblemon_potion.core.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;

/** Fabric entrypoint (unofficial port). Registration order: fluids, blocks, items, tabs. */
public class CreateCobblemonPotionFabric implements ModInitializer {
   public static final String MODID = "create_cobblemon_potion";

   @Override
   public void onInitialize() {
      FluidTypeRegistry.register(null);
      FluidRegistry.register(null);
      BlockRegistry.register(null);
      ItemRegistry.register(null);
      CreativeTabRegistry.register(null);
   }
}
