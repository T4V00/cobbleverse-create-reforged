package com.junto.create_cobblemon_potion.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import java.lang.Object;
import com.junto.create_cobblemon_potion.neocompat.BaseFlowingFluid.Flowing;
import com.junto.create_cobblemon_potion.neocompat.BaseFlowingFluid.Properties;
import com.junto.create_cobblemon_potion.neocompat.BaseFlowingFluid.Source;
import com.junto.create_cobblemon_potion.neocompat.DeferredHolder;
import com.junto.create_cobblemon_potion.neocompat.DeferredRegister;

public class FluidRegistry {
   public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, "create_cobblemon_potion");
   public static final DeferredHolder<Fluid, FlowingFluid> MEDICINAL_BREW_SOURCE = FLUIDS.register(
      "medicinal_brew_source", () -> new Source(FluidRegistry.MEDICINAL_BREW_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> MEDICINAL_BREW_FLOWING = FLUIDS.register(
      "medicinal_brew_flowing", () -> new Flowing(FluidRegistry.MEDICINAL_BREW_FLUID_PROPERTIES)
   );
   private static final Properties MEDICINAL_BREW_FLUID_PROPERTIES = new Properties(
         FluidTypeRegistry.MEDICINAL_BREW_FLUID_TYPE, MEDICINAL_BREW_SOURCE, MEDICINAL_BREW_FLOWING
      )
      .bucket(ItemRegistry.MEDICINAL_BREW_BUCKET)
      .block(BlockRegistry.MEDICINAL_BREW_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> POTION_SOURCE = FLUIDS.register(
      "potion_source", () -> new Source(FluidRegistry.POTION_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> POTION_FLOWING = FLUIDS.register(
      "potion_flowing", () -> new Flowing(FluidRegistry.POTION_FLUID_PROPERTIES)
   );
   private static final Properties POTION_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.POTION_FLUID_TYPE, POTION_SOURCE, POTION_FLOWING)
      .bucket(ItemRegistry.POTION_BUCKET)
      .block(BlockRegistry.POTION_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> SUPER_POTION_SOURCE = FLUIDS.register(
      "super_potion_source", () -> new Source(FluidRegistry.SUPER_POTION_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> SUPER_POTION_FLOWING = FLUIDS.register(
      "super_potion_flowing", () -> new Flowing(FluidRegistry.SUPER_POTION_FLUID_PROPERTIES)
   );
   private static final Properties SUPER_POTION_FLUID_PROPERTIES = new Properties(
         FluidTypeRegistry.SUPER_POTION_FLUID_TYPE, SUPER_POTION_SOURCE, SUPER_POTION_FLOWING
      )
      .bucket(ItemRegistry.SUPER_POTION_BUCKET)
      .block(BlockRegistry.SUPER_POTION_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> HYPER_POTION_SOURCE = FLUIDS.register(
      "hyper_potion_source", () -> new Source(FluidRegistry.HYPER_POTION_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> HYPER_POTION_FLOWING = FLUIDS.register(
      "hyper_potion_flowing", () -> new Flowing(FluidRegistry.HYPER_POTION_FLUID_PROPERTIES)
   );
   private static final Properties HYPER_POTION_FLUID_PROPERTIES = new Properties(
         FluidTypeRegistry.HYPER_POTION_FLUID_TYPE, HYPER_POTION_SOURCE, HYPER_POTION_FLOWING
      )
      .bucket(ItemRegistry.HYPER_POTION_BUCKET)
      .block(BlockRegistry.HYPER_POTION_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> MAX_POTION_SOURCE = FLUIDS.register(
      "max_potion_source", () -> new Source(FluidRegistry.MAX_POTION_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> MAX_POTION_FLOWING = FLUIDS.register(
      "max_potion_flowing", () -> new Flowing(FluidRegistry.MAX_POTION_FLUID_PROPERTIES)
   );
   private static final Properties MAX_POTION_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.MAX_POTION_FLUID_TYPE, MAX_POTION_SOURCE, MAX_POTION_FLOWING)
      .bucket(ItemRegistry.MAX_POTION_BUCKET)
      .block(BlockRegistry.MAX_POTION_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> FULL_RESTORE_SOURCE = FLUIDS.register(
      "full_restore_source", () -> new Source(FluidRegistry.FULL_RESTORE_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> FULL_RESTORE_FLOWING = FLUIDS.register(
      "full_restore_flowing", () -> new Flowing(FluidRegistry.FULL_RESTORE_FLUID_PROPERTIES)
   );
   private static final Properties FULL_RESTORE_FLUID_PROPERTIES = new Properties(
         FluidTypeRegistry.FULL_RESTORE_FLUID_TYPE, FULL_RESTORE_SOURCE, FULL_RESTORE_FLOWING
      )
      .bucket(ItemRegistry.FULL_RESTORE_BUCKET)
      .block(BlockRegistry.FULL_RESTORE_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> ETHER_SOURCE = FLUIDS.register(
      "ether_source", () -> new Source(FluidRegistry.ETHER_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> ETHER_FLOWING = FLUIDS.register(
      "ether_flowing", () -> new Flowing(FluidRegistry.ETHER_FLUID_PROPERTIES)
   );
   private static final Properties ETHER_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.ETHER_FLUID_TYPE, ETHER_SOURCE, ETHER_FLOWING)
      .bucket(ItemRegistry.ETHER_BUCKET)
      .block(BlockRegistry.ETHER_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> MAX_ETHER_SOURCE = FLUIDS.register(
      "max_ether_source", () -> new Source(FluidRegistry.MAX_ETHER_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> MAX_ETHER_FLOWING = FLUIDS.register(
      "max_ether_flowing", () -> new Flowing(FluidRegistry.MAX_ETHER_FLUID_PROPERTIES)
   );
   private static final Properties MAX_ETHER_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.MAX_ETHER_FLUID_TYPE, MAX_ETHER_SOURCE, MAX_ETHER_FLOWING)
      .bucket(ItemRegistry.MAX_ETHER_BUCKET)
      .block(BlockRegistry.MAX_ETHER_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> ELIXIR_SOURCE = FLUIDS.register(
      "elixir_source", () -> new Source(FluidRegistry.ELIXIR_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> ELIXIR_FLOWING = FLUIDS.register(
      "elixir_flowing", () -> new Flowing(FluidRegistry.ELIXIR_FLUID_PROPERTIES)
   );
   private static final Properties ELIXIR_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.ELIXIR_FLUID_TYPE, ELIXIR_SOURCE, ELIXIR_FLOWING)
      .bucket(ItemRegistry.ELIXIR_BUCKET)
      .block(BlockRegistry.ELIXIR_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> MAX_ELIXIR_SOURCE = FLUIDS.register(
      "max_elixir_source", () -> new Source(FluidRegistry.MAX_ELIXIR_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> MAX_ELIXIR_FLOWING = FLUIDS.register(
      "max_elixir_flowing", () -> new Flowing(FluidRegistry.MAX_ELIXIR_FLUID_PROPERTIES)
   );
   private static final Properties MAX_ELIXIR_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.MAX_ELIXIR_FLUID_TYPE, MAX_ELIXIR_SOURCE, MAX_ELIXIR_FLOWING)
      .bucket(ItemRegistry.MAX_ELIXIR_BUCKET)
      .block(BlockRegistry.MAX_ELIXIR_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> ANTIDOTE_SOURCE = FLUIDS.register(
      "antidote_source", () -> new Source(FluidRegistry.ANTIDOTE_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> ANTIDOTE_FLOWING = FLUIDS.register(
      "antidote_flowing", () -> new Flowing(FluidRegistry.ANTIDOTE_FLUID_PROPERTIES)
   );
   private static final Properties ANTIDOTE_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.ANTIDOTE_FLUID_TYPE, ANTIDOTE_SOURCE, ANTIDOTE_FLOWING)
      .bucket(ItemRegistry.ANTIDOTE_BUCKET)
      .block(BlockRegistry.ANTIDOTE_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> AWAKENING_SOURCE = FLUIDS.register(
      "awakening_source", () -> new Source(FluidRegistry.AWAKENING_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> AWAKENING_FLOWING = FLUIDS.register(
      "awakening_flowing", () -> new Flowing(FluidRegistry.AWAKENING_FLUID_PROPERTIES)
   );
   private static final Properties AWAKENING_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.AWAKENING_FLUID_TYPE, AWAKENING_SOURCE, AWAKENING_FLOWING)
      .bucket(ItemRegistry.AWAKENING_BUCKET)
      .block(BlockRegistry.AWAKENING_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> BURN_HEAL_SOURCE = FLUIDS.register(
      "burn_heal_source", () -> new Source(FluidRegistry.BURN_HEAL_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> BURN_HEAL_FLOWING = FLUIDS.register(
      "burn_heal_flowing", () -> new Flowing(FluidRegistry.BURN_HEAL_FLUID_PROPERTIES)
   );
   private static final Properties BURN_HEAL_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.BURN_HEAL_FLUID_TYPE, BURN_HEAL_SOURCE, BURN_HEAL_FLOWING)
      .bucket(ItemRegistry.BURN_HEAL_BUCKET)
      .block(BlockRegistry.BURN_HEAL_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> ICE_HEAL_SOURCE = FLUIDS.register(
      "ice_heal_source", () -> new Source(FluidRegistry.ICE_HEAL_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> ICE_HEAL_FLOWING = FLUIDS.register(
      "ice_heal_flowing", () -> new Flowing(FluidRegistry.ICE_HEAL_FLUID_PROPERTIES)
   );
   private static final Properties ICE_HEAL_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.ICE_HEAL_FLUID_TYPE, ICE_HEAL_SOURCE, ICE_HEAL_FLOWING)
      .bucket(ItemRegistry.ICE_HEAL_BUCKET)
      .block(BlockRegistry.ICE_HEAL_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> PARALYZE_HEAL_SOURCE = FLUIDS.register(
      "paralyze_heal_source", () -> new Source(FluidRegistry.PARALYZE_HEAL_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> PARALYZE_HEAL_FLOWING = FLUIDS.register(
      "paralyze_heal_flowing", () -> new Flowing(FluidRegistry.PARALYZE_HEAL_FLUID_PROPERTIES)
   );
   private static final Properties PARALYZE_HEAL_FLUID_PROPERTIES = new Properties(
         FluidTypeRegistry.PARALYZE_HEAL_FLUID_TYPE, PARALYZE_HEAL_SOURCE, PARALYZE_HEAL_FLOWING
      )
      .bucket(ItemRegistry.PARALYZE_HEAL_BUCKET)
      .block(BlockRegistry.PARALYZE_HEAL_BLOCK);
   public static final DeferredHolder<Fluid, FlowingFluid> FULL_HEAL_SOURCE = FLUIDS.register(
      "full_heal_source", () -> new Source(FluidRegistry.FULL_HEAL_FLUID_PROPERTIES)
   );
   public static final DeferredHolder<Fluid, FlowingFluid> FULL_HEAL_FLOWING = FLUIDS.register(
      "full_heal_flowing", () -> new Flowing(FluidRegistry.FULL_HEAL_FLUID_PROPERTIES)
   );
   private static final Properties FULL_HEAL_FLUID_PROPERTIES = new Properties(FluidTypeRegistry.FULL_HEAL_FLUID_TYPE, FULL_HEAL_SOURCE, FULL_HEAL_FLOWING)
      .bucket(ItemRegistry.FULL_HEAL_BUCKET)
      .block(BlockRegistry.FULL_HEAL_BLOCK);

   public static void register(Object modEventBus) {
      FLUIDS.register(modEventBus);
   }

   private FluidRegistry() {
   }
}
