package net.createcobblestone.neoforge.index;

import java.util.LinkedList;
import java.util.List;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.createcobblestone.neoforge.data.GeneratorType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import org.jetbrains.annotations.NotNull;

public final class RegistrateDisplayItemsGenerator implements DisplayItemsGenerator {
   private final CreativeTabs.Tabs tab;

   public RegistrateDisplayItemsGenerator(CreativeTabs.Tabs tab) {
      this.tab = tab;
   }

   public void accept(@NotNull ItemDisplayParameters pParameters, @NotNull Output output) {
      ResourceKey<CreativeModeTab> tab = this.tab.getKey();
      List<ItemStack> stacks = new LinkedList<>();
      ItemStack stack = Blocks.MECHANICAL_GENERATOR_BLOCK.asStack();
      GeneratorType.NONE.writeToItemStack(stack);
      stacks.add(stack);

      for (GeneratorType type : GeneratorType.getTypes()) {
         if (type != GeneratorType.NONE) {
            stack = Blocks.MECHANICAL_GENERATOR_BLOCK.asStack();
            type.writeToItemStack(stack);
            stacks.add(stack);
            if ((Boolean)Config.common().enableDebugLogging.get()) {
               CreateCobblestoneNeoForge.LOGGER.info("Added {} generator to creative menu", type.getId());
            }
         }
      }

      outputAll(output, stacks);
   }

   private static void outputAll(Output output, List<ItemStack> stacks) {
      for (ItemStack stack : stacks) {
         if (stack.getCount() != 1) {
            CreateCobblestoneNeoForge.LOGGER.error("Invalid stack size {} for stack {}.", stack.getCount(), stack);
         } else {
            output.accept(stack, TabVisibility.PARENT_AND_SEARCH_TABS);
         }
      }
   }

   public record TabInfo(ResourceKey<CreativeModeTab> key, CreativeModeTab tab) {
   }
}
