package net.mcreator.karelmod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.gui.widget.TextFieldWidget;

import net.mcreator.karelmod.block.TeleporterBlock;
import net.mcreator.karelmod.KarelModModVariables;
import net.mcreator.karelmod.KarelModModElements;

import java.util.Map;
import java.util.HashMap;

@KarelModModElements.ModElement.Tag
public class CheckPortalCoordsProcedure extends KarelModModElements.ModElement {
	public CheckPortalCoordsProcedure(KarelModModElements instance) {
		super(instance, 19);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				System.err.println("Failed to load dependency guistate for procedure CheckPortalCoords!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CheckPortalCoords!");
			return;
		}
		HashMap guistate = (HashMap) dependencies.get("guistate");
		IWorld world = (IWorld) dependencies.get("world");
		if ((TeleporterBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_x");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())), (int) new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_x");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText())), (int) new Object() {
			int convert(String s) {
				try {
					return Integer.parseInt(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert((new Object() {
			public String getText() {
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_x");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText()))))).getBlock())) {
			KarelModModVariables.TeleportSetupLog = (String) "Changes applied!";
		} else {
			KarelModModVariables.TeleportSetupLog = (String) "New portal not found!";
		}
	}
}
