package com.junto.create_cobblemon_potion.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class TestBlock extends Block {
   public TestBlock() {
      super(createProperties());
   }

   private static Properties createProperties() {
      return Properties.of().mapColor(MapColor.STONE).strength(1.5F, 6.0F).requiresCorrectToolForDrops();
   }
}
