
package net.mcreator.karelmod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import net.mcreator.karelmod.procedures.TeleportSetupThisGUIIsOpenedProcedure;
import net.mcreator.karelmod.procedures.QuitTeleportGUIProcedure;
import net.mcreator.karelmod.procedures.CheckPortalCoordsProcedure;
import net.mcreator.karelmod.KarelModModVariables;
import net.mcreator.karelmod.KarelModModElements;
import net.mcreator.karelmod.KarelModMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@KarelModModElements.ModElement.Tag
public class TeleportSetupGui extends KarelModModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public TeleportSetupGui(KarelModModElements instance) {
		super(instance, 11);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("teleport_setup"));
	}
	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;
		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(0);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				TeleportSetupThisGUIIsOpenedProcedure.executeProcedure($_dependencies);
			}
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entity;
		TextFieldWidget pos_x;
		TextFieldWidget pos_y;
		TextFieldWidget pos_z;
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 200;
			this.ySize = 147;
		}
		private static final ResourceLocation texture = new ResourceLocation("karel_mod:textures/teleport_setup.png");
		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
			pos_x.render(mouseX, mouseY, partialTicks);
			pos_y.render(mouseX, mouseY, partialTicks);
			pos_z.render(mouseX, mouseY, partialTicks);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.blit(k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			if (pos_x.isFocused())
				return pos_x.keyPressed(key, b, c);
			if (pos_y.isFocused())
				return pos_y.keyPressed(key, b, c);
			if (pos_z.isFocused())
				return pos_z.keyPressed(key, b, c);
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
			pos_x.tick();
			pos_y.tick();
			pos_z.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			this.font.drawString("KAREL TELEPORTATION PORTAL", 12, 7, -16777216);
			this.font.drawString("Teleport the robot to...", 12, 19, -12829636);
			this.font.drawString("" + (KarelModModVariables.TeleportSetupLog) + "", 11, 100, -12829636);
			this.font.drawString(" X = " + (int) (KarelModModVariables.TeleportLastX) + "", 126, 38, -12829636);
			this.font.drawString(" Y = " + (int) (KarelModModVariables.TeleportLastY) + "", 126, 60, -12829636);
			this.font.drawString(" Z = " + (int) (KarelModModVariables.TeleportLastZ) + "", 126, 81, -12829636);
		}

		@Override
		public void removed() {
			super.removed();
			Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);
			pos_x = new TextFieldWidget(this.font, this.guiLeft + 7, this.guiTop + 33, 120, 20, "Enter x") {
				{
					setSuggestion("Enter x");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Enter x");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Enter x");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:pos_x", pos_x);
			pos_x.setMaxStringLength(32767);
			this.children.add(this.pos_x);
			pos_y = new TextFieldWidget(this.font, this.guiLeft + 7, this.guiTop + 55, 120, 20, "Enter y") {
				{
					setSuggestion("Enter y");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Enter y");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Enter y");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:pos_y", pos_y);
			pos_y.setMaxStringLength(32767);
			this.children.add(this.pos_y);
			pos_z = new TextFieldWidget(this.font, this.guiLeft + 7, this.guiTop + 77, 120, 20, "Enter z") {
				{
					setSuggestion("Enter z");
				}
				@Override
				public void writeText(String text) {
					super.writeText(text);
					if (getText().isEmpty())
						setSuggestion("Enter z");
					else
						setSuggestion(null);
				}

				@Override
				public void setCursorPosition(int pos) {
					super.setCursorPosition(pos);
					if (getText().isEmpty())
						setSuggestion("Enter z");
					else
						setSuggestion(null);
				}
			};
			guistate.put("text:pos_z", pos_z);
			pos_z.setMaxStringLength(32767);
			this.children.add(this.pos_z);
			this.addButton(new Button(this.guiLeft + 7, this.guiTop + 117, 85, 20, "    Exit    ", e -> {
				KarelModMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
				handleButtonAction(entity, 0, x, y, z);
			}));
			this.addButton(new Button(this.guiLeft + 99, this.guiTop + 117, 90, 20, "    Apply    ", e -> {
				KarelModMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
				handleButtonAction(entity, 1, x, y, z);
			}));
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;
		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				QuitTeleportGUIProcedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 1) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("guistate", guistate);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				CheckPortalCoordsProcedure.executeProcedure($_dependencies);
			}
		}
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
