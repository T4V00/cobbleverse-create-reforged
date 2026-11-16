package net.createcobblestone.neoforge.index;

import java.util.function.Supplier;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class CreativeTabs {
   public static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY =
      ResourceKey.create(Registries.CREATIVE_MODE_TAB, CreateCobblestoneNeoForge.asResource("main"));
   public static CreativeModeTab MAIN_TAB;

   public static CreativeModeTab getBaseTab() {
      return MAIN_TAB;
   }

   public static ResourceKey<CreativeModeTab> getBaseTabKey() {
      return MAIN_TAB_KEY;
   }

   public static void register() {
      MAIN_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_TAB_KEY, CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
         .title(Component.translatable("itemGroup.create_cobblestone"))
         .icon(() -> Blocks.MECHANICAL_GENERATOR_BLOCK.asStack())
         .displayItems(new RegistrateDisplayItemsGenerator(Tabs.MAIN))
         .build());
   }

   public static enum Tabs {
      MAIN(CreativeTabs::getBaseTabKey);

      private final Supplier<ResourceKey<CreativeModeTab>> keySupplier;

      private Tabs(Supplier<ResourceKey<CreativeModeTab>> keySupplier) {
         this.keySupplier = keySupplier;
      }

      public ResourceKey<CreativeModeTab> getKey() {
         return this.keySupplier.get();
      }

      public void use() {
         use(this);
      }

      public static void use(Tabs tab) {
         CreateCobblestoneNeoForge.REGISTRATE.setCreativeTab(tab.getKey());
      }
   }
}
