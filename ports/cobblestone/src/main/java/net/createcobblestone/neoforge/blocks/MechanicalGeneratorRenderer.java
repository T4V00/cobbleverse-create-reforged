package net.createcobblestone.neoforge.blocks;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalGeneratorRenderer extends KineticBlockEntityRenderer<MechanicalGeneratorBlockEntity> {
   public MechanicalGeneratorRenderer(Context context) {
      super(context);
   }

   @Override
   protected BlockState getRenderedBlockState(MechanicalGeneratorBlockEntity be) {
      return shaft(((Direction)be.getBlockState().getValue(MechanicalGeneratorBlock.HORIZONTAL_FACING)).getAxis());
   }

   protected void renderSafe(MechanicalGeneratorBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
      Block renderedBlock = be.type.getBlock();
      if (renderedBlock != null) {
         ItemStack renderedItem = new ItemStack(renderedBlock);
         Direction shaftDirection = (Direction)be.getBlockState().getValue(MechanicalGeneratorBlock.HORIZONTAL_FACING);
         BlockPos bp = be.getBlockPos();
         if (shaftDirection == Direction.EAST || shaftDirection == Direction.WEST) {
            this.renderOnSides(
               ms,
               buffer,
               be,
               renderedItem,
               overlay,
               new Vec3[]{new Vec3(0.5, 1.0, 0.5), new Vec3(0.5, 0.0, 0.5), new Vec3(0.5, 0.5, 0.0), new Vec3(0.5, 0.5, 1.0)},
               new BlockPos[]{bp.above(), bp.below(), bp.north(), bp.south()}
            );
         } else if (shaftDirection == Direction.NORTH || shaftDirection == Direction.SOUTH) {
            this.renderOnSides(
               ms,
               buffer,
               be,
               renderedItem,
               overlay,
               new Vec3[]{new Vec3(0.5, 1.0, 0.5), new Vec3(0.5, 0.0, 0.5), new Vec3(0.0, 0.5, 0.5), new Vec3(1.0, 0.5, 0.5)},
               new BlockPos[]{bp.above(), bp.below(), bp.west(), bp.east()}
            );
         }

         super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
      }
   }

   private void renderOnSides(
      PoseStack ms,
      MultiBufferSource buffer,
      MechanicalGeneratorBlockEntity be,
      ItemStack renderedItem,
      int overlay,
      Vec3[] translations,
      BlockPos[] sidePositions
   ) {
      Level level = be.getLevel();
      if (level != null) {
         for (int i = 0; i < translations.length; i++) {
            int blockBright = level.getBrightness(LightLayer.BLOCK, sidePositions[i]);
            int skyBright = level.getBrightness(LightLayer.SKY, sidePositions[i]);
            int light = (skyBright << 20) + (blockBright << 4);
            ms.pushPose();
            ms.translate(translations[i].x, translations[i].y, translations[i].z);
            ms.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderStatic(renderedItem, ItemDisplayContext.FIXED, light, overlay, ms, buffer, be.getLevel(), 0);
            ms.popPose();
         }
      }
   }
}
