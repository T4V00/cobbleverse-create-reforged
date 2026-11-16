package net.createcobblestone.neoforge.index;

import com.simibubi.create.foundation.config.ConfigBase;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.createcobblestone.neoforge.config.CreateCobblestoneCommon;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
   public static final Map<Type, ConfigBase> CONFIGS = new EnumMap<>(Type.class);
   protected static CreateCobblestoneCommon common;

   private static <T extends ConfigBase> T register(Supplier<T> factory, Type side) {
      Pair<T, ModConfigSpec> specPair = new Builder().configure(builder -> {
         T config = factory.get();
         config.registerAll(builder);
         return config;
      });
      T config = specPair.getLeft();
      config.specification = specPair.getRight();
      CONFIGS.put(side, config);
      return config;
   }

   public static CreateCobblestoneCommon common() {
      return common;
   }

   public static ConfigBase byType(Type type) {
      return CONFIGS.get(type);
   }

   public static void onLoad(ModConfig modConfig) {
      for (ConfigBase config : CONFIGS.values()) {
         if (config.specification == modConfig.getSpec()) config.onLoad();
      }
   }

   public static void onReload(ModConfig modConfig) {
      for (ConfigBase config : CONFIGS.values()) {
         if (config.specification == modConfig.getSpec()) config.onReload();
      }
   }

   public static void register() {
      common = register(CreateCobblestoneCommon::new, Type.COMMON);
      for (Entry<Type, ConfigBase> pair : CONFIGS.entrySet()) {
         NeoForgeConfigRegistry.INSTANCE.register(CreateCobblestoneNeoForge.MOD_ID, pair.getKey(), pair.getValue().specification);
      }
   }
}
