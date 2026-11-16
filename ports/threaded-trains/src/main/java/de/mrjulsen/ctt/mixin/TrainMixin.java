package de.mrjulsen.ctt.mixin;

import com.simibubi.create.content.trains.entity.Carriage;
import com.simibubi.create.content.trains.entity.Train;
import de.mrjulsen.ctt.CreateThreadedTrains;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Train.class, remap = false)
public class TrainMixin {
   @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
   private void ctt$onTickTrain(List<Carriage> carriages, Consumer<? super Carriage> consumer, Level level) {
      CreateThreadedTrains.getServer().ifPresent(x -> x.execute(() -> {
         for (Carriage c : carriages) {
            c.manageEntities(level);
         }
      }));
   }

   @Redirect(method = "collideWithOtherTrains", at = @At(value = "INVOKE",
      target = "Lnet/minecraft/class_1937;method_8437(Lnet/minecraft/class_1297;DDDFLnet/minecraft/class_1937$class_7867;)Lnet/minecraft/class_1927;"))
   private Explosion ctt$redirectTrainExplosion(Level level, Entity source, double x, double y, double z, float power, ExplosionInteraction interaction) {
      CreateThreadedTrains.getServer().ifPresent(s -> s.execute(() -> level.explode(source, x, y, z, power, interaction)));
      return null;
   }
}
