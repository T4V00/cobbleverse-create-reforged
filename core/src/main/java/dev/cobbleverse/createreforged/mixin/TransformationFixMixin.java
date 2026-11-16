package dev.cobbleverse.createreforged.mixin;

import com.mojang.math.Transformation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * Porting Lib (bundled by the Sophisticated mods) and porting_lib_ufo (bundled by Create Reforged) both inject a
 * TransformationExtensions interface into Transformation with identical default methods. The JVM then throws
 * IncompatibleClassChangeError ("Conflicting default methods"), which breaks model loading and freezes the game on
 * the loading screen. Concrete class methods win over interface defaults, so defining them here fixes it for both.
 */
@Mixin(Transformation.class)
public abstract class TransformationFixMixin {
   @Shadow
   public abstract Matrix4f getMatrix();

   public boolean isIdentity() {
      return ((Object) this).equals(Transformation.identity());
   }

   public void transformNormal(Vector3f normal) {
      Matrix3f n = new Matrix3f(getMatrix());
      n.invert();
      n.transpose();
      normal.mul(n);
      normal.normalize();
   }

   public Transformation blockCenterToCorner() {
      return cvcr$applyOrigin(new Vector3f(0.5f, 0.5f, 0.5f));
   }

   public Transformation blockCornerToCenter() {
      return cvcr$applyOrigin(new Vector3f(-0.5f, -0.5f, -0.5f));
   }

   @Unique
   private Transformation cvcr$applyOrigin(Vector3f origin) {
      if (isIdentity()) return Transformation.identity();
      Matrix4f ret = new Matrix4f(getMatrix());
      Matrix4f tmp = new Matrix4f().translation(origin.x(), origin.y(), origin.z());
      tmp.mul(ret, ret);
      tmp.translation(-origin.x(), -origin.y(), -origin.z());
      ret.mul(tmp);
      return new Transformation(ret);
   }
}
