package dev.cobbleverse.createreforged.rei;

import com.simibubi.create.AllItems;
import java.util.ArrayList;
import java.util.List;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

/**
 * Sequenced assembly in REI. Top row: start item, arrow, product (and junk outputs). Bottom row: one column per
 * step with the machine above the extra ingredient it consumes, in order. Repeat count is shown when above 1.
 */
public class AssemblyCategory implements DisplayCategory<AssemblyDisplay> {
   private static final int SLOT = 18;
   private static final int STEP = 22;

   @Override
   public CategoryIdentifier<? extends AssemblyDisplay> getCategoryIdentifier() {
      return AssemblyDisplay.ID;
   }

   @Override
   public Component getTitle() {
      return Component.translatable("create.recipe.sequenced_assembly");
   }

   @Override
   public Renderer getIcon() {
      return EntryStacks.of(AllItems.PRECISION_MECHANISM.asStack());
   }

   @Override
   public int getDisplayWidth(AssemblyDisplay display) {
      int stepsWidth = 12 + display.steps.size() * STEP;
      int resultsWidth = 12 + SLOT + 30 + Math.max(1, display.results.size()) * SLOT + 30;
      return Math.max(150, Math.max(stepsWidth, resultsWidth));
   }

   @Override
   public int getDisplayHeight() {
      return 96;
   }

   @Override
   public List<Widget> setupDisplay(AssemblyDisplay display, Rectangle bounds) {
      List<Widget> w = new ArrayList<>();
      w.add(Widgets.createRecipeBase(bounds));
      int left = bounds.x + 6;
      int top = bounds.y + 6;

      // start -> transitional -> results
      w.add(Widgets.createSlot(new Point(left, top)).entries(display.start).markInput());
      w.add(Widgets.createArrow(new Point(left + SLOT + 4, top)));
      w.add(Widgets.createSlot(new Point(left + SLOT + 30, top)).entries(display.transitional));
      int resX = left + SLOT + 30 + SLOT + 6;
      w.add(Widgets.createArrow(new Point(resX, top)));
      resX += 28;
      for (int i = 0; i < display.results.size(); i++) {
         Point p = new Point(resX + i * SLOT, top);
         if (i == 0) w.add(Widgets.createResultSlotBackground(p));
         w.add(Widgets.createSlot(p).entries(display.results.get(i)).disableBackground().markOutput());
      }

      // steps: machine on top, consumed ingredient below
      int stepTop = top + 26;
      for (int i = 0; i < display.steps.size(); i++) {
         AssemblyDisplay.Step step = display.steps.get(i);
         int x = left + i * STEP;
         w.add(Widgets.createLabel(new Point(x + 9, stepTop), Component.literal(String.valueOf(i + 1)))
            .noShadow().color(0xFF404040, 0xFFBBBBBB));
         w.add(Widgets.createSlot(new Point(x, stepTop + 10)).entries(step.machine()).disableBackground());
         if (!step.extra().isEmpty()) {
            w.add(Widgets.createSlot(new Point(x, stepTop + 30)).entries(step.extra()).markInput());
         }
         if (step.description() != null && !step.description().getString().isEmpty()) {
            w.add(Widgets.createTooltip(new Rectangle(x, stepTop + 10, SLOT, SLOT), step.description()));
         }
      }

      if (display.loops > 1) {
         w.add(Widgets.createLabel(new Point(bounds.x + 6, bounds.getMaxY() - 12),
            Component.translatable("create.recipe.assembly.repeat", display.loops).withStyle(ChatFormatting.DARK_GRAY))
            .leftAligned().noShadow());
      }
      return w;
   }
}
