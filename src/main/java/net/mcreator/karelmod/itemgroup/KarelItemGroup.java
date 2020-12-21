
package net.mcreator.karelmod.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.karelmod.block.BeeperBlock;
import net.mcreator.karelmod.KarelModModElements;

@KarelModModElements.ModElement.Tag
public class KarelItemGroup extends KarelModModElements.ModElement {
	public KarelItemGroup(KarelModModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabkarel") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BeeperBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
