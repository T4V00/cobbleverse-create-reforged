package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.sequenced.IAssemblyRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;

/** A Create Reforged sequenced assembly recipe: start item, ordered steps (machine + extra input), results. */
public class AssemblyDisplay extends BasicDisplay {
   public static final CategoryIdentifier<AssemblyDisplay> ID = CategoryIdentifier.of("cobbleverse_create_reforged", "sequenced_assembly");

   /** One step: the machine that performs it, the extra item/fluid it consumes (may be empty) and its description. */
   record Step(EntryIngredient machine, EntryIngredient extra, Component description) {
   }

   final EntryIngredient start;
   final EntryIngredient transitional;
   final List<EntryIngredient> results;
   final List<Step> steps;
   final int loops;

   public AssemblyDisplay(RecipeHolder<SequencedAssemblyRecipe> holder) {
      this(holder.value(), holder);
   }

   private AssemblyDisplay(SequencedAssemblyRecipe recipe, RecipeHolder<?> holder) {
      this(recipe, buildSteps(recipe), holder);
   }

   private AssemblyDisplay(SequencedAssemblyRecipe recipe, List<Step> steps, RecipeHolder<?> holder) {
      super(allInputs(recipe, steps), allOutputs(recipe), Optional.of(holder.id()));
      this.start = Entries.item(recipe.getIngredient());
      this.transitional = EntryIngredients.of(recipe.getTransitionalItem());
      this.results = allOutputs(recipe);
      this.steps = steps;
      this.loops = recipe.getLoops();
   }

   private static List<Step> buildSteps(SequencedAssemblyRecipe recipe) {
      List<Step> list = new ArrayList<>();
      for (SequencedRecipe<?> seq : recipe.getSequence()) {
         IAssemblyRecipe step = seq.getAsAssemblyRecipe();
         Set<ItemLike> machines = new LinkedHashSet<>();
         step.addRequiredMachines(machines);
         EntryIngredient machine = machines.isEmpty() ? EntryIngredient.empty()
            : EntryIngredient.of(machines.stream().map(EntryStacks::of).toList());
         List<Ingredient> items = new ArrayList<>();
         List<FluidIngredient> fluids = new ArrayList<>();
         step.addAssemblyIngredients(items);
         step.addAssemblyFluidIngredients(fluids);
         EntryIngredient extra = !items.isEmpty() ? Entries.item(items.get(0))
            : !fluids.isEmpty() ? Entries.fluid(fluids.get(0)) : EntryIngredient.empty();
         Component description;
         try {
            description = step.getDescriptionForAssembly();
         } catch (RuntimeException e) {
            description = Component.empty();
         }
         list.add(new Step(machine, extra, description));
      }
      return list;
   }

   private static List<EntryIngredient> allInputs(SequencedAssemblyRecipe recipe, List<Step> steps) {
      List<EntryIngredient> list = new ArrayList<>();
      list.add(Entries.item(recipe.getIngredient()));
      for (Step s : steps) if (!s.extra().isEmpty()) list.add(s.extra());
      return list;
   }

   private static List<EntryIngredient> allOutputs(SequencedAssemblyRecipe recipe) {
      List<EntryIngredient> list = new ArrayList<>();
      // the first result is the real product; the rest of the pool are junk outputs (shown with their chance)
      ItemLikeOutputs.collect(recipe).forEach(o -> list.add(Entries.output(o)));
      return list;
   }

   @Override
   public CategoryIdentifier<?> getCategoryIdentifier() {
      return ID;
   }

   /** Reads the result pool; chances are normalised the same way Create does (weight / total weight). */
   static final class ItemLikeOutputs {
      static List<ProcessingOutput> collect(SequencedAssemblyRecipe recipe) {
         List<ProcessingOutput> pool = recipe.resultPool;
         float total = 0;
         for (ProcessingOutput o : pool) total += o.getChance();
         List<ProcessingOutput> out = new ArrayList<>();
         for (ProcessingOutput o : pool) out.add(new ProcessingOutput(o.getStack(), total <= 0 ? 1f : o.getChance() / total));
         return out;
      }
   }
}
