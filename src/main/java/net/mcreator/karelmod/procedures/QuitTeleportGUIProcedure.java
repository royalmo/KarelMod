package net.mcreator.karelmod.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.karelmod.KarelModModElements;

import java.util.Map;

@KarelModModElements.ModElement.Tag
public class QuitTeleportGUIProcedure extends KarelModModElements.ModElement {
	public QuitTeleportGUIProcedure(KarelModModElements instance) {
		super(instance, 20);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure QuitTeleportGUI!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
