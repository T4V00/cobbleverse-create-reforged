package dev.cobbleverse.createreforged.mixin;

import com.cobblemon.mod.common.entity.pokemon.effects.EffectTracker;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * porting_lib_ufo (Create Reforged) calls getDimensions() at the end of Entity's constructor, before PokemonEntity's
 * own fields exist. That NPE fires on every Pokemon spawn and Neruina kicks the player ("ticking exception on your
 * player"). While the entity is still being constructed, fall back to the entity type's default dimensions.
 */
@Mixin(targets = "com.cobblemon.mod.common.entity.pokemon.PokemonEntity")
public abstract class PokemonEntityFixMixin {
   @Shadow(remap = false)
   private EffectTracker effects;

   @Inject(method = "method_18377(Lnet/minecraft/class_4050;)Lnet/minecraft/class_4048;", at = @At("HEAD"), cancellable = true, remap = false)
   private void cvcr$safeDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {
      if (this.effects == null) {
         cir.setReturnValue(((Entity) (Object) this).getType().getDimensions());
      }
   }
}
