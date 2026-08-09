/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package app.vercel.macho_buck_webapp.hidden_house.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import app.vercel.macho_buck_webapp.hidden_house.block.OpenHiddenBlockBlock;
import app.vercel.macho_buck_webapp.hidden_house.block.HiddenBlockBlock;
import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class HiddenHouseModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, HiddenHouseMod.MODID);
	public static final RegistryObject<Block> HIDDEN_BLOCK;
	public static final RegistryObject<Block> OPEN_HIDDEN_BLOCK;
	static {
		HIDDEN_BLOCK = REGISTRY.register("hidden_block", HiddenBlockBlock::new);
		OPEN_HIDDEN_BLOCK = REGISTRY.register("open_hidden_block", OpenHiddenBlockBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.Block event) {
			HiddenBlockBlock.blockColorLoad(event);
			OpenHiddenBlockBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(RegisterColorHandlersEvent.Item event) {
			HiddenBlockBlock.itemColorLoad(event);
			OpenHiddenBlockBlock.itemColorLoad(event);
		}
	}
}