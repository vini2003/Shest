package shest.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shest.Shest;
import shest.entity.ShestBlockEntity;

import java.util.function.Supplier;

public class EntityRegistry {
	public static final BlockEntityType<ShestBlockEntity> ENTITY_SHEST = register(
			"shest",
			ShestBlockEntity::new,
			BlockRegistry.WOOD_SHEST_BLOCK
	);

	public static void initialize() {
	}

	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Shest.MOD_ID, name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null));
	}
}