package net.xkcinnay.createmonballsoverhaul.block;

import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import java.lang.Object;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredBlock;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredRegister;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredRegister.Blocks;
import net.xkcinnay.createmonballsoverhaul.item.ModItems;

public class ModBlocks {
   public static final Blocks BLOCKS = DeferredRegister.createBlocks("createmonballsoverhaul");

   private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
      DeferredBlock<T> toReturn = BLOCKS.register(name, block);
      registerBlockItem(name, toReturn);
      return toReturn;
   }

   private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
      ModItems.ITEMS.register(name, () -> new BlockItem((Block)block.get(), new Properties()));
   }

   public static void register(Object eventBus) {
      BLOCKS.register(eventBus);
   }
}
