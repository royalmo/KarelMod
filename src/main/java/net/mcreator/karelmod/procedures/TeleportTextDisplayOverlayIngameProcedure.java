package net.mcreator.karelmod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.karelmod.block.TeleporterBlock;
import net.mcreator.karelmod.KarelModModElements;

import java.util.Map;

@KarelModModElements.ModElement.Tag
public class TeleportTextDisplayOverlayIngameProcedure extends KarelModModElements.ModElement {
	public TeleportTextDisplayOverlayIngameProcedure(KarelModModElements instance) {
		super(instance, 16);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure TeleportTextDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure TeleportTextDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure TeleportTextDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure TeleportTextDisplayOverlayIngame!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		return (TeleporterBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock());
	}
}
