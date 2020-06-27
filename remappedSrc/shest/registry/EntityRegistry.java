package com.github.vini2003.shest.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import com.github.vini2003.shest.Shest;
import com.github.vini2003.shest.entity.ShestBlockEntity;

import java.util.function.Supplier;

public class EntityRegistry {
	public static final BlockEntityType<ShestBlockEntity> ENTITY_SHEST = register(
			"com.github.vini2003.shest",
			ShestBlockEntity::new,
			BlockRegistry.DIRT_SHEST_BLOCK,
			BlockRegistry.WOOD_SHEST_BLOCK,
            BlockRegistry.IRON_SHEST_BLOCK,
            BlockRegistry.GOLD_SHEST_BLOCK,
            BlockRegistry.DIAMOND_SHEST_BLOCK,
            BlockRegistry.EMERALD_SHEST_BLOCK
	);

	public static void initialize() {
	}

	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Shest.MOD_ID, name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}
}