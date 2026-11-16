package com.titammods.aeronautics_curios_compat.client;

import com.titammods.aeronautics_curios_compat.AeronauticsCuriosCompat;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/** Renders the goggles with their normal armor model when worn in an Accessories slot. */
public class AeronauticsCuriosCompatClient implements ClientModInitializer {
   @Override
   public void onInitializeClient() {
      Item goggles = BuiltInRegistries.ITEM.get(AeronauticsCuriosCompat.GOGGLES);
      if (goggles != Items.AIR) {
         AccessoriesRendererRegistry.registerArmorRendering(goggles);
      }
   }
}
