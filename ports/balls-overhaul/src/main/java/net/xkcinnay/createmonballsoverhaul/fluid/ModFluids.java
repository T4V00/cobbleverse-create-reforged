package net.xkcinnay.createmonballsoverhaul.fluid;

import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import java.lang.Object;
import net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Flowing;
import net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Source;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredBlock;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredItem;
import net.xkcinnay.createmonballsoverhaul.neocompat.DeferredRegister;
import net.xkcinnay.createmonballsoverhaul.block.ModBlocks;
import net.xkcinnay.createmonballsoverhaul.item.ModItems;

public class ModFluids {
   public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, "createmonballsoverhaul");
   public static final Supplier<FlowingFluid> SOURCE_STANDARD_TUMBLESTONE_COATING = FLUIDS.register(
      "source_standard_tumblestone_coating", () -> new Source(ModFluids.STANDARD_TUMBLESTONE_COATING_PROPERTIES)
   );
   public static final Supplier<FlowingFluid> FLOWING_STANDARD_TUMBLESTONE_COATING = FLUIDS.register(
      "flowing_standard_tumblestone_coating", () -> new Flowing(ModFluids.STANDARD_TUMBLESTONE_COATING_PROPERTIES)
   );
   public static final DeferredBlock<LiquidBlock> STANDARD_TUMBLESTONE_COATING_BLOCK = ModBlocks.BLOCKS
      .register(
         "standard_tumblestone_coating_block",
         () -> new LiquidBlock(SOURCE_STANDARD_TUMBLESTONE_COATING.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable().mapColor(MapColor.COLOR_ORANGE))
      );
   public static final DeferredItem<Item> STANDARD_TUMBLESTONE_COATING_BUCKET = ModItems.ITEMS
      .registerItem(
         "standard_tumblestone_coating_bucket",
         properties -> new BucketItem((Fluid)SOURCE_STANDARD_TUMBLESTONE_COATING.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1))
      );
   public static final net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Properties STANDARD_TUMBLESTONE_COATING_PROPERTIES = new net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Properties(
         ModFluidTypes.STANDARD_TUMBLESTONE_COATING_FLUID_TYPE, SOURCE_STANDARD_TUMBLESTONE_COATING, FLOWING_STANDARD_TUMBLESTONE_COATING
      )
      .slopeFindDistance(3)
      .levelDecreasePerBlock(2)
      .tickRate(25)
      .block(STANDARD_TUMBLESTONE_COATING_BLOCK)
      .bucket(STANDARD_TUMBLESTONE_COATING_BUCKET);
   public static final Supplier<FlowingFluid> SOURCE_LIGHT_TUMBLESTONE_COATING = FLUIDS.register(
      "source_light_tumblestone_coating", () -> new Source(ModFluids.LIGHT_TUMBLESTONE_COATING_PROPERTIES)
   );
   public static final Supplier<FlowingFluid> FLOWING_LIGHT_TUMBLESTONE_COATING = FLUIDS.register(
      "flowing_light_tumblestone_coating", () -> new Flowing(ModFluids.LIGHT_TUMBLESTONE_COATING_PROPERTIES)
   );
   public static final DeferredBlock<LiquidBlock> LIGHT_TUMBLESTONE_COATING_BLOCK = ModBlocks.BLOCKS
      .register(
         "light_tumblestone_coating_block",
         () -> new LiquidBlock(SOURCE_LIGHT_TUMBLESTONE_COATING.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable().mapColor(MapColor.COLOR_LIGHT_BLUE))
      );
   public static final DeferredItem<Item> LIGHT_TUMBLESTONE_COATING_BUCKET = ModItems.ITEMS
      .registerItem(
         "light_tumblestone_coating_bucket",
         properties -> new BucketItem((Fluid)SOURCE_LIGHT_TUMBLESTONE_COATING.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1))
      );
   public static final net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Properties LIGHT_TUMBLESTONE_COATING_PROPERTIES = new net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Properties(
         ModFluidTypes.LIGHT_TUMBLESTONE_COATING_FLUID_TYPE, SOURCE_LIGHT_TUMBLESTONE_COATING, FLOWING_LIGHT_TUMBLESTONE_COATING
      )
      .slopeFindDistance(3)
      .levelDecreasePerBlock(2)
      .tickRate(25)
      .block(LIGHT_TUMBLESTONE_COATING_BLOCK)
      .bucket(LIGHT_TUMBLESTONE_COATING_BUCKET);
   public static final Supplier<FlowingFluid> SOURCE_DENSE_TUMBLESTONE_COATING = FLUIDS.register(
      "source_dense_tumblestone_coating", () -> new Source(ModFluids.DENSE_TUMBLESTONE_COATING_PROPERTIES)
   );
   public static final Supplier<FlowingFluid> FLOWING_DENSE_TUMBLESTONE_COATING = FLUIDS.register(
      "flowing_dense_tumblestone_coating", () -> new Flowing(ModFluids.DENSE_TUMBLESTONE_COATING_PROPERTIES)
   );
   public static final DeferredBlock<LiquidBlock> DENSE_TUMBLESTONE_COATING_BLOCK = ModBlocks.BLOCKS
      .register(
         "dense_tumblestone_coating_block",
         () -> new LiquidBlock(SOURCE_DENSE_TUMBLESTONE_COATING.get(), Properties.ofFullCopy(Blocks.WATER).noLootTable().mapColor(MapColor.COLOR_GRAY))
      );
   public static final DeferredItem<Item> DENSE_TUMBLESTONE_COATING_BUCKET = ModItems.ITEMS
      .registerItem(
         "dense_tumblestone_coating_bucket",
         properties -> new BucketItem((Fluid)SOURCE_DENSE_TUMBLESTONE_COATING.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1))
      );
   public static final net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Properties DENSE_TUMBLESTONE_COATING_PROPERTIES = new net.xkcinnay.createmonballsoverhaul.neocompat.BaseFlowingFluid.Properties(
         ModFluidTypes.DENSE_TUMBLESTONE_COATING_FLUID_TYPE, SOURCE_DENSE_TUMBLESTONE_COATING, FLOWING_DENSE_TUMBLESTONE_COATING
      )
      .slopeFindDistance(3)
      .levelDecreasePerBlock(2)
      .tickRate(25)
      .block(DENSE_TUMBLESTONE_COATING_BLOCK)
      .bucket(DENSE_TUMBLESTONE_COATING_BUCKET);

   public static void register(Object eventBus) {
      FLUIDS.register(eventBus);
   }
}
