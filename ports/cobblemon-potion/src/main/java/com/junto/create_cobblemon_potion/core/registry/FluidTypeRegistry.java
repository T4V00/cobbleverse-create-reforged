package com.junto.create_cobblemon_potion.core.registry;

import com.junto.create_cobblemon_potion.common.fluids.BaseFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import java.lang.Object;
import com.junto.create_cobblemon_potion.neocompat.FluidType;
import com.junto.create_cobblemon_potion.neocompat.FluidType.Properties;
import com.junto.create_cobblemon_potion.neocompat.DeferredHolder;
import com.junto.create_cobblemon_potion.neocompat.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class FluidTypeRegistry {
   public static final ResourceLocation WATER_STILL_RL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still");
   public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow");
   public static final ResourceLocation POTION_OVERLAY_RL = null;
   public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.createUnbacked("create_cobblemon_potion");
   public static final DeferredHolder<FluidType, BaseFluidType> MEDICINAL_BREW_FLUID_TYPE = register(
      "medicinal_brew_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.medicinal_brew_fluidtype"),
      -13780320,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> POTION_FLUID_TYPE = register(
      "potion_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.potion_fluidtype"),
      -9547876,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> SUPER_POTION_FLUID_TYPE = register(
      "super_potion_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.super_potion_fluidtype"),
      -4630190,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> HYPER_POTION_FLUID_TYPE = register(
      "hyper_potion_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.hyper_potion_fluidtype"),
      -2331478,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> MAX_POTION_FLUID_TYPE = register(
      "max_potion_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.max_potion_fluidtype"),
      -8151830,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> FULL_RESTORE_FLUID_TYPE = register(
      "full_restore_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.full_restore_fluidtype"),
      -6564482,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> ETHER_FLUID_TYPE = register(
      "ether_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.ether_fluidtype"),
      -2775331,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> MAX_ETHER_FLUID_TYPE = register(
      "max_ether_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.max_ether_fluidtype"),
      -4591209,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> ELIXIR_FLUID_TYPE = register(
      "elixir_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.elixir_fluidtype"),
      -1134955,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> MAX_ELIXIR_FLUID_TYPE = register(
      "max_elixir_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.max_elixir_fluidtype"),
      -6951199,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> ANTIDOTE_FLUID_TYPE = register(
      "antidote_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.antidote_fluidtype"),
      -198773,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> AWAKENING_FLUID_TYPE = register(
      "awakening_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.awakening_fluidtype"),
      -9644828,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> BURN_HEAL_FLUID_TYPE = register(
      "burn_heal_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.burn_heal_fluidtype"),
      -9970294,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> ICE_HEAL_FLUID_TYPE = register(
      "ice_heal_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.ice_heal_fluidtype"),
      -549484,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> PARALYZE_HEAL_FLUID_TYPE = register(
      "paralyze_heal_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.paralyze_heal_fluidtype"),
      -3086527,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );
   public static final DeferredHolder<FluidType, BaseFluidType> FULL_HEAL_FLUID_TYPE = register(
      "full_heal_fluidtype",
      Properties.create()
         .density(1000)
         .viscosity(1000)
         .sound(null, SoundEvents.BUCKET_FILL)
         .sound(null, SoundEvents.BUCKET_EMPTY)
         .descriptionId("fluid_type.create_cobblemon_potion.full_heal_fluidtype"),
      -725954,
      new Vector3f(0.972549F, 0.36078432F, 0.36078432F)
   );

   @NotNull
   private static DeferredHolder<FluidType, BaseFluidType> register(String name, Properties properties, int tintColor, Vector3f fogColor) {
      return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, POTION_OVERLAY_RL, tintColor, fogColor, properties));
   }

   public static void register(Object modEventBus) {
      FLUID_TYPES.register(modEventBus);
   }
}
