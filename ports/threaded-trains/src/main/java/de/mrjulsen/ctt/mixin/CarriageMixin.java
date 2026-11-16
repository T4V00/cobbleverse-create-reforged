package de.mrjulsen.ctt.mixin;

import com.simibubi.create.content.trains.entity.Carriage;
import de.mrjulsen.ctt.CreateThreadedTrains;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Carriage.class, remap = false)
public class CarriageMixin {
   @Redirect(method = "travel", at = @At(value = "INVOKE",
      target = "Lcom/simibubi/create/content/trains/entity/Carriage;manageEntities(Lnet/minecraft/class_1937;)V"))
   public void ctt$onTravel(Carriage carriage, Level level) {
      CreateThreadedTrains.getServer().ifPresent(x -> x.execute(() -> carriage.manageEntities(level)));
   }
}
