package net.mcreator.karelmod.procedures;

import net.mcreator.karelmod.KarelModModVariables;
import net.mcreator.karelmod.KarelModModElements;

import java.util.Map;

@KarelModModElements.ModElement.Tag
public class TeleportSetupThisGUIIsOpenedProcedure extends KarelModModElements.ModElement {
	public TeleportSetupThisGUIIsOpenedProcedure(KarelModModElements instance) {
		super(instance, 18);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		KarelModModVariables.TeleportSetupLog = (String) "No errors!";
	}
}
