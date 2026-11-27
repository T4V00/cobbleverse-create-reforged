package net.createcobblestone.neoforge.blocks;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import java.util.List;
import net.createcobblestone.neoforge.CreateCobblestoneNeoForge;
import net.createcobblestone.neoforge.data.GeneratorType;
import net.createcobblestone.neoforge.index.BlockEntities;
import net.createcobblestone.neoforge.index.Config;
import net.createcobblestone.neoforge.utils.CreateCobblestoneLang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.DataComponentMap.Builder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import org.jetbrains.annotations.NotNull;

public class MechanicalGeneratorBlockEntity extends KineticBlockEntity implements Container {
   final NonNullList<ItemStack> items;
   private final int size = 1;
   private double available = 0.0;
   public GeneratorType type;

   public MechanicalGeneratorBlockEntity(BlockEntityType<? extends MechanicalGeneratorBlockEntity> typeIn, BlockPos pos, BlockState state) {
      super(typeIn, pos, state);
      this.items = NonNullList.withSize(1, ItemStack.EMPTY);
      this.type = GeneratorType.NONE;
   }

   protected void write(CompoundTag compound, boolean clientPacket) {
      super.write(compound, clientPacket);
      this.type.writeToCompoundTag(compound);
   }

   protected void read(CompoundTag compound, Provider registries, boolean clientPacket) {
      super.read(compound, registries, clientPacket);

      try {
         this.changeType(GeneratorType.fromCompoundTag(compound));
      } catch (IllegalArgumentException var5) {
         CreateCobblestoneNeoForge.LOGGER.error("Invalid generator type \"{}\", setting type to NONE", compound.getString("type"));
         this.type = GeneratorType.NONE;
      }
   }

   protected void collectImplicitComponents(@NotNull Builder builder) {
      super.collectImplicitComponents(builder);
      CompoundTag tag = new CompoundTag();
      this.type.writeToCompoundTag(tag);
      builder.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(tag));
   }

   public int getContainerSize() {
      return 1;
   }

   public boolean isEmpty() {
      for (ItemStack itemstack : this.items) {
         if (!itemstack.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   @NotNull
   public ItemStack getItem(int index) {
      return (ItemStack)this.items.get(index);
   }

   @NotNull
   public ItemStack removeItem(int index, int count) {
      ItemStack itemstack = ContainerHelper.removeItem(this.items, index, count);
      if (!itemstack.isEmpty()) {
         this.setChanged();
      }

      return itemstack;
   }

   @NotNull
   public ItemStack removeItemNoUpdate(int index) {
      return ContainerHelper.takeItem(this.items, index);
   }

   public void setItem(int index, @NotNull ItemStack stack) {
      this.items.set(index, stack);
      if (stack.getCount() > this.getMaxStackSize()) {
         stack.setCount(this.getMaxStackSize());
      }

      this.setChanged();
   }

   public boolean stillValid(@NotNull Player player) {
      return true;
   }

   public void clearContent() {
      this.items.clear();
   }

   public void tick() {
      super.tick();

      Block generatorBlock;
      try {
         generatorBlock = this.type.getBlock();
         if (!Config.common().isEnabled(this.type)) {
            this.updateType(GeneratorType.NONE);
            return;
         }
      } catch (NullPointerException var4) {
         if ((Boolean)Config.common().enableDebugLogging.get()) {
            CreateCobblestoneNeoForge.LOGGER.error("Tried accessing generator block before world was loaded");
         }

         return;
      }

      if (generatorBlock != Blocks.AIR) {
         if (!this.type.isLoaded()) {
            this.updateType(GeneratorType.NONE);
            return;
         }

         if (this.available < this.type.getStorage()) {
            this.available = Math.min(this.available + Math.abs(this.getSpeed() * this.type.getOutputPerSecondPerRpm()) / 20.0F, (double)this.type.getStorage());
         }

         int current = ((ItemStack)this.items.getFirst()).getCount();
         int added = (int)this.available;
         this.available -= added;
         this.items.set(0, new ItemStack(generatorBlock, Math.min(current + added, this.type.getStorage())));
      }
   }

   public float calculateStressApplied() {
      float impact = this.type.getGeneratorStress();
      this.lastStressApplied = impact;
      return impact;
   }

   public void updateType(GeneratorType newType) {
      this.changeType(newType);
      this.setChanged();
   }

   public void changeType(GeneratorType newType) {
      if (newType == null) {
         if ((Boolean)Config.common().enableDebugLogging.get()) {
            CreateCobblestoneNeoForge.LOGGER.error("Attempted to update generator type to null");
         }
      } else {
         if ((Boolean)Config.common().enableDebugLogging.get()) {
            CreateCobblestoneNeoForge.LOGGER.info("Trying to update generator type from \"{}\" to \"{}\"", this.type.getId(), newType.getId());
         }

         if (!Config.common().isEnabled(newType)) {
            if (Config.common().isEnabled(this.type)) {
               CreateCobblestoneNeoForge.LOGGER
                  .error("Disabled generator type \"{}\", not changing old generator type. ({})", newType.getId(), this.type.getId());
               return;
            }

            newType = GeneratorType.NONE;
         }

         if ((Boolean)Config.common().enableDebugLogging.get()) {
            CreateCobblestoneNeoForge.LOGGER.info("Changing generator type from \"{}\" to \"{}\"", this.type.getId(), newType.getId());
         }

         this.type = newType;
         this.available = 0.0;
         this.items.clear();
      }
   }

   public static void registerCapabilities() {
      ItemStorage.SIDED.registerForBlockEntity((be, side) -> InventoryStorage.of(be, side), BlockEntities.MECHANICAL_GENERATOR.get());
   }

   public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
      boolean added = super.addToGoggleTooltip(tooltip, isPlayerSneaking);
      if (!this.type.equals(GeneratorType.NONE)) {
         Item generatedItem = this.type.getItem();
         CreateCobblestoneLang.translate("gui.goggles.generators.itemprefix", generatedItem.getName(generatedItem.getDefaultInstance()))
            .style(ChatFormatting.GREEN)
            .forGoggles(tooltip);
      }

      return added;
   }
}
