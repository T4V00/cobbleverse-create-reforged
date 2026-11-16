package de.mrjulsen.ctt.mixin;

import com.simibubi.create.content.trains.GlobalRailwayManager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = GlobalRailwayManager.class, remap = false)
public interface GlobalRailwayManagerAccessor {
   @Invoker(value = "tickTrains", remap = false)
   void invokeTickTrains(Level var1);
}
