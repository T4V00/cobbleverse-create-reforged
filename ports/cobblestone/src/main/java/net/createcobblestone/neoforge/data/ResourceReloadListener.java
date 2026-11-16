package net.createcobblestone.neoforge.data;

import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

public class ResourceReloadListener implements SimpleSynchronousResourceReloadListener {
   @Override
   public ResourceLocation getFabricId() {
      return CreateCobblestoneNeoForge.asResource("generator_types");
   }

   @Override
   public void onResourceManagerReload(ResourceManager resourceManager) {
      GeneratorTypeLoader.loadGeneratorTypes(resourceManager);
   }
}
