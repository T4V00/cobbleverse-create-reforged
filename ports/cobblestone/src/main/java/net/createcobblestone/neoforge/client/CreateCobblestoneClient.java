package net.createcobblestone.neoforge.client;

import net.createcobblestone.neoforge.data.GeneratorTypeLoader;
import net.createcobblestone.neoforge.data.GeneratorTypesPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class CreateCobblestoneClient implements ClientModInitializer {
   @Override
   public void onInitializeClient() {
      ClientPlayNetworking.registerGlobalReceiver(GeneratorTypesPayload.TYPE,
         (payload, context) -> context.client().execute(() -> GeneratorTypeLoader.loadGeneratorTypesFromPacket(payload)));
   }
}
