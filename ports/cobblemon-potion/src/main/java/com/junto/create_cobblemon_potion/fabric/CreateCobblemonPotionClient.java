package com.junto.create_cobblemon_potion.fabric;

import com.junto.create_cobblemon_potion.common.fluids.BaseFluidType;
import com.junto.create_cobblemon_potion.core.registry.FluidRegistry;
import com.junto.create_cobblemon_potion.core.registry.FluidTypeRegistry;
import java.lang.reflect.Field;
import java.util.function.Supplier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

/** Registers fluid rendering for every X_SOURCE / X_FLOWING / X_FLUID_TYPE triple declared by the mod. */
public class CreateCobblemonPotionClient implements ClientModInitializer {
   @Override
   public void onInitializeClient() {
      try {
         for (Field f : FluidRegistry.class.getFields()) {
            if (!f.getName().endsWith("_SOURCE")) continue;
            String base = f.getName().substring(0, f.getName().length() - "_SOURCE".length());
            Fluid source = (Fluid) ((Supplier<?>) f.get(null)).get();
            Fluid flowing = (Fluid) ((Supplier<?>) FluidRegistry.class.getField(base + "_FLOWING").get(null)).get();
            BaseFluidType type = (BaseFluidType) ((Supplier<?>) FluidTypeRegistry.class.getField(base + "_FLUID_TYPE").get(null)).get();
            FluidRenderHandlerRegistry.INSTANCE.register(source, flowing, new SimpleFluidRenderHandler(
               type.getStillTextureRL(), type.getFlowingTextureRL(), type.getOverlayTextureRL(), type.getTintColor()));
            BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), new Fluid[]{source, flowing});
         }
      } catch (ReflectiveOperationException e) {
         throw new IllegalStateException("Failed to register Create Cobblemon Potion fluid rendering", e);
      }
   }
}
