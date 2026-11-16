package net.xkcinnay.createmonballsoverhaul.fluid;

import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import java.lang.Object;
import net.xkcinnay.createmonballsoverhaul.neocompat.FluidType;
import net.xkcinnay.createmonballsoverhaul.neocompat.FluidType.Properties;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredRegister;
import org.joml.Vector3f;

public class ModFluidTypes {
   public static final ResourceLocation WATER_STILL_RL = ResourceLocation.fromNamespaceAndPath("createmonballsoverhaul", "block/tumblestone_coating_still");
   public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.fromNamespaceAndPath("createmonballsoverhaul", "block/tumblestone_coating_flow");
   public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");
   public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.createUnbacked("createmonballsoverhaul");
   public static final Supplier<FluidType> STANDARD_TUMBLESTONE_COATING_FLUID_TYPE = registerFluidType(
      "standard_tumblestone_coating_fluid",
      new BaseFluidType(
         WATER_STILL_RL,
         WATER_FLOWING_RL,
         WATER_OVERLAY_RL,
         -254909125,
         new Vector3f(0.80784315F, 0.39607844F, 0.23137255F),
         Properties.create().viscosity(2000)
      )
   );
   public static final Supplier<FluidType> LIGHT_TUMBLESTONE_COATING_FLUID_TYPE = registerFluidType(
      "light_tumblestone_coating_fluid",
      new BaseFluidType(
         WATER_STILL_RL,
         WATER_FLOWING_RL,
         WATER_OVERLAY_RL,
         -259402534,
         new Vector3f(0.5372549F, 0.83137256F, 0.85490197F),
         Properties.create().viscosity(2000)
      )
   );
   public static final Supplier<FluidType> DENSE_TUMBLESTONE_COATING_FLUID_TYPE = registerFluidType(
      "dense_tumblestone_coating_fluid",
      new BaseFluidType(
         WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, -262770074, new Vector3f(0.3372549F, 0.44705883F, 0.4F), Properties.create().viscosity(2000)
      )
   );

   private static Supplier<FluidType> registerFluidType(String name, FluidType fluidType) {
      return FLUID_TYPES.register(name, () -> fluidType);
   }

   public static void register(Object eventBus) {
      FLUID_TYPES.register(eventBus);
   }
}
