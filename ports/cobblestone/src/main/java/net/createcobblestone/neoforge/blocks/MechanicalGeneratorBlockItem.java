package net.createcobblestone.neoforge.blocks;

import java.util.List;
import net.createcobblestone.neoforge.data.GeneratorType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class MechanicalGeneratorBlockItem extends BlockItem {
   public MechanicalGeneratorBlockItem(Block block, Properties properties) {
      super(block, properties);
   }

   public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
      CustomData beData = (CustomData)stack.get(DataComponents.BLOCK_ENTITY_DATA);
      Item generatedItem = GeneratorType.NONE.getItem();
      if (beData == null) {
         GeneratorType.NONE.writeToItemStack(stack);
      } else {
         generatedItem = GeneratorType.fromCompoundTag(beData.copyTag()).getItem();
      }

      if (generatedItem != Items.AIR) {
         tooltipComponents.add(
            Component.translatable("block.createcobblestone.generators.hovertext.itemprefix")
               .append(generatedItem.getName(generatedItem.getDefaultInstance()))
               .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY))
         );
      } else {
         tooltipComponents.add(
            Component.translatable("block.createcobblestone.generators.hovertext.no_item").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY))
         );
      }

      super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
   }

   @NotNull
   public ItemStack getDefaultInstance() {
      ItemStack defaultStack = super.getDefaultInstance();
      GeneratorType.NONE.writeToItemStack(defaultStack);
      return defaultStack;
   }

   public void onCraftedBy(@NotNull ItemStack stack, @NotNull Level level, @NotNull Player player) {
      GeneratorType.NONE.writeToItemStack(stack);
      super.onCraftedBy(stack, level, player);
   }
}
