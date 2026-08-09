/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package app.vercel.macho_buck_webapp.hidden_house.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;

import app.vercel.macho_buck_webapp.hidden_house.block.entity.OpenHiddenBlockBlockEntity;
import app.vercel.macho_buck_webapp.hidden_house.block.entity.HiddenBlockBlockEntity;
import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class HiddenHouseModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HiddenHouseMod.MODID);
	public static final RegistryObject<BlockEntityType<HiddenBlockBlockEntity>> HIDDEN_BLOCK = register("hidden_block", HiddenHouseModBlocks.HIDDEN_BLOCK, HiddenBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<OpenHiddenBlockBlockEntity>> OPEN_HIDDEN_BLOCK = register("open_hidden_block", HiddenHouseModBlocks.OPEN_HIDDEN_BLOCK, OpenHiddenBlockBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}