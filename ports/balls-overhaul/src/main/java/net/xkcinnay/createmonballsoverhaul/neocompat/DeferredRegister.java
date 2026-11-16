package net.xkcinnay.createmonballsoverhaul.neocompat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Minimal Fabric stand-in for NeoForge's DeferredRegister, used by this unofficial port.
 * Entries are created and registered, in declaration order, when {@link #register(Object)} is called.
 * A register created without a vanilla registry (e.g. NeoForge fluid types) only memoizes its suppliers.
 */
public class DeferredRegister<T> {
   protected final ResourceKey<? extends Registry<T>> registryKey;
   protected final String namespace;
   protected final List<DeferredHolder<T, ? extends T>> entries = new ArrayList<>();

   protected DeferredRegister(ResourceKey<? extends Registry<T>> registryKey, String namespace) {
      this.registryKey = registryKey;
      this.namespace = namespace;
   }

   public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
      return new DeferredRegister<>(key, namespace);
   }

   @SuppressWarnings("unchecked")
   public static <T> DeferredRegister<T> create(Registry<T> registry, String namespace) {
      return new DeferredRegister<>((ResourceKey<? extends Registry<T>>) registry.key(), namespace);
   }

   /** For registries that do not exist on Fabric (e.g. NeoForge fluid types). */
   public static <T> DeferredRegister<T> createUnbacked(String namespace) {
      return new DeferredRegister<>(null, namespace);
   }

   public static Items createItems(String namespace) {
      return new Items(namespace);
   }

   public static Blocks createBlocks(String namespace) {
      return new Blocks(namespace);
   }

   public String getNamespace() {
      return this.namespace;
   }

   protected <I extends T> DeferredHolder<T, I> createHolder(ResourceLocation id, Supplier<? extends I> sup) {
      return new DeferredHolder<>(this.registryKey, id, sup);
   }

   public <I extends T> DeferredHolder<T, I> register(String name, Supplier<? extends I> sup) {
      DeferredHolder<T, I> holder = createHolder(ResourceLocation.fromNamespaceAndPath(this.namespace, name), sup);
      this.entries.add(holder);
      return holder;
   }

   public <I extends T> DeferredHolder<T, I> register(String name, Function<ResourceLocation, ? extends I> func) {
      ResourceLocation id = ResourceLocation.fromNamespaceAndPath(this.namespace, name);
      return register(name, () -> func.apply(id));
   }

   /** Registers every entry. The argument (formerly the mod event bus) is ignored. */
   @SuppressWarnings("unchecked")
   public void register(Object ignoredBus) {
      Registry<T> registry = this.registryKey == null ? null : (Registry<T>) BuiltInRegistries.REGISTRY.get(this.registryKey.location());
      for (DeferredHolder<T, ? extends T> holder : this.entries) {
         T value = holder.create();
         if (registry != null) {
            Registry.register(registry, holder.getId(), value);
         }
      }
   }

   public List<DeferredHolder<T, ? extends T>> getEntries() {
      return this.entries;
   }

   public static class Items extends DeferredRegister<Item> {
      protected Items(String namespace) {
         super(net.minecraft.core.registries.Registries.ITEM, namespace);
      }

      @Override
      protected <I extends Item> DeferredHolder<Item, I> createHolder(ResourceLocation id, Supplier<? extends I> sup) {
         return new DeferredItem<>(id, sup);
      }

      @Override
      @SuppressWarnings("unchecked")
      public <I extends Item> DeferredItem<I> register(String name, Supplier<? extends I> sup) {
         return (DeferredItem<I>) super.register(name, sup);
      }

      @Override
      @SuppressWarnings("unchecked")
      public <I extends Item> DeferredItem<I> register(String name, Function<ResourceLocation, ? extends I> func) {
         return (DeferredItem<I>) super.register(name, func);
      }

      public <I extends Item> DeferredItem<I> registerItem(String name, Function<Item.Properties, ? extends I> func, Item.Properties props) {
         return register(name, () -> func.apply(props));
      }

      public <I extends Item> DeferredItem<I> registerItem(String name, Function<Item.Properties, ? extends I> func) {
         return registerItem(name, func, new Item.Properties());
      }

      public DeferredItem<Item> registerSimpleItem(String name, Item.Properties props) {
         return registerItem(name, Item::new, props);
      }

      public DeferredItem<Item> registerSimpleItem(String name) {
         return registerItem(name, Item::new, new Item.Properties());
      }

      public DeferredItem<BlockItem> registerSimpleBlockItem(String name, Supplier<? extends Block> block, Item.Properties props) {
         return register(name, () -> new BlockItem(block.get(), props));
      }

      public DeferredItem<BlockItem> registerSimpleBlockItem(String name, Supplier<? extends Block> block) {
         return registerSimpleBlockItem(name, block, new Item.Properties());
      }

      public DeferredItem<BlockItem> registerSimpleBlockItem(DeferredHolder<Block, ?> block) {
         return registerSimpleBlockItem(block.getId().getPath(), block::get, new Item.Properties());
      }
   }

   public static class Blocks extends DeferredRegister<Block> {
      protected Blocks(String namespace) {
         super(net.minecraft.core.registries.Registries.BLOCK, namespace);
      }

      @Override
      protected <I extends Block> DeferredHolder<Block, I> createHolder(ResourceLocation id, Supplier<? extends I> sup) {
         return new DeferredBlock<>(id, sup);
      }

      @Override
      @SuppressWarnings("unchecked")
      public <B extends Block> DeferredBlock<B> register(String name, Supplier<? extends B> sup) {
         return (DeferredBlock<B>) super.register(name, sup);
      }

      @Override
      @SuppressWarnings("unchecked")
      public <B extends Block> DeferredBlock<B> register(String name, Function<ResourceLocation, ? extends B> func) {
         return (DeferredBlock<B>) super.register(name, func);
      }

      public <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties props) {
         return register(name, () -> func.apply(props));
      }

      public <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> func) {
         return registerBlock(name, func, BlockBehaviour.Properties.of());
      }

      public DeferredBlock<Block> registerSimpleBlock(String name, BlockBehaviour.Properties props) {
         return registerBlock(name, Block::new, props);
      }
   }
}
