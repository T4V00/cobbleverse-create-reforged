package net.createcobblestone.neoforge;

import com.simibubi.create.foundation.data.CreateRegistrate;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeModConfigEvents;
import java.util.Collections;
import net.createcobblestone.neoforge.blocks.MechanicalGeneratorBlockEntity;
import net.createcobblestone.neoforge.data.GeneratorTypeLoader;
import net.createcobblestone.neoforge.data.GeneratorTypesPayload;
import net.createcobblestone.neoforge.data.ResourceReloadListener;
import net.createcobblestone.neoforge.index.BlockEntities;
import net.createcobblestone.neoforge.index.Blocks;
import net.createcobblestone.neoforge.index.Config;
import net.createcobblestone.neoforge.index.CreativeTabs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Fabric entrypoint (unofficial port for Create Reforged). */
public class CreateCobblestoneNeoForge implements ModInitializer {
   public static final String MOD_ID = "createcobblestone";
   public static final String NAME = "Create cobblestone";
   public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
   public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

   @Override
   public void onInitialize() {
      LOGGER.info("{} initializing on platform: Fabric (port)", NAME);
      Config.register();
      NeoForgeModConfigEvents.loading(MOD_ID).register(Config::onLoad);
      NeoForgeModConfigEvents.reloading(MOD_ID).register(Config::onReload);
      CreativeTabs.register();
      Blocks.register();
      BlockEntities.register();
      REGISTRATE.register();
      MechanicalGeneratorBlockEntity.registerCapabilities();
      GeneratorTypeLoader.register();

      PayloadTypeRegistry.playS2C().register(GeneratorTypesPayload.TYPE, GeneratorTypesPayload.STREAM_CODEC);
      ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new ResourceReloadListener());
      ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, joined) -> {
         LOGGER.info("Syncing generator types to client");
         GeneratorTypeLoader.sendGeneratorTypesToClient(Collections.singleton(player));
      });
   }

   public static ResourceLocation asResource(String path) {
      return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
   }
}
