package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import io.github.fabricators_of_create.porting_lib_ufo.fluids.FluidStack;
import java.util.ArrayList;
import java.util.List;
import me.shedaniel.rei.api.client.util.ClientEntryStacks;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;

/** Converts Create Reforged recipe parts (items, fluids in droplets, chanced outputs) to REI entries. */
final class Entries {
   private Entries() {
   }

   static EntryIngredient item(Ingredient ingredient) {
      return EntryIngredients.ofIngredient(ingredient);
   }

   static EntryIngredient fluid(FluidIngredient ingredient) {
      List<EntryStack<?>> stacks = new ArrayList<>();
      for (FluidStack fs : ingredient.getMatchingFluidStacks()) {
         stacks.add(EntryStacks.of(fs.getFluid(), ingredient.getRequiredAmount()));
      }
      return EntryIngredient.of(stacks);
   }

   static EntryIngredient fluid(FluidStack stack) {
      return EntryIngredient.of(EntryStacks.of(stack.getFluid(), stack.getAmount()));
   }

   /** An output stack; outputs below 100% get a "X% Chance" tooltip line, like Create's JEI view. */
   static EntryIngredient output(ProcessingOutput output) {
      EntryStack<?> stack = EntryStacks.of(output.getStack());
      float chance = output.getChance();
      if (chance < 1f) {
         String percent = chance < 0.01f ? "<1" : String.valueOf(Math.round(chance * 100));
         ClientEntryStacks.setTooltipProcessor(stack, (entry, tooltip) -> {
            tooltip.add(Component.translatable("create.recipe.processing.chance", percent).withStyle(ChatFormatting.GOLD));
            return tooltip;
         });
      }
      return EntryIngredient.of(stack);
   }
}
