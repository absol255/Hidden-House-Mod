/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package app.vercel.macho_buck_webapp.hidden_house.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class HiddenHouseModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, HiddenHouseMod.MODID);
	public static final RegistryObject<Item> HIDDEN_BLOCK;
	public static final RegistryObject<Item> OPEN_HIDDEN_BLOCK;
	public static final RegistryObject<Item> REVERSE_HIDDEN_BLOCK;
	public static final RegistryObject<Item> REVERSE_OPEN_HIDDEN_BLOCK;
	static {
		HIDDEN_BLOCK = block(HiddenHouseModBlocks.HIDDEN_BLOCK, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant());
		OPEN_HIDDEN_BLOCK = block(HiddenHouseModBlocks.OPEN_HIDDEN_BLOCK, new Item.Properties().rarity(Rarity.EPIC).fireResistant());
		REVERSE_HIDDEN_BLOCK = block(HiddenHouseModBlocks.REVERSE_HIDDEN_BLOCK, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant());
		REVERSE_OPEN_HIDDEN_BLOCK = block(HiddenHouseModBlocks.REVERSE_OPEN_HIDDEN_BLOCK, new Item.Properties().rarity(Rarity.EPIC).fireResistant());
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}