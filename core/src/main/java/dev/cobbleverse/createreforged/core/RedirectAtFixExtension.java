package dev.cobbleverse.createreforged.core;

import com.llamalad7.mixinextras.lib.apache.commons.tuple.Pair;
import com.llamalad7.mixinextras.utils.MixinInternals;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.ext.IExtension;
import org.spongepowered.asm.mixin.transformer.ext.ITargetClassContext;

/**
 * Create Aeronautics Reforged ships mixins whose {@code @Redirect} (and similar) annotations store {@code at} as a
 * one-element array instead of a single {@code @At}. MixinExtras casts that value to AnnotationNode and crashes the
 * game at startup (ClassCastException: ArrayList cannot be cast to AnnotationNode). This extension runs before
 * MixinExtras' own transformer and rewrites those values in memory; the original jar is left untouched.
 */
public class RedirectAtFixExtension implements IExtension {
   private static final Logger LOGGER = LoggerFactory.getLogger("Cobbleverse x Create Reforged");
   private static final Set<String> SINGLE_AT = Set.of(
      "Lorg/spongepowered/asm/mixin/injection/Redirect;",
      "Lorg/spongepowered/asm/mixin/injection/ModifyArg;",
      "Lorg/spongepowered/asm/mixin/injection/ModifyArgs;",
      "Lorg/spongepowered/asm/mixin/injection/ModifyVariable;");
   private final Set<ClassNode> done = Collections.newSetFromMap(new WeakHashMap<>());
   private int fixed;

   public static void register() {
      MixinInternals.registerExtension(new RedirectAtFixExtension(), true);
   }

   @Override
   public boolean checkActive(MixinEnvironment environment) {
      return true;
   }

   @Override
   public void preApply(ITargetClassContext context) {
      for (Pair<IMixinInfo, ClassNode> pair : MixinInternals.getMixinsFor(context)) {
         ClassNode node = pair.getRight();
         if (node == null || !this.done.add(node)) continue;
         for (MethodNode method : node.methods) {
            fix(method.visibleAnnotations, node, method);
            fix(method.invisibleAnnotations, node, method);
         }
      }
   }

   private void fix(List<AnnotationNode> annotations, ClassNode owner, MethodNode method) {
      if (annotations == null) return;
      for (AnnotationNode a : annotations) {
         if (!SINGLE_AT.contains(a.desc) || a.values == null) continue;
         for (int i = 0; i + 1 < a.values.size(); i += 2) {
            if ("at".equals(a.values.get(i)) && a.values.get(i + 1) instanceof List<?> list && list.size() == 1) {
               a.values.set(i + 1, list.get(0));
               if (this.fixed++ == 0) {
                  LOGGER.info("Fixing malformed mixin annotations (first: {}.{})", owner.name, method.name);
               }
            }
         }
      }
   }

   @Override
   public void postApply(ITargetClassContext context) {
   }

   @Override
   public void export(MixinEnvironment env, String name, boolean force, ClassNode classNode) {
   }
}
