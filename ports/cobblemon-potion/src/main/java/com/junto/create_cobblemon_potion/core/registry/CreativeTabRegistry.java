package com.junto.create_cobblemon_potion.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import java.lang.Object;
import com.junto.create_cobblemon_potion.neocompat.DeferredHolder;
import com.junto.create_cobblemon_potion.neocompat.DeferredRegister;

public class CreativeTabRegistry {
   public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "create_cobblemon_potion");
   public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATECOBBLEMONPOTION = CREATIVE_MODE_TABS.register(
      "create_cobblemon_potion",
      () -> net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup.builder()
         .title(Component.translatable("itemGroup.create_cobblemon_potion"))
         .icon(() -> ((BucketItem)ItemRegistry.MEDICINAL_BREW_BUCKET.get()).getDefaultInstance())
         .displayItems((itemDisplayParameters, output) -> {
            output.accept((ItemLike)ItemRegistry.MEDICINAL_BREW_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.POTION_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.SUPER_POTION_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.HYPER_POTION_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.MAX_POTION_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.FULL_RESTORE_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.ETHER_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.MAX_ETHER_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.ELIXIR_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.MAX_ELIXIR_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.ANTIDOTE_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.AWAKENING_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.BURN_HEAL_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.ICE_HEAL_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.PARALYZE_HEAL_BUCKET.get());
            output.accept((ItemLike)ItemRegistry.FULL_HEAL_BUCKET.get());
         })
         .build()
   );

   public static void register(Object eventBus) {
      CREATIVE_MODE_TABS.register(eventBus);
   }
}
