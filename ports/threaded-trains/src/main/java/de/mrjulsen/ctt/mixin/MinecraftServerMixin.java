package de.mrjulsen.ctt.mixin;

import de.mrjulsen.ctt.CreateThreadedTrains;
import java.util.function.BooleanSupplier;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecraftServer.class})
public class MinecraftServerMixin {
   @Inject(
      method = {"runServer()V"},
      at = {@At("HEAD")}
   )
   public void ctt$start(CallbackInfo ci) {
      CreateThreadedTrains.start((MinecraftServer)(Object)this);
   }

   @Inject(
      method = {"runServer()V"},
      at = {@At("TAIL")}
   )
   public void ctt$stop(CallbackInfo ci) {
      CreateThreadedTrains.stop((MinecraftServer)(Object)this);
   }

   @Inject(
      method = {"tickServer(Ljava/util/function/BooleanSupplier;)V"},
      at = {@At("HEAD")}
   )
   public void ctt$preTick(BooleanSupplier b, CallbackInfo ci) {
      CreateThreadedTrains.preTick((MinecraftServer)(Object)this);
   }

   @Inject(
      method = {"tickServer(Ljava/util/function/BooleanSupplier;)V"},
      at = {@At("TAIL")}
   )
   public void ctt$postTick(BooleanSupplier b, CallbackInfo ci) {
      CreateThreadedTrains.postTick((MinecraftServer)(Object)this);
   }
}
