package com.junto.create_cobblemon_potion.common.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;

public class MedicinalBrewLiquidBlock extends AbstractEffectLiquidBlock {
   public static final int INITIAL_STRENGTH = 240;
   private static final Supplier<BlockState> DEGRADED_STATE = Blocks.WATER::defaultBlockState;

   public MedicinalBrewLiquidBlock(Supplier<? extends FlowingFluid> pFluidSupplier, Properties pProperties) {
      super(pFluidSupplier, pProperties, 240, DEGRADED_STATE);
   }

   @Override
   protected void applyEffect(LivingEntity entity, Level level, BlockPos pos, int strength) {
   }
}
