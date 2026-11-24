package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.AllRecipeTypes;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REI integration for Create Reforged. Create Reforged only ships a JEI plugin, so with REI (as in COBBLEVERSE)
 * none of Create's machine recipes were visible. This registers 13 machine categories (see ProcessingKind) plus
 * sequenced assembly, with the machines as workstations, including the recipes added by the bundled ports. Mechanical
 * crafting, fan smoking/blasting, block cutting and the automatic crafting/brewing categories are not shown yet.
 */
public class CvcrReiPlugin implements REIClientPlugin {
   private static final Logger LOGGER = LoggerFactory.getLogger("Cobbleverse x Create Reforged");

   @Override
   public void registerCategories(CategoryRegistry registry) {
      for (ProcessingKind kind : ProcessingKind.values()) {
         registry.add(new ProcessingCategory(kind));
         for (ItemStack machine : kind.machines.get()) {
            registry.addWorkstations(kind.id, EntryStacks.of(machine));
         }
      }
      registry.add(new AssemblyCategory());
      registry.addWorkstations(AssemblyDisplay.ID,
         EntryStacks.of(AllBlocks.DEPLOYER.asStack()), EntryStacks.of(AllBlocks.MECHANICAL_PRESS.asStack()),
         EntryStacks.of(AllBlocks.SPOUT.asStack()), EntryStacks.of(AllBlocks.MECHANICAL_SAW.asStack()));
   }

   @Override
   @SuppressWarnings({"unchecked", "rawtypes"})
   public void registerDisplays(DisplayRegistry registry) {
      Class<ProcessingRecipe<?>> processing = (Class) ProcessingRecipe.class;
      for (ProcessingKind kind : ProcessingKind.values()) {
         RecipeType<ProcessingRecipe<?>> type = (RecipeType) kind.type.getType();
         Function<RecipeHolder<ProcessingRecipe<?>>, ProcessingDisplay> filler = holder -> new ProcessingDisplay(kind, holder);
         registry.registerRecipeFiller(processing, type, filler);
      }
      RecipeType<SequencedAssemblyRecipe> assembly = AllRecipeTypes.SEQUENCED_ASSEMBLY.getType();
      registry.registerRecipeFiller(SequencedAssemblyRecipe.class, assembly, AssemblyDisplay::new);
      LOGGER.info("Registered REI categories for Create Reforged ({} processing types + sequenced assembly)", ProcessingKind.values().length);
   }
}
