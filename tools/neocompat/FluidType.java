package __PKG__;

/**
 * Placeholder for NeoForge's FluidType. Fabric has no fluid-type registry: the physical behaviour lives in
 * the Fluid itself and rendering is registered client-side, so the properties here are only recorded.
 */
public class FluidType {
   protected final Properties properties;

   public FluidType(Properties properties) {
      this.properties = properties;
   }

   public String getDescriptionId() {
      return this.properties.descriptionId;
   }

   public static final class Properties {
      String descriptionId = "";
      int density = 1000;
      int viscosity = 1000;
      int temperature = 300;
      int lightLevel = 0;

      private Properties() {
      }

      public static Properties create() {
         return new Properties();
      }

      public Properties descriptionId(String id) { this.descriptionId = id; return this; }
      public Properties density(int v) { this.density = v; return this; }
      public Properties viscosity(int v) { this.viscosity = v; return this; }
      public Properties temperature(int v) { this.temperature = v; return this; }
      public Properties lightLevel(int v) { this.lightLevel = v; return this; }
      public Properties motionScale(double v) { return this; }
      public Properties fallDistanceModifier(float v) { return this; }
      public Properties canExtinguish(boolean v) { return this; }
      public Properties canConvertToSource(boolean v) { return this; }
      public Properties supportsBoating(boolean v) { return this; }
      public Properties canPushEntity(boolean v) { return this; }
      public Properties canSwim(boolean v) { return this; }
      public Properties canDrown(boolean v) { return this; }
      public Properties canHydrate(boolean v) { return this; }
      public Properties rarity(Object v) { return this; }
      public Properties pathType(Object v) { return this; }
      public Properties adjacentPathType(Object v) { return this; }
      public Properties sound(Object action, Object sound) { return this; }
   }
}
