/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package app.vercel.macho_buck_webapp.hidden_house.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import app.vercel.macho_buck_webapp.hidden_house.client.gui.ReverseHiddenBlockSetCodeGUIScreen;
import app.vercel.macho_buck_webapp.hidden_house.client.gui.ReverseHiddenBlockGUIScreen;
import app.vercel.macho_buck_webapp.hidden_house.client.gui.HiddenBlockSetCodeGUIScreen;
import app.vercel.macho_buck_webapp.hidden_house.client.gui.HiddenBlockGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HiddenHouseModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(HiddenHouseModMenus.HIDDEN_BLOCK_GUI.get(), HiddenBlockGUIScreen::new);
			MenuScreens.register(HiddenHouseModMenus.HIDDEN_BLOCK_SET_CODE_GUI.get(), HiddenBlockSetCodeGUIScreen::new);
			MenuScreens.register(HiddenHouseModMenus.REVERSE_HIDDEN_BLOCK_GUI.get(), ReverseHiddenBlockGUIScreen::new);
			MenuScreens.register(HiddenHouseModMenus.REVERSE_HIDDEN_BLOCK_SET_CODE_GUI.get(), ReverseHiddenBlockSetCodeGUIScreen::new);
		});
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}