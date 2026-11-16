package __PKG__;

import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

/** Fabric stand-in for NeoForge's DeferredHolder: a lazily created, memoized registry entry. */
public class DeferredHolder<R, T extends R> implements Supplier<T> {
   protected final ResourceKey<? extends Registry<R>> registryKey;
   protected final ResourceLocation id;
   private final Supplier<? extends T> factory;
   private T value;

   public DeferredHolder(ResourceKey<? extends Registry<R>> registryKey, ResourceLocation id, Supplier<? extends T> factory) {
      this.registryKey = registryKey;
      this.id = id;
      this.factory = factory;
   }

   /** Creates the value on first use; later calls return the same instance. */
   synchronized T create() {
      if (this.value == null) {
         this.value = this.factory.get();
      }
      return this.value;
   }

   @Override
   public T get() {
      return create();
   }

   public T value() {
      return get();
   }

   public ResourceLocation getId() {
      return this.id;
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public ResourceKey<R> getKey() {
      return this.registryKey == null ? null : ResourceKey.create((ResourceKey) this.registryKey, this.id);
   }

   public boolean isBound() {
      return this.value != null;
   }
}
