package net.mcreator.karelmod.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.block.BlockState;

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
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure CheckPortalCoords!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure CheckPortalCoords!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure CheckPortalCoords!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure CheckPortalCoords!");
			return;
		}
		HashMap guistate = (HashMap) dependencies.get("guistate");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
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
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_y");
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
				TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_z");
				if (textField != null) {
					return textField.getText();
				}
				return "";
			}
		}.getText()))))).getBlock())) {
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("objective_x", new Object() {
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
					}.getText())));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("objective_y", new Object() {
						int convert(String s) {
							try {
								return Integer.parseInt(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert((new Object() {
						public String getText() {
							TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_y");
							if (textField != null) {
								return textField.getText();
							}
							return "";
						}
					}.getText())));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("objective_z", new Object() {
						int convert(String s) {
							try {
								return Integer.parseInt(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert((new Object() {
						public String getText() {
							TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_z");
							if (textField != null) {
								return textField.getText();
							}
							return "";
						}
					}.getText())));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			KarelModModVariables.TeleportLastX = (double) new Object() {
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
			}.getText()));
			KarelModModVariables.TeleportLastY = (double) new Object() {
				int convert(String s) {
					try {
						return Integer.parseInt(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert((new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_y");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText()));
			KarelModModVariables.TeleportLastZ = (double) new Object() {
				int convert(String s) {
					try {
						return Integer.parseInt(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert((new Object() {
				public String getText() {
					TextFieldWidget textField = (TextFieldWidget) guistate.get("text:pos_z");
					if (textField != null) {
						return textField.getText();
					}
					return "";
				}
			}.getText()));
			KarelModModVariables.TeleportSetupLog = (String) "Changes applied!";
		} else {
			KarelModModVariables.TeleportSetupLog = (String) "New portal not found!";
		}
	}
}
