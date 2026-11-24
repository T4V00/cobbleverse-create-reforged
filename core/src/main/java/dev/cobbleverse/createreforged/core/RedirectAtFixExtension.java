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
 * Newer Fabric Mixin versions compile the {@code at} of {@code @Redirect} (and similar) as an array. That is valid, but
 * MixinExtras 0.5.0 and 0.5.4 (Fabric Loader 0.18.4 and 0.19.2) cast it to a single AnnotationNode, so mods built that
 * way, such as Create Aeronautics Reforged, crash the game at startup. MixinExtras 0.5.5 (Loader 0.19.5) reads it. On those loaders only (see {@link MixinExtrasProbe})
 * this extension runs before MixinExtras and converts the values in memory; the original jars are left untouched.
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

   /** Registers the conversion only when the running MixinExtras needs it. */
   public static void registerIfNeeded() {
      if (MixinExtrasProbe.cannotReadArrayAt()) {
         LOGGER.info("This MixinExtras cannot read array-style 'at' annotations, enabling in-memory conversion");
         MixinInternals.registerExtension(new RedirectAtFixExtension(), true);
      } else {
         LOGGER.info("This MixinExtras reads array-style 'at' annotations, conversion not needed");
      }
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
                  LOGGER.info("Converting array-style mixin annotations (first: {}.{})", owner.name, method.name);
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
