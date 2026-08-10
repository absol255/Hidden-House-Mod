package app.vercel.macho_buck_webapp.hidden_house.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;

import app.vercel.macho_buck_webapp.hidden_house.world.inventory.ReverseHiddenBlockGUIMenu;
import app.vercel.macho_buck_webapp.hidden_house.network.ReverseHiddenBlockGUIButtonMessage;
import app.vercel.macho_buck_webapp.hidden_house.init.HiddenHouseModScreens;
import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class ReverseHiddenBlockGUIScreen extends AbstractContainerScreen<ReverseHiddenBlockGUIMenu> implements HiddenHouseModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox code;
	private Button button_open;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("hidden_house:textures/screens/reverse_hidden_block_gui.png");

	public ReverseHiddenBlockGUIScreen(ReverseHiddenBlockGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("code"))
				code.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		code.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (code.isFocused())
			return code.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String codeValue = code.getValue();
		super.resize(minecraft, width, height);
		code.setValue(codeValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.hidden_house.reverse_hidden_block_gui.label_enter_code"), 60, 16, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		code = new EditBox(this.font, this.leftPos + 24, this.topPos + 52, 120, 20, Component.translatable("gui.hidden_house.reverse_hidden_block_gui.code"));
		code.setMaxLength(8192);
		code.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "code", content, false);
		});
		code.setHint(Component.translatable("gui.hidden_house.reverse_hidden_block_gui.code"));
		this.addWidget(this.code);
		button_open = Button.builder(Component.translatable("gui.hidden_house.reverse_hidden_block_gui.button_open"), e -> {
			int x = ReverseHiddenBlockGUIScreen.this.x;
			int y = ReverseHiddenBlockGUIScreen.this.y;
			if (true) {
				HiddenHouseMod.PACKET_HANDLER.sendToServer(new ReverseHiddenBlockGUIButtonMessage(0, x, y, z));
				ReverseHiddenBlockGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 60, this.topPos + 97, 50, 20).build();
		this.addRenderableWidget(button_open);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		code.tick();
	}
}