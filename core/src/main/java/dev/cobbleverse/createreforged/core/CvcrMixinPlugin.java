package dev.cobbleverse.createreforged.core;

import java.util.List;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

/** Registers the annotation conversion (if this loader needs it) when mixin configs load, and only applies fixes whose target mod is present. */
public class CvcrMixinPlugin implements IMixinConfigPlugin {
   @Override
   public void onLoad(String mixinPackage) {
      RedirectAtFixExtension.registerIfNeeded();
   }

   @Override
   public String getRefMapperConfig() {
      return null;
   }

   @Override
   public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
      if (mixinClassName.endsWith("PokemonEntityFixMixin")) {
         return FabricLoader.getInstance().isModLoaded("cobblemon");
      }
      return true;
   }

   @Override
   public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
   }

   @Override
   public List<String> getMixins() {
      return null;
   }

   @Override
   public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
   }

   @Override
   public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
   }
}
