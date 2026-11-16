package net.xkcinnay.createmonballsoverhaul.neocompat;

import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

/** Fabric re-implementation of NeoForge's BaseFlowingFluid (same semantics, vanilla API only). */
public abstract class BaseFlowingFluid extends FlowingFluid {
   private final Supplier<? extends Fluid> flowing;
   private final Supplier<? extends Fluid> still;
   private final Supplier<? extends Item> bucket;
   private final Supplier<? extends LiquidBlock> block;
   private final int slopeFindDistance;
   private final int levelDecreasePerBlock;
   private final float explosionResistance;
   private final int tickRate;

   protected BaseFlowingFluid(Properties properties) {
      this.flowing = properties.flowing;
      this.still = properties.still;
      this.bucket = properties.bucket;
      this.block = properties.block;
      this.slopeFindDistance = properties.slopeFindDistance;
      this.levelDecreasePerBlock = properties.levelDecreasePerBlock;
      this.explosionResistance = properties.explosionResistance;
      this.tickRate = properties.tickRate;
   }

   @Override
   public Fluid getFlowing() {
      return this.flowing.get();
   }

   @Override
   public Fluid getSource() {
      return this.still.get();
   }

   @Override
   protected boolean canConvertToSource(Level level) {
      return false;
   }

   @Override
   protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
      BlockEntity be = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
      Block.dropResources(state, level, pos, be);
   }

   @Override
   protected int getSlopeFindDistance(LevelReader level) {
      return this.slopeFindDistance;
   }

   @Override
   protected int getDropOff(LevelReader level) {
      return this.levelDecreasePerBlock;
   }

   @Override
   public Item getBucket() {
      return this.bucket != null ? this.bucket.get() : Items.AIR;
   }

   @Override
   protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
      return direction == Direction.DOWN && !isSame(fluid);
   }

   @Override
   public int getTickDelay(LevelReader level) {
      return this.tickRate;
   }

   @Override
   protected float getExplosionResistance() {
      return this.explosionResistance;
   }

   @Override
   protected BlockState createLegacyBlock(FluidState state) {
      if (this.block != null) {
         return this.block.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
      }
      return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
   }

   @Override
   public boolean isSame(Fluid fluid) {
      return fluid == this.still.get() || fluid == this.flowing.get();
   }

   @Override
   public Optional<SoundEvent> getPickupSound() {
      return Optional.of(SoundEvents.BUCKET_FILL);
   }

   public static class Flowing extends BaseFlowingFluid {
      public Flowing(Properties properties) {
         super(properties);
         registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
      }

      @Override
      protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
         super.createFluidStateDefinition(builder);
         builder.add(LEVEL);
      }

      @Override
      public int getAmount(FluidState state) {
         return state.getValue(LEVEL);
      }

      @Override
      public boolean isSource(FluidState state) {
         return false;
      }
   }

   public static class Source extends BaseFlowingFluid {
      public Source(Properties properties) {
         super(properties);
      }

      @Override
      public int getAmount(FluidState state) {
         return 8;
      }

      @Override
      public boolean isSource(FluidState state) {
         return true;
      }
   }

   public static class Properties {
      private final Supplier<? extends Fluid> still;
      private final Supplier<? extends Fluid> flowing;
      private Supplier<? extends Item> bucket;
      private Supplier<? extends LiquidBlock> block;
      private int slopeFindDistance = 4;
      private int levelDecreasePerBlock = 1;
      private float explosionResistance = 1;
      private int tickRate = 5;

      public Properties(Supplier<?> fluidType, Supplier<? extends Fluid> still, Supplier<? extends Fluid> flowing) {
         this.still = still;
         this.flowing = flowing;
      }

      public Properties bucket(Supplier<? extends Item> bucket) { this.bucket = bucket; return this; }
      public Properties block(Supplier<? extends LiquidBlock> block) { this.block = block; return this; }
      public Properties slopeFindDistance(int v) { this.slopeFindDistance = v; return this; }
      public Properties levelDecreasePerBlock(int v) { this.levelDecreasePerBlock = v; return this; }
      public Properties explosionResistance(float v) { this.explosionResistance = v; return this; }
      public Properties tickRate(int v) { this.tickRate = v; return this; }
   }
}
