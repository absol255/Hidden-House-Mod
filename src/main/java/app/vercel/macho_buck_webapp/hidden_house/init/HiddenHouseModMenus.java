/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package app.vercel.macho_buck_webapp.hidden_house.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import java.util.Map;

import app.vercel.macho_buck_webapp.hidden_house.world.inventory.ReverseHiddenBlockSetCodeGUIMenu;
import app.vercel.macho_buck_webapp.hidden_house.world.inventory.ReverseHiddenBlockGUIMenu;
import app.vercel.macho_buck_webapp.hidden_house.world.inventory.HiddenBlockSetCodeGUIMenu;
import app.vercel.macho_buck_webapp.hidden_house.world.inventory.HiddenBlockGUIMenu;
import app.vercel.macho_buck_webapp.hidden_house.network.MenuStateUpdateMessage;
import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class HiddenHouseModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, HiddenHouseMod.MODID);
	public static final RegistryObject<MenuType<HiddenBlockGUIMenu>> HIDDEN_BLOCK_GUI = REGISTRY.register("hidden_block_gui", () -> IForgeMenuType.create(HiddenBlockGUIMenu::new));
	public static final RegistryObject<MenuType<HiddenBlockSetCodeGUIMenu>> HIDDEN_BLOCK_SET_CODE_GUI = REGISTRY.register("hidden_block_set_code_gui", () -> IForgeMenuType.create(HiddenBlockSetCodeGUIMenu::new));
	public static final RegistryObject<MenuType<ReverseHiddenBlockGUIMenu>> REVERSE_HIDDEN_BLOCK_GUI = REGISTRY.register("reverse_hidden_block_gui", () -> IForgeMenuType.create(ReverseHiddenBlockGUIMenu::new));
	public static final RegistryObject<MenuType<ReverseHiddenBlockSetCodeGUIMenu>> REVERSE_HIDDEN_BLOCK_SET_CODE_GUI = REGISTRY.register("reverse_hidden_block_set_code_gui", () -> IForgeMenuType.create(ReverseHiddenBlockSetCodeGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				HiddenHouseMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof HiddenHouseModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				HiddenHouseMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}