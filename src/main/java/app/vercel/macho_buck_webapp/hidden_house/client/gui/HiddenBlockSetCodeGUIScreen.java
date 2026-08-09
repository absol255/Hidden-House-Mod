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

import app.vercel.macho_buck_webapp.hidden_house.world.inventory.HiddenBlockSetCodeGUIMenu;
import app.vercel.macho_buck_webapp.hidden_house.network.HiddenBlockSetCodeGUIButtonMessage;
import app.vercel.macho_buck_webapp.hidden_house.init.HiddenHouseModScreens;
import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class HiddenBlockSetCodeGUIScreen extends AbstractContainerScreen<HiddenBlockSetCodeGUIMenu> implements HiddenHouseModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private EditBox code;
	private Button button_set_code;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("hidden_house:textures/screens/hidden_block_set_code_gui.png");

	public HiddenBlockSetCodeGUIScreen(HiddenBlockSetCodeGUIMenu container, Inventory inventory, Component text) {
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
		guiGraphics.drawString(this.font, Component.translatable("gui.hidden_house.hidden_block_set_code_gui.label_set_code_to_unlock_it"), 69, 25, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		code = new EditBox(this.font, this.leftPos + 33, this.topPos + 61, 120, 20, Component.translatable("gui.hidden_house.hidden_block_set_code_gui.code"));
		code.setMaxLength(8192);
		code.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "code", content, false);
		});
		code.setHint(Component.translatable("gui.hidden_house.hidden_block_set_code_gui.code"));
		this.addWidget(this.code);
		button_set_code = Button.builder(Component.translatable("gui.hidden_house.hidden_block_set_code_gui.button_set_code"), e -> {
			int x = HiddenBlockSetCodeGUIScreen.this.x;
			int y = HiddenBlockSetCodeGUIScreen.this.y;
			if (true) {
				HiddenHouseMod.PACKET_HANDLER.sendToServer(new HiddenBlockSetCodeGUIButtonMessage(0, x, y, z));
				HiddenBlockSetCodeGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 60, this.topPos + 97, 65, 20).build();
		this.addRenderableWidget(button_set_code);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		code.tick();
	}
}