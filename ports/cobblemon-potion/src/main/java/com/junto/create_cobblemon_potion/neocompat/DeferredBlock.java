package com.junto.create_cobblemon_potion.neocompat;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class DeferredBlock<T extends Block> extends DeferredHolder<Block, T> implements ItemLike {
   public DeferredBlock(ResourceLocation id, Supplier<? extends T> factory) {
      super(Registries.BLOCK, id, factory);
   }

   @Override
   public Item asItem() {
      return get().asItem();
   }

   public ItemStack toStack() {
      return new ItemStack(this);
   }
}
