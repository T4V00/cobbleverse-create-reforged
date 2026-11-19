package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import io.github.fabricators_of_create.porting_lib_ufo.fluids.FluidStack;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

/** One Create Reforged processing recipe (milling, pressing, mixing, deploying, filling...). */
public class ProcessingDisplay extends BasicDisplay {
   final ProcessingKind kind;
   final int itemInputCount;
   final HeatCondition heat;
   final boolean keepHeldItem;

   public ProcessingDisplay(ProcessingKind kind, RecipeHolder<?> holder) {
      this(kind, (ProcessingRecipe<?>) holder.value(), holder);
   }

   private ProcessingDisplay(ProcessingKind kind, ProcessingRecipe<?> recipe, RecipeHolder<?> holder) {
      super(inputs(recipe), outputs(recipe), Optional.of(holder.id()));
      this.kind = kind;
      this.itemInputCount = recipe.getIngredients().size();
      this.heat = recipe.getRequiredHeat();
      this.keepHeldItem = recipe instanceof ItemApplicationRecipe iar && iar.shouldKeepHeldItem();
   }

   private static List<EntryIngredient> inputs(ProcessingRecipe<?> recipe) {
      List<EntryIngredient> list = new ArrayList<>();
      for (Ingredient i : recipe.getIngredients()) list.add(Entries.item(i));
      for (FluidIngredient f : recipe.getFluidIngredients()) list.add(Entries.fluid(f));
      return list;
   }

   private static List<EntryIngredient> outputs(ProcessingRecipe<?> recipe) {
      List<EntryIngredient> list = new ArrayList<>();
      for (ProcessingOutput o : recipe.getRollableResults()) list.add(Entries.output(o));
      for (FluidStack f : recipe.getFluidResults()) list.add(Entries.fluid(f));
      return list;
   }

   @Override
   public CategoryIdentifier<?> getCategoryIdentifier() {
      return this.kind.id;
   }
}
