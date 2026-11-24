package dev.cobbleverse.createreforged.core;

import com.llamalad7.mixinextras.wrapper.factory.FactoryRedirectWrapperMixinTransformer;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Detects whether the running MixinExtras can read {@code @Redirect} annotations whose {@code at} is an array, the
 * format newer Fabric Mixin versions compile to. It hands MixinExtras one sample annotation instead of comparing
 * version numbers, so it stays correct for loaders that were never tested.
 */
public final class MixinExtrasProbe {
   private MixinExtrasProbe() {
   }

   /** True only if MixinExtras fails on the array format with the known ClassCastException. */
   public static boolean cannotReadArrayAt() {
      try {
         new FactoryRedirectWrapperMixinTransformer().transform(null, sample());
         return false;
      } catch (ClassCastException e) {
         return true;
      } catch (LinkageError | RuntimeException e) {
         // Internals changed in some other way: leave other mods' annotations alone.
         return false;
      }
   }

   private static ClassNode sample() {
      AnnotationNode at = new AnnotationNode("Lorg/spongepowered/asm/mixin/injection/At;");
      at.visit("value", "INVOKE");
      AnnotationNode redirect = new AnnotationNode("Lorg/spongepowered/asm/mixin/injection/Redirect;");
      redirect.values = new ArrayList<>(List.of("at", new ArrayList<>(List.of(at))));
      MethodNode method = new MethodNode(Opcodes.ACC_PRIVATE, "sample", "()V", null, null);
      method.visibleAnnotations = new ArrayList<>(List.of(redirect));
      ClassNode node = new ClassNode();
      node.name = "dev/cobbleverse/createreforged/core/Sample";
      node.methods.add(method);
      return node;
   }
}
