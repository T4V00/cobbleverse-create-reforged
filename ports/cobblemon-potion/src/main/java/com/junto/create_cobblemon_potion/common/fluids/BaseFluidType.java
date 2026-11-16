package com.junto.create_cobblemon_potion.common.fluids;

import org.jetbrains.annotations.Nullable;
import net.minecraft.resources.ResourceLocation;
import com.junto.create_cobblemon_potion.neocompat.FluidType;
import com.junto.create_cobblemon_potion.neocompat.FluidType.Properties;
import org.joml.Vector3f;

public class BaseFluidType extends FluidType {
   private final ResourceLocation stillTexture;
   private final ResourceLocation flowingTexture;
   private final ResourceLocation overlayTexture;
   private final int tintColor;
   private final Vector3f fogColor;

   public BaseFluidType(
      ResourceLocation stillTexture,
      ResourceLocation flowingTexture,
      @Nullable ResourceLocation overlayTexture,
      int tintColor,
      Vector3f fogColor,
      Properties properties
   ) {
      super(properties);
      this.stillTexture = stillTexture;
      this.flowingTexture = flowingTexture;
      this.overlayTexture = overlayTexture;
      this.tintColor = tintColor;
      this.fogColor = fogColor;
   }

   public ResourceLocation getStillTextureRL() {
      return this.stillTexture;
   }

   public ResourceLocation getFlowingTextureRL() {
      return this.flowingTexture;
   }

   public int getTintColor() {
      return this.tintColor;
   }

   @Nullable
   public ResourceLocation getOverlayTextureRL() {
      return this.overlayTexture;
   }

   public Vector3f getFogColor() {
      return this.fogColor;
   }
}
