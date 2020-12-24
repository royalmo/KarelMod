package net.mcreator.karelmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.karelmod.block.NormalWallBlock;
import net.mcreator.karelmod.block.BeeperBlock;
import net.mcreator.karelmod.KarelModModElements;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@KarelModModElements.ModElement.Tag
public class CreateNukeProcedure extends KarelModModElements.ModElement {
	public CreateNukeProcedure(KarelModModElements instance) {
		super(instance, 35);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure CreateNuke!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure CreateNuke!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure CreateNuke!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CreateNuke!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double l_x = 0;
		double l_z = 0;
		double l_y = 0;
		world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(ParticleTypes.EXPLOSION, x, y, z, (int) 25, 5, 5, 5, 1);
		}
		if (!world.getWorld().isRemote) {
			world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		} else {
			world.getWorld().playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.explode")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
		}
		if (world instanceof ServerWorld)
			((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world.getWorld(), (int) x, (int) y, (int) z, true));
		{
			List<Entity> _entfound = world
					.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (20 / 2d), y - (20 / 2d), z - (20 / 2d), x + (20 / 2d), y + (20 / 2d), z + (20 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				entityiterator.attackEntityFrom(DamageSource.MAGIC, (float) 5);
			}
		}
		l_x = (double) (x - 7);
		l_z = (double) (z - 7);
		l_y = (double) y;
		for (int index0 = 0; index0 < (int) (2); index0++) {
			l_x = (double) (x - 7);
			for (int index1 = 0; index1 < (int) (15); index1++) {
				l_z = (double) (z - 7);
				for (int index2 = 0; index2 < (int) (15); index2++) {
					if ((((world.getBlockState(new BlockPos((int) (l_x), (int) (l_y), (int) (l_z)))).getBlock() == NormalWallBlock.block
							.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) (l_x), (int) (l_y), (int) (l_z)))).getBlock() == BeeperBlock.block
									.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) (l_x), (int) (l_y), (int) (l_z)), Blocks.AIR.getDefaultState(), 3);
					}
					l_z = (double) ((l_z) + 1);
				}
				l_x = (double) ((l_x) + 1);
			}
			l_y = (double) ((l_y) + 1);
		}
	}
}
