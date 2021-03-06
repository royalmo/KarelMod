
package net.mcreator.karelmod.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.karelmod.itemgroup.KarelItemGroup;
import net.mcreator.karelmod.KarelModModElements;

import java.util.List;
import java.util.Collections;

@KarelModModElements.ModElement.Tag
public class UnbreakableWall3Block extends KarelModModElements.ModElement {
	@ObjectHolder("karel_mod:unbreakable_wall_3")
	public static final Block block = null;
	public UnbreakableWall3Block(KarelModModElements instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(KarelItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(-1, 3600000).lightValue(0).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("unbreakable_wall_3");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
