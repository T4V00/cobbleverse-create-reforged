package net.createcobblestone.neoforge.blocks;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.base.IRotate.SpeedLevel;
import com.simibubi.create.foundation.block.IBE;
import net.createcobblestone.neoforge.data.GeneratorType;
import net.createcobblestone.neoforge.index.BlockEntities;
import net.createcobblestone.neoforge.index.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class MechanicalGeneratorBlock extends HorizontalKineticBlock implements IBE<MechanicalGeneratorBlockEntity> {
   public MechanicalGeneratorBlock(Properties properties) {
      super(properties);
   }

   @Override
   protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
      // 1: instant, 0: never (upstream 1.4.11)
      return 1.0F / (20.0F * Config.common().breakTime.getF());
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction preferredSide = this.getPreferredHorizontalFacing(context);
      return preferredSide != null ? (BlockState)this.defaultBlockState().setValue(HORIZONTAL_FACING, preferredSide) : super.getStateForPlacement(context);
   }

   public Axis getRotationAxis(BlockState state) {
      return ((Direction)state.getValue(HORIZONTAL_FACING)).getAxis();
   }

   public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
      return face.getAxis() == ((Direction)state.getValue(HORIZONTAL_FACING)).getAxis();
   }

   public Class<MechanicalGeneratorBlockEntity> getBlockEntityClass() {
      return MechanicalGeneratorBlockEntity.class;
   }

   public BlockEntityType<? extends MechanicalGeneratorBlockEntity> getBlockEntityType() {
      return (BlockEntityType<? extends MechanicalGeneratorBlockEntity>)BlockEntities.MECHANICAL_GENERATOR.get();
   }

   public SpeedLevel getMinimumRequiredSpeedLevel() {
      return SpeedLevel.SLOW;
   }

   @NotNull
   public ItemInteractionResult useItemOn(
      @NotNull ItemStack stack,
      @NotNull BlockState state,
      @NotNull Level level,
      @NotNull BlockPos pos,
      @NotNull Player player,
      @NotNull InteractionHand hand,
      @NotNull BlockHitResult hitResult
   ) {
      super.useItemOn(stack, state, level, pos, player, hand, hitResult);

      try {
         GeneratorType type = GeneratorType.fromItem(player.getMainHandItem().getItem());
         if (type.getBlock() != null && type != GeneratorType.NONE && Config.common().isEnabled(type)) {
            MechanicalGeneratorBlockEntity be = (MechanicalGeneratorBlockEntity)this.getBlockEntity(level, pos);
            if (be != null) {
               be.updateType(type);
               return ItemInteractionResult.SUCCESS;
            } else {
               return ItemInteractionResult.FAIL;
            }
         } else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
         }
      } catch (NullPointerException var10) {
         return ItemInteractionResult.FAIL;
      }
   }

   @NotNull
   @Override
   public ItemStack getCloneItemStack(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state) {
      ItemStack stack = super.getCloneItemStack(level, pos, state);
      if (level.getBlockEntity(pos) instanceof MechanicalGeneratorBlockEntity gen) {
         gen.type.writeToItemStack(stack);
      }

      return stack;
   }
}
