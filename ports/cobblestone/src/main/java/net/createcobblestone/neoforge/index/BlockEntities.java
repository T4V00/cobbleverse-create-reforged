package net.createcobblestone.neoforge.index;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.createcobblestone.neoforge.blocks.MechanicalGeneratorBlockEntity;
import net.createcobblestone.neoforge.blocks.MechanicalGeneratorRenderer;
import com.simibubi.create.content.kinetics.base.ShaftInstance;

public class BlockEntities {
   public static BlockEntityEntry<MechanicalGeneratorBlockEntity> MECHANICAL_GENERATOR;

   public static void register() {
      CreateCobblestoneNeoForge.LOGGER.info("Registering blockEntities for Create cobblestone NeoForge");
      MECHANICAL_GENERATOR = CreateCobblestoneNeoForge.REGISTRATE
         .<MechanicalGeneratorBlockEntity>blockEntity("mechanical_generator", MechanicalGeneratorBlockEntity::new)
         .instance(() -> ShaftInstance::new, false)
         .validBlocks(Blocks.MECHANICAL_GENERATOR_BLOCK)
         .renderer(() -> ctx -> new MechanicalGeneratorRenderer(ctx))
         .register();
   }
}
