package de.mrjulsen.ctt.mixin;

import com.simibubi.create.content.trains.GlobalRailwayManager;
import com.simibubi.create.foundation.events.CommonEvents;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CommonEvents.class, remap = false)
public class CommonEventsMixin {
   @Redirect(method = "onServerWorldTick", at = @At(value = "INVOKE",
      target = "Lcom/simibubi/create/content/trains/GlobalRailwayManager;tick(Lnet/minecraft/class_1937;)V"))
   private static void ctt$disableGlobalManagerTick(GlobalRailwayManager manager, Level level) {
      // Railways are ticked on the worker thread instead (see CreateThreadedTrains.preTick).
   }
}
