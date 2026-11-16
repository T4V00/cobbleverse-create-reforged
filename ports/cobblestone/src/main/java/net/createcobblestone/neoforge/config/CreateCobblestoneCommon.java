package net.createcobblestone.neoforge.config;

import java.util.Objects;
import net.createcobblestone.neoforge.data.GeneratorType;
import net.createcobblestone.neoforge.index.Config;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.foundation.config.ConfigBase.ConfigBool;
import com.simibubi.create.foundation.config.ConfigBase.ConfigFloat;
import com.simibubi.create.foundation.config.ConfigBase.ConfigGroup;
import com.simibubi.create.foundation.config.ConfigBase.ConfigInt;

public class CreateCobblestoneCommon extends ConfigBase {
   public final ConfigGroup common = this.group(0, "common", new String[]{CreateCobblestoneCommon.Comments.common});
   public final ConfigInt generatorStress = this.i(8, 0, "generatorStress", CreateCobblestoneCommon.Comments.generatorStress);
   public final ConfigFloat outputPerSecondPerRpm = this.f(2.5F, 0.0F, "outputPerSecondPerRpm", CreateCobblestoneCommon.Comments.outputPerSecondPerRpm);
   public final ConfigInt maxStorage = this.i(256, 1, "maxGeneratorStorage", CreateCobblestoneCommon.Comments.maxStorage);
   public final ConfigBool enableDebugLogging = this.b(false, "enableDebugLogging", CreateCobblestoneCommon.Comments.enableDebugLogging);
   public final ConfigGroup generatorsGroup = this.group(1, "generatorsEnabled", CreateCobblestoneCommon.Comments.generatorsGroup);
   public final ConfigBool cobblestoneGeneratorEnabled = this.b(
      true, "cobblestoneGeneratorEnabled", new String[]{CreateCobblestoneCommon.Comments.generatorEnabled}
   );
   public final ConfigBool basaltGeneratorEnabled = this.b(true, "basaltGeneratorEnabled", new String[]{CreateCobblestoneCommon.Comments.generatorEnabled});
   public final ConfigBool stoneGeneratorEnabled = this.b(true, "stoneGeneratorEnabled", new String[]{CreateCobblestoneCommon.Comments.generatorEnabled});
   public final ConfigBool limestoneGeneratorEnabled = this.b(
      true, "limestoneGeneratorEnabled", new String[]{CreateCobblestoneCommon.Comments.generatorEnabled}
   );
   public final ConfigBool scoriaGeneratorEnabled = this.b(true, "scoriaGeneratorEnabled", new String[]{CreateCobblestoneCommon.Comments.generatorEnabled});
   public final ConfigBool deepslateGeneratorEnabled = this.b(true, "deepslateGeneratorEnabled", CreateCobblestoneCommon.Comments.deepslateGeneratorsEnabled);
   public final ConfigBool cobbledDeepslateGeneratorEnabled = this.b(
      true, "cobbledDeepslateGeneratorEnabled", CreateCobblestoneCommon.Comments.deepslateGeneratorsEnabled
   );

   public String getName() {
      return "common";
   }

   public boolean isEnabled(GeneratorType type) {
      if (type.equals(GeneratorType.NONE)) {
         return true;
      } else if (Objects.equals(type.getId(), "createcobblestone:generator_types/cobblestone.json")) {
         return (Boolean)Config.common().cobblestoneGeneratorEnabled.get();
      } else if (Objects.equals(type.getId(), "createcobblestone:generator_types/stone.json")) {
         return (Boolean)Config.common().stoneGeneratorEnabled.get();
      } else if (Objects.equals(type.getId(), "createcobblestone:generator_types/basalt.json")) {
         return (Boolean)Config.common().basaltGeneratorEnabled.get();
      } else if (Objects.equals(type.getId(), "createcobblestone:generator_types/limestone.json")) {
         return (Boolean)Config.common().limestoneGeneratorEnabled.get();
      } else if (Objects.equals(type.getId(), "createcobblestone:generator_types/scoria.json")) {
         return (Boolean)Config.common().scoriaGeneratorEnabled.get();
      } else if (Objects.equals(type.getId(), "createcobblestone:generator_types/deepslate.json")) {
         return (Boolean)Config.common().deepslateGeneratorEnabled.get();
      } else {
         return Objects.equals(type.getId(), "createcobblestone:generator_types/cobbled_deepslate.json")
            ? (Boolean)Config.common().cobbledDeepslateGeneratorEnabled.get()
            : true;
      }
   }

   private static class Comments {
      public static String common = "Common config";
      public static String[] generatorStress = new String[]{"stress * rpm = total stress", "(Can be overridden by custom generator types)"};
      public static String[] outputPerSecondPerRpm = new String[]{"(Can be overridden by custom generator types)"};
      public static String[] maxStorage = new String[]{"(Can be overridden by custom generator types)"};
      public static String[] enableDebugLogging = new String[]{
         "Log information that may be usefull for reporting a problem and debugging it.", "THIS WILL SPAM YOUR LOGS"
      };
      public static String[] generatorsGroup = new String[]{"Cobblestone generator types"};
      public static String generatorEnabled = "Enables the generator. When disabled, the generator is replaced with unset generators. Make a backup before doing this.";
      public static String[] deepslateGeneratorsEnabled = new String[]{
         generatorEnabled, "(Also requires deepslate generators datapack, https://modrinth.com/datapack/create-cobblestone-deepslate-generators)"
      };
   }
}
