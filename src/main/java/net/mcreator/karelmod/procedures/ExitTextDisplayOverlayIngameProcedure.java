package net.mcreator.karelmod.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.karelmod.block.GroundRedBlock;
import net.mcreator.karelmod.KarelModModElements;

import java.util.Map;

@KarelModModElements.ModElement.Tag
public class ExitTextDisplayOverlayIngameProcedure extends KarelModModElements.ModElement {
	public ExitTextDisplayOverlayIngameProcedure(KarelModModElements instance) {
		super(instance, 15);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure ExitTextDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure ExitTextDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure ExitTextDisplayOverlayIngame!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure ExitTextDisplayOverlayIngame!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
			world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO,
					(ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(), "me hola");
		}
		return (GroundRedBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) Math.abs((y - 1)), (int) z)))
				.getBlock());
	}
}
