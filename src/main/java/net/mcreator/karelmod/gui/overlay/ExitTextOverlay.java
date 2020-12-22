
package net.mcreator.karelmod.gui.overlay;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.karelmod.procedures.ExitTextDisplayOverlayIngameProcedure;
import net.mcreator.karelmod.KarelModModElements;

import com.google.common.collect.ImmutableMap;

@KarelModModElements.ModElement.Tag
public class ExitTextOverlay extends KarelModModElements.ModElement {
	public ExitTextOverlay(KarelModModElements instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event) {
		if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int posX = (event.getWindow().getScaledWidth()) / 2;
			int posY = (event.getWindow().getScaledHeight()) / 2;
			PlayerEntity entity = Minecraft.getInstance().player;
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			if (ExitTextDisplayOverlayIngameProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world))) {
				Minecraft.getInstance().fontRenderer.drawString("Press G to exit", posX + -38, posY + 62, -1);
			}
		}
	}
}
