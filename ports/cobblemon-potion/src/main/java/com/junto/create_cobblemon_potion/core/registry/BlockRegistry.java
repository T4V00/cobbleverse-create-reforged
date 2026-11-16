package com.junto.create_cobblemon_potion.core.registry;

import com.junto.create_cobblemon_potion.common.block.HyperPotionLiquidBlock;
import com.junto.create_cobblemon_potion.common.block.MaxPotionLiquidBlock;
import com.junto.create_cobblemon_potion.common.block.MedicinalBrewLiquidBlock;
import com.junto.create_cobblemon_potion.common.block.PotionLiquidBlock;
import com.junto.create_cobblemon_potion.common.block.SuperPotionLiquidBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;
import java.lang.Object;
import com.junto.create_cobblemon_potion.neocompat.DeferredHolder;
import com.junto.create_cobblemon_potion.neocompat.DeferredRegister;

public class BlockRegistry extends Blocks {
   public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks("create_cobblemon_potion");
   public static final DeferredHolder<Block, MedicinalBrewLiquidBlock> MEDICINAL_BREW_BLOCK = BLOCKS.register(
      "medicinal_brew_block", () -> new MedicinalBrewLiquidBlock(FluidRegistry.MEDICINAL_BREW_SOURCE, Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, PotionLiquidBlock> POTION_BLOCK = BLOCKS.register(
      "potion_block", () -> new PotionLiquidBlock(FluidRegistry.POTION_SOURCE, Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, SuperPotionLiquidBlock> SUPER_POTION_BLOCK = BLOCKS.register(
      "super_potion_block", () -> new SuperPotionLiquidBlock(FluidRegistry.SUPER_POTION_SOURCE, Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, HyperPotionLiquidBlock> HYPER_POTION_BLOCK = BLOCKS.register(
      "hyper_potion_block", () -> new HyperPotionLiquidBlock(FluidRegistry.HYPER_POTION_SOURCE, Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, MaxPotionLiquidBlock> MAX_POTION_BLOCK = BLOCKS.register(
      "max_potion_block", () -> new MaxPotionLiquidBlock(FluidRegistry.MAX_POTION_SOURCE, Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> FULL_RESTORE_BLOCK = BLOCKS.register(
      "full_restore_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.FULL_RESTORE_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> ETHER_BLOCK = BLOCKS.register(
      "ether_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.ETHER_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> MAX_ETHER_BLOCK = BLOCKS.register(
      "max_ether_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.MAX_ETHER_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> ELIXIR_BLOCK = BLOCKS.register(
      "elixir_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.ELIXIR_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> MAX_ELIXIR_BLOCK = BLOCKS.register(
      "max_elixir_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.MAX_ELIXIR_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> ANTIDOTE_BLOCK = BLOCKS.register(
      "antidote_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.ANTIDOTE_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> AWAKENING_BLOCK = BLOCKS.register(
      "awakening_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.AWAKENING_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> BURN_HEAL_BLOCK = BLOCKS.register(
      "burn_heal_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.BURN_HEAL_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> ICE_HEAL_BLOCK = BLOCKS.register(
      "ice_heal_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.ICE_HEAL_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> PARALYZE_HEAL_BLOCK = BLOCKS.register(
      "paralyze_heal_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.PARALYZE_HEAL_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );
   public static final DeferredHolder<Block, LiquidBlock> FULL_HEAL_BLOCK = BLOCKS.register(
      "full_heal_block", () -> new LiquidBlock((FlowingFluid)FluidRegistry.FULL_HEAL_SOURCE.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable())
   );

   public static void register(Object eventBus) {
      BLOCKS.register(eventBus);
   }
}
