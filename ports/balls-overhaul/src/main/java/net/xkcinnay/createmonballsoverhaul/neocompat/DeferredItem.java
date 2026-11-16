package net.xkcinnay.createmonballsoverhaul.neocompat;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DeferredItem<T extends Item> extends DeferredHolder<Item, T> implements ItemLike {
   public DeferredItem(ResourceLocation id, Supplier<? extends T> factory) {
      super(Registries.ITEM, id, factory);
   }

   @Override
   public Item asItem() {
      return get();
   }

   public ItemStack toStack() {
      return new ItemStack(get());
   }

   public ItemStack toStack(int count) {
      return new ItemStack(get(), count);
   }
}
