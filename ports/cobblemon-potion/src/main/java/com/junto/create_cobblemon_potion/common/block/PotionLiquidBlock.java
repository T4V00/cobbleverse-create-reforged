package com.junto.create_cobblemon_potion.common.block;

import com.junto.create_cobblemon_potion.core.registry.BlockRegistry;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;

public class PotionLiquidBlock extends AbstractEffectLiquidBlock {
   public static final int INITIAL_STRENGTH = 120;
   private static final Supplier<BlockState> DEGRADED_STATE = () -> (BlockState)((MedicinalBrewLiquidBlock)BlockRegistry.MEDICINAL_BREW_BLOCK.get())
      .defaultBlockState()
      .setValue(STRENGTH, 240);

   public PotionLiquidBlock(Supplier<? extends FlowingFluid> pFluidSupplier, Properties pProperties) {
      super(pFluidSupplier, pProperties, 120, DEGRADED_STATE);
   }

   @Override
   protected void applyEffect(LivingEntity entity, Level level, BlockPos pos, int strength) {
      if (strength >= 1) {
         entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, true, true));
      }
   }
}
