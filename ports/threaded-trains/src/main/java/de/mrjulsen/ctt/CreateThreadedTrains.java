package de.mrjulsen.ctt;

import com.mojang.logging.LogUtils;
import com.simibubi.create.Create;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;

public final class CreateThreadedTrains {
   public static final String MOD_ID = "createthreadedtrains";
   public static final Logger LOGGER = LogUtils.getLogger();
   private static MinecraftServer serverInstance;
   private static WorkerThread thread;
   private static Future<?> future;

   public static void init() {
   }

   public static void start(MinecraftServer server) {
      serverInstance = server;
      future = null;
      thread = new WorkerThread("Train Worker");
   }

   public static void stop(MinecraftServer server) {
      thread.shutdown();
      future = null;
      serverInstance = null;
   }

   public static void preTick(MinecraftServer server) {
      future = thread.submitTask(() -> Create.RAILWAYS.tick(server.overworld()));
   }

   public static void postTick(MinecraftServer server) {
      if (future != null) {
         try {
            future.get();
         } catch (ExecutionException | InterruptedException var2) {
            LOGGER.warn("Error while waiting for train worker.", var2);
         }
      }
   }

   public static Optional<MinecraftServer> getServer() {
      return Optional.ofNullable(serverInstance);
   }
}
