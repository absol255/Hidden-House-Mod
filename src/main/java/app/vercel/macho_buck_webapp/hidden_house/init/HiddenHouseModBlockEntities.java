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

import app.vercel.macho_buck_webapp.hidden_house.block.entity.ReverseOpenHiddenBlockBlockEntity;
import app.vercel.macho_buck_webapp.hidden_house.block.entity.ReverseHiddenBlockBlockEntity;
import app.vercel.macho_buck_webapp.hidden_house.HiddenHouseMod;

public class HiddenHouseModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HiddenHouseMod.MODID);
	public static final RegistryObject<BlockEntityType<ReverseHiddenBlockBlockEntity>> REVERSE_HIDDEN_BLOCK = register("reverse_hidden_block", HiddenHouseModBlocks.REVERSE_HIDDEN_BLOCK, ReverseHiddenBlockBlockEntity::new);
	public static final RegistryObject<BlockEntityType<ReverseOpenHiddenBlockBlockEntity>> REVERSE_OPEN_HIDDEN_BLOCK = register("reverse_open_hidden_block", HiddenHouseModBlocks.REVERSE_OPEN_HIDDEN_BLOCK, ReverseOpenHiddenBlockBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}