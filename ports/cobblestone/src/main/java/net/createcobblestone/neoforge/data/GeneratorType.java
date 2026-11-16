package net.createcobblestone.neoforge.data;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.createcobblestone.neoforge.index.Config;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class GeneratorType {
   private static final Map<String, GeneratorType> ID_TO_TYPE = new HashMap<>();
   private static final Map<ResourceLocation, GeneratorType> BLOCK_TO_TYPE = new HashMap<>();
   private static final Map<String, String> LEGACY_IDS = Map.of(
      "cobblestone",
      "createcobblestone:generator_types/cobblestone.json",
      "stone",
      "createcobblestone:generator_types/stone.json",
      "basalt",
      "createcobblestone:generator_types/basalt.json",
      "limestone",
      "createcobblestone:generator_types/limestone.json",
      "scoria",
      "createcobblestone:generator_types/scoria.json",
      "deepslate",
      "createcobblestone:generator_types/deepslate.json",
      "cobbled_deepslate",
      "createcobblestone:generator_types/cobbled_deepslate.json"
   );
   private static final Set<String> DEEPSLATE_PATHS = Set.of(
      "createcobblestone:generator_types/deepslate.json", "createcobblestone:generator_types/cobbled_deepslate.json"
   );
   private final String id;
   private final ResourceLocation block;
   private final int generatorStress;
   private final float outputPerSecondPerRpm;
   private final int generatorStorage;
   public static final GeneratorType NONE = initializeNewType("none", BuiltInRegistries.BLOCK.getKey(Blocks.AIR), -1, -1.0F, -1);

   private GeneratorType(String id, ResourceLocation block, int generatorStress, float outputPerSecondPerRpm, int generatorStorage) {
      this.id = id;
      this.block = block;
      this.generatorStress = generatorStress;
      this.outputPerSecondPerRpm = outputPerSecondPerRpm;
      this.generatorStorage = generatorStorage;
   }

   public static void init() {
      ID_TO_TYPE.clear();
      BLOCK_TO_TYPE.clear();
      CreateCobblestoneNeoForge.LOGGER.info("Generator types cleared");
   }

   public static GeneratorType initializeNewType(String id, ResourceLocation block, int generatorStress, float outputPerSecondPerRpm, int generatorStorage) {
      Objects.requireNonNull(block, "block key");
      String normId = normalizeId(id);
      GeneratorType existing = BLOCK_TO_TYPE.get(block);
      if (existing != null) {
         CreateCobblestoneNeoForge.LOGGER
            .error(
               "Error initializing generator, generator type with block {} already exists (existing id: {}, new id: {})",
               new Object[]{block, existing.getId(), normId}
            );
         return existing;
      } else {
         GeneratorType type = new GeneratorType(normId, block, generatorStress, outputPerSecondPerRpm, generatorStorage);
         register(type);
         if ((Boolean)Config.common().enableDebugLogging.get()) {
            CreateCobblestoneNeoForge.LOGGER
               .info(
                  "Generator type {} initialized with block {} generatorStress {} outputPerSecondPerRpm {} generatorStorage {}",
                  new Object[]{normId, block, generatorStress, outputPerSecondPerRpm, generatorStorage}
               );
         }

         return type;
      }
   }

   private static void register(GeneratorType type) {
      ID_TO_TYPE.put(type.id, type);
      BLOCK_TO_TYPE.put(type.block, type);
   }

   public String getId() {
      return this.id;
   }

   public Block getBlock() throws NullPointerException {
      return (Block)BuiltInRegistries.BLOCK.get(this.block);
   }

   public Item getItem() throws NullPointerException {
      return this.getBlock().asItem();
   }

   public int getGeneratorStress() {
      return this.generatorStress == -1 ? (Integer)Config.common().generatorStress.get() : this.generatorStress;
   }

   public float getOutputPerSecondPerRpm() {
      return this.outputPerSecondPerRpm == -1.0F ? ((Double)Config.common().outputPerSecondPerRpm.get()).floatValue() : this.outputPerSecondPerRpm;
   }

   public int getStorage() {
      return this.generatorStorage == -1 ? (Integer)Config.common().maxStorage.get() : this.generatorStorage;
   }

   public boolean isLoaded() {
      return ID_TO_TYPE.containsKey(this.id);
   }

   @NotNull
   public static GeneratorType fromId(String id) {
      String norm = normalizeId(id);
      GeneratorType type = ID_TO_TYPE.get(norm);
      if (type != null) {
         return type;
      } else {
         String mapped = LEGACY_IDS.get(norm);
         if (mapped != null) {
            type = ID_TO_TYPE.get(mapped);
            if (type != null) {
               return type;
            }

            if (DEEPSLATE_PATHS.contains(mapped)) {
               CreateCobblestoneNeoForge.LOGGER
                  .error("Deepslate generators are now added using a data pack. Please install it from the mod page. (generator: {})", mapped);
            }
         }

         return NONE;
      }
   }

   @NotNull
   public static GeneratorType fromCompoundTag(CompoundTag tag) {
      return fromId(tag.getString("type"));
   }

   @NotNull
   public static GeneratorType fromBlock(Block block) {
      ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
      return Objects.requireNonNullElse(BLOCK_TO_TYPE.get(key), NONE);
   }

   @NotNull
   public static GeneratorType fromItem(Item item) {
      Block block = Block.byItem(item);
      if (block != Blocks.AIR) {
         return fromBlock(block);
      } else {
         ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(item);
         GeneratorType byItemKey = BLOCK_TO_TYPE.get(itemKey);
         return Objects.requireNonNullElse(byItemKey, NONE);
      }
   }

   public static List<GeneratorType> getTypes() {
      return List.copyOf(ID_TO_TYPE.values());
   }

   public void writeToCompoundTag(CompoundTag tag) {
      tag.putString("id", net.createcobblestone.neoforge.index.Blocks.MECHANICAL_GENERATOR_BLOCK.getId().toString());
      tag.putString("type", this.id);
   }

   public void writeToItemStack(ItemStack stack) {
      CompoundTag tag = new CompoundTag();
      this.writeToCompoundTag(tag);
      stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(tag));
   }

   private static String normalizeId(String id) {
      if (id != null && !id.isEmpty()) {
         return id.toLowerCase(Locale.ROOT);
      } else {
         throw new IllegalArgumentException("Generator type ID cannot be null or empty");
      }
   }
}
