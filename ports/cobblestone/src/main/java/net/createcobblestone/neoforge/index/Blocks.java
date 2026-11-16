package net.createcobblestone.neoforge.index;

import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.simibubi.create.foundation.item.ItemDescription.Modifier;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.createcobblestone.neoforge.blocks.MechanicalGeneratorBlock;
import net.createcobblestone.neoforge.blocks.MechanicalGeneratorBlockItem;
import com.simibubi.create.foundation.item.TooltipHelper.Palette;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class Blocks {
   public static BlockEntry<MechanicalGeneratorBlock> MECHANICAL_GENERATOR_BLOCK;

   public static void register() {
      CreateCobblestoneNeoForge.LOGGER.info("Registering blocks for Create cobblestone NeoForge");

      try {
         int generator_stress = (Integer)Config.common().generatorStress.get();
      } catch (IllegalStateException var2) {
         CreateCobblestoneNeoForge.LOGGER.warn("Set generator stress tooltip stress to 8. (config not initialized, this is a common forge problem)");
         int generator_stressx = 8;
      }

      MECHANICAL_GENERATOR_BLOCK = CreateCobblestoneNeoForge.REGISTRATE
         .<MechanicalGeneratorBlock>block("mechanical_generator", MechanicalGeneratorBlock::new)
         .properties(p -> p.mapColor(MapColor.COLOR_BROWN))
         .tag(AllBlockTags.SAFE_NBT.tag)
         .<MechanicalGeneratorBlockItem>item((block, props) -> new MechanicalGeneratorBlockItem(block, props))
         .build()
         .register();
   }

   static {
      CreateCobblestoneNeoForge.REGISTRATE
         .setTooltipModifierFactory(item -> new Modifier(item, Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(KineticStats.create(item))));
   }
}
