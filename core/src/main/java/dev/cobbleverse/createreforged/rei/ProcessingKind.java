package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import java.util.List;
import java.util.function.Supplier;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Create Reforged recipe types shown in REI. Mirrors the categories Create Reforged registers for JEI
 * (com.simibubi.create.compat.jei.CreateJEI): same titles, icons and machines.
 */
public enum ProcessingKind {
   MILLING("milling", AllRecipeTypes.MILLING, () -> AllBlocks.MILLSTONE.asStack(), () -> List.of(AllBlocks.MILLSTONE.asStack())),
   CRUSHING("crushing", AllRecipeTypes.CRUSHING, () -> AllBlocks.CRUSHING_WHEEL.asStack(), () -> List.of(AllBlocks.CRUSHING_WHEEL.asStack())),
   PRESSING("pressing", AllRecipeTypes.PRESSING, () -> AllBlocks.MECHANICAL_PRESS.asStack(), () -> List.of(AllBlocks.MECHANICAL_PRESS.asStack())),
   FAN_WASHING("fan_washing", AllRecipeTypes.SPLASHING, () -> AllItems.PROPELLER.asStack(),
      () -> List.of(AllBlocks.ENCASED_FAN.asStack(), new ItemStack(Items.WATER_BUCKET))),
   FAN_HAUNTING("fan_haunting", AllRecipeTypes.HAUNTING, () -> AllItems.PROPELLER.asStack(),
      () -> List.of(AllBlocks.ENCASED_FAN.asStack(), new ItemStack(Items.SOUL_CAMPFIRE))),
   MIXING("mixing", AllRecipeTypes.MIXING, () -> AllBlocks.MECHANICAL_MIXER.asStack(),
      () -> List.of(AllBlocks.MECHANICAL_MIXER.asStack(), AllBlocks.BASIN.asStack(), AllBlocks.BLAZE_BURNER.asStack())),
   PACKING("packing", AllRecipeTypes.COMPACTING, () -> AllBlocks.MECHANICAL_PRESS.asStack(),
      () -> List.of(AllBlocks.MECHANICAL_PRESS.asStack(), AllBlocks.BASIN.asStack(), AllBlocks.BLAZE_BURNER.asStack())),
   SAWING("sawing", AllRecipeTypes.CUTTING, () -> AllBlocks.MECHANICAL_SAW.asStack(), () -> List.of(AllBlocks.MECHANICAL_SAW.asStack())),
   SANDPAPER_POLISHING("sandpaper_polishing", AllRecipeTypes.SANDPAPER_POLISHING, () -> AllItems.SAND_PAPER.asStack(),
      () -> List.of(AllItems.SAND_PAPER.asStack(), AllItems.RED_SAND_PAPER.asStack())),
   ITEM_APPLICATION("item_application", AllRecipeTypes.ITEM_APPLICATION, () -> AllItems.BRASS_HAND.asStack(), List::of),
   DEPLOYING("deploying", AllRecipeTypes.DEPLOYING, () -> AllBlocks.DEPLOYER.asStack(),
      () -> List.of(AllBlocks.DEPLOYER.asStack(), AllBlocks.DEPOT.asStack())),
   SPOUT_FILLING("spout_filling", AllRecipeTypes.FILLING, () -> AllBlocks.SPOUT.asStack(), () -> List.of(AllBlocks.SPOUT.asStack())),
   DRAINING("draining", AllRecipeTypes.EMPTYING, () -> AllBlocks.ITEM_DRAIN.asStack(), () -> List.of(AllBlocks.ITEM_DRAIN.asStack()));

   public final String name;
   public final AllRecipeTypes type;
   public final Supplier<ItemStack> icon;
   public final Supplier<List<ItemStack>> machines;
   public final CategoryIdentifier<ProcessingDisplay> id;

   ProcessingKind(String name, AllRecipeTypes type, Supplier<ItemStack> icon, Supplier<List<ItemStack>> machines) {
      this.name = name;
      this.type = type;
      this.icon = icon;
      this.machines = machines;
      this.id = CategoryIdentifier.of("cobbleverse_create_reforged", name);
   }

   /** Lang key from Create's own assets, e.g. "create.recipe.milling". */
   public String titleKey() {
      return "create.recipe." + this.name;
   }

   /** Deploying-style recipes keep their second ingredient in the "held" slot. */
   public boolean hasHeldItem() {
      return this == DEPLOYING || this == ITEM_APPLICATION;
   }

   public boolean isBasin() {
      return this == MIXING || this == PACKING;
   }
}
