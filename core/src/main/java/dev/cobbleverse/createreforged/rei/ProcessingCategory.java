package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.content.processing.recipe.HeatCondition;
import java.util.ArrayList;
import java.util.List;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Generic REI category for Create Reforged processing recipes: inputs on the left (items then fluids),
 * an arrow, outputs on the right. Shows heat requirements for basin recipes and "not consumed" for deployers.
 */
public class ProcessingCategory implements DisplayCategory<ProcessingDisplay> {
   private static final int SLOT = 18;
   private static final int IN_COLS = 3;
   private static final int OUT_COLS = 2;
   private final ProcessingKind kind;

   public ProcessingCategory(ProcessingKind kind) {
      this.kind = kind;
   }

   @Override
   public CategoryIdentifier<? extends ProcessingDisplay> getCategoryIdentifier() {
      return this.kind.id;
   }

   @Override
   public Component getTitle() {
      return Component.translatable(this.kind.titleKey());
   }

   @Override
   public Renderer getIcon() {
      return EntryStacks.of(this.kind.icon.get());
   }

   @Override
   public int getDisplayWidth(ProcessingDisplay display) {
      return 150;
   }

   @Override
   public int getDisplayHeight() {
      return 66;
   }

   @Override
   public List<Widget> setupDisplay(ProcessingDisplay display, Rectangle bounds) {
      List<Widget> w = new ArrayList<>();
      w.add(Widgets.createRecipeBase(bounds));
      List<EntryIngredient> in = display.getInputEntries();
      List<EntryIngredient> out = display.getOutputEntries();

      int inRows = Math.max(1, (in.size() + IN_COLS - 1) / IN_COLS);
      int outRows = Math.max(1, (out.size() + OUT_COLS - 1) / OUT_COLS);
      int top = bounds.y + 6;
      int inTop = top + Math.max(0, (Math.max(inRows, outRows) - inRows) * SLOT / 2);
      int outTop = top + Math.max(0, (Math.max(inRows, outRows) - outRows) * SLOT / 2);
      int left = bounds.x + 6;

      for (int i = 0; i < in.size(); i++) {
         Point p = new Point(left + (i % IN_COLS) * SLOT, inTop + (i / IN_COLS) * SLOT);
         w.add(Widgets.createSlot(p).entries(in.get(i)).markInput());
      }

      int arrowX = left + IN_COLS * SLOT + 6;
      int midY = top + (Math.max(inRows, outRows) * SLOT) / 2 - 8;
      w.add(Widgets.createArrow(new Point(arrowX, midY)));

      int outLeft = arrowX + 30;
      for (int i = 0; i < out.size(); i++) {
         Point p = new Point(outLeft + (i % OUT_COLS) * SLOT, outTop + (i / OUT_COLS) * SLOT);
         if (i == 0) w.add(Widgets.createResultSlotBackground(p));
         w.add(Widgets.createSlot(p).entries(out.get(i)).disableBackground().markOutput());
      }

      int textY = bounds.getMaxY() - 13;
      if (this.kind.isBasin() && display.heat != null && display.heat != HeatCondition.NONE) {
         w.add(Widgets.createLabel(new Point(bounds.x + 6, textY), Component.translatable(display.heat.getTranslationKey()))
            .leftAligned().noShadow().color(display.heat.getColor()));
      } else if (this.kind.hasHeldItem() && display.keepHeldItem) {
         w.add(Widgets.createLabel(new Point(bounds.x + 6, textY),
            Component.translatable("create.recipe.deploying.not_consumed").withStyle(ChatFormatting.DARK_GRAY)).leftAligned().noShadow());
      }
      return w;
   }
}
