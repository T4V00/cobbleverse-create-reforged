package com.junto.create_cobblemon_potion.common.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;

public abstract class AbstractEffectLiquidBlock extends LiquidBlock {
   public static final int MAX_STRENGTH = 255;
   public static final IntegerProperty STRENGTH = IntegerProperty.create("strength", 0, 255);
   protected final Supplier<? extends FlowingFluid> fluidSupplier;
   private final int initialStrength;
   private final Supplier<BlockState> degradedStateSupplier;

   protected AbstractEffectLiquidBlock(
      Supplier<? extends FlowingFluid> pFluidSupplier, Properties pProperties, int initialStrength, Supplier<BlockState> degradedStateSupplier
   ) {
      super(pFluidSupplier.get(), pProperties);
      this.fluidSupplier = pFluidSupplier;
      this.initialStrength = Math.min(Math.max(initialStrength, 1), 255);
      this.degradedStateSupplier = degradedStateSupplier;
      FluidState fluidstate = pFluidSupplier.get().getSource(false);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LEVEL, fluidstate.getAmount())).setValue(STRENGTH, this.initialStrength)
      );
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
      pBuilder.add(new Property[]{LEVEL, STRENGTH});
   }

   public boolean isRandomlyTicking(BlockState pState) {
      return (Integer)pState.getValue(STRENGTH) > 0;
   }

   public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
      int currentStrength = (Integer)pState.getValue(STRENGTH);
      if (currentStrength > 0) {
         int nextStrength = currentStrength - 1;
         if (nextStrength > 0) {
            pLevel.setBlock(pPos, (BlockState)pState.setValue(STRENGTH, nextStrength), 2);
         } else {
            BlockState degradedState = this.degradedStateSupplier.get();
            if (degradedState.hasProperty(LEVEL)) {
               int currentLevel = (Integer)pState.getValue(LEVEL);
               degradedState = (BlockState)degradedState.setValue(LEVEL, currentLevel);
            }

            pLevel.setBlock(pPos, degradedState, 2);
         }
      }
   }

   public final void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
      if (!pLevel.isClientSide && pEntity instanceof LivingEntity livingEntity) {
         int currentStrength = (Integer)pState.getValue(STRENGTH);
         if (currentStrength > 0) {
            this.applyEffect(livingEntity, pLevel, pPos, currentStrength);
         }
      }

      super.entityInside(pState, pLevel, pPos, pEntity);
   }

   protected abstract void applyEffect(LivingEntity var1, Level var2, BlockPos var3, int var4);
}
