package com.titammods.aeronautics_curios_compat;

import com.simibubi.create.content.equipment.goggles.GogglesItem;
import io.wispforest.accessories.api.AccessoriesCapability;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/** Fabric port: lets Aeronautics' Aviator's Goggles count as Create goggles when worn in an Accessories slot. */
public class AeronauticsCuriosCompat implements ModInitializer {
   public static final String MODID = "aeronautics_curios_compat";
   public static final ResourceLocation GOGGLES = ResourceLocation.fromNamespaceAndPath("aeronautics", "aviators_goggles");

   @Override
   public void onInitialize() {
      GogglesItem.addIsWearingPredicate(player -> {
         Item goggles = BuiltInRegistries.ITEM.get(GOGGLES);
         if (goggles == Items.AIR) return false;
         AccessoriesCapability cap = AccessoriesCapability.get(player);
         return cap != null && cap.isEquipped(goggles);
      });
   }
}
