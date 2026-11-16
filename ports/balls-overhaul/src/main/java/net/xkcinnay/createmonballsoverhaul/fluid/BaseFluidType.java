package net.xkcinnay.createmonballsoverhaul.fluid;

import net.minecraft.resources.ResourceLocation;
import net.xkcinnay.createmonballsoverhaul.neocompat.FluidType;
import org.joml.Vector3f;

/** Holds the render data that NeoForge kept in IClientFluidTypeExtensions; rendered via Fabric in the client entrypoint. */
public class BaseFluidType extends FluidType {
   private final ResourceLocation stillTexture;
   private final ResourceLocation flowingTexture;
   private final ResourceLocation overlayTexture;
   private final int tintColor;
   private final Vector3f fogColor;

   public BaseFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor, Vector3f fogColor, FluidType.Properties properties) {
      super(properties);
      this.stillTexture = stillTexture;
      this.flowingTexture = flowingTexture;
      this.overlayTexture = overlayTexture;
      this.tintColor = tintColor;
      this.fogColor = fogColor;
   }

   public ResourceLocation getStillTexture() { return this.stillTexture; }
   public ResourceLocation getFlowingTexture() { return this.flowingTexture; }
   public ResourceLocation getOverlayTexture() { return this.overlayTexture; }
   public int getTintColor() { return this.tintColor; }
   public Vector3f getFogColor() { return this.fogColor; }
}
