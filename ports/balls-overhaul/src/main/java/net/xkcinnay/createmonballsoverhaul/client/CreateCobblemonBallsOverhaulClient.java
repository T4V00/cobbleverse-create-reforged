package net.xkcinnay.createmonballsoverhaul.client;

import java.util.function.Supplier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.xkcinnay.createmonballsoverhaul.fluid.BaseFluidType;
import net.xkcinnay.createmonballsoverhaul.fluid.ModFluidTypes;
import net.xkcinnay.createmonballsoverhaul.fluid.ModFluids;
import net.xkcinnay.createmonballsoverhaul.neocompat.FluidType;

public class CreateCobblemonBallsOverhaulClient implements ClientModInitializer {
   @Override
   public void onInitializeClient() {
      fluid(ModFluidTypes.STANDARD_TUMBLESTONE_COATING_FLUID_TYPE, ModFluids.SOURCE_STANDARD_TUMBLESTONE_COATING, ModFluids.FLOWING_STANDARD_TUMBLESTONE_COATING);
      fluid(ModFluidTypes.LIGHT_TUMBLESTONE_COATING_FLUID_TYPE, ModFluids.SOURCE_LIGHT_TUMBLESTONE_COATING, ModFluids.FLOWING_LIGHT_TUMBLESTONE_COATING);
      fluid(ModFluidTypes.DENSE_TUMBLESTONE_COATING_FLUID_TYPE, ModFluids.SOURCE_DENSE_TUMBLESTONE_COATING, ModFluids.FLOWING_DENSE_TUMBLESTONE_COATING);
   }

   private static void fluid(Supplier<FluidType> type, Supplier<FlowingFluid> source, Supplier<FlowingFluid> flowing) {
      BaseFluidType t = (BaseFluidType) type.get();
      FluidRenderHandlerRegistry.INSTANCE.register(source.get(), flowing.get(),
         new SimpleFluidRenderHandler(t.getStillTexture(), t.getFlowingTexture(), t.getOverlayTexture(), t.getTintColor()));
      BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), new Fluid[]{source.get(), flowing.get()});
   }
}
