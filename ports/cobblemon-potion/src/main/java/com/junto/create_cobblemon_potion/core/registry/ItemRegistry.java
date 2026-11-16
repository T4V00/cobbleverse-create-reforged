package com.junto.create_cobblemon_potion.core.registry;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.material.Fluid;
import java.lang.Object;
import com.junto.create_cobblemon_potion.neocompat.DeferredHolder;
import com.junto.create_cobblemon_potion.neocompat.DeferredRegister;
import com.junto.create_cobblemon_potion.neocompat.DeferredRegister.Items;

public class ItemRegistry {
   public static final Items ITEMS = DeferredRegister.createItems("create_cobblemon_potion");
   public static final DeferredHolder<Item, BucketItem> MEDICINAL_BREW_BUCKET = ITEMS.register(
      "medicinal_brew_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.MEDICINAL_BREW_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> POTION_BUCKET = ITEMS.register(
      "potion_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.POTION_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> SUPER_POTION_BUCKET = ITEMS.register(
      "super_potion_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.SUPER_POTION_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> HYPER_POTION_BUCKET = ITEMS.register(
      "hyper_potion_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.HYPER_POTION_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> MAX_POTION_BUCKET = ITEMS.register(
      "max_potion_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.MAX_POTION_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> FULL_RESTORE_BUCKET = ITEMS.register(
      "full_restore_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.FULL_RESTORE_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> ETHER_BUCKET = ITEMS.register(
      "ether_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.ETHER_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> MAX_ETHER_BUCKET = ITEMS.register(
      "max_ether_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.MAX_ETHER_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> ELIXIR_BUCKET = ITEMS.register(
      "elixir_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.ELIXIR_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> MAX_ELIXIR_BUCKET = ITEMS.register(
      "max_elixir_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.MAX_ELIXIR_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> ANTIDOTE_BUCKET = ITEMS.register(
      "antidote_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.ANTIDOTE_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> AWAKENING_BUCKET = ITEMS.register(
      "awakening_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.AWAKENING_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> BURN_HEAL_BUCKET = ITEMS.register(
      "burn_heal_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.BURN_HEAL_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> ICE_HEAL_BUCKET = ITEMS.register(
      "ice_heal_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.ICE_HEAL_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> PARALYZE_HEAL_BUCKET = ITEMS.register(
      "paralyze_heal_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.PARALYZE_HEAL_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );
   public static final DeferredHolder<Item, BucketItem> FULL_HEAL_BUCKET = ITEMS.register(
      "full_heal_bucket",
      () -> new BucketItem((Fluid)FluidRegistry.FULL_HEAL_SOURCE.get(), new Properties().craftRemainder(net.minecraft.world.item.Items.BUCKET).stacksTo(1))
   );

   public static void register(Object eventBus) {
      ITEMS.register(eventBus);
   }
}
