package shest.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shest.Shest;
import shest.block.ShestBlock;
import shest.block.ShestBlockDiamond;
import shest.block.ShestBlockEmerald;
import shest.block.ShestBlockGold;
import shest.block.ShestBlockIron;

public class BlockRegistry {
	public static final Block WOOD_SHEST_BLOCK = register("wood_shest", new ShestBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(1.5F, 3.5F).build()), new Item.Settings().group(ItemGroupRegistry.SHEST_GROUP));
	public static final Block IRON_SHEST_BLOCK = register("iron_shest", new ShestBlockIron(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(1.5F, 3.5F).build()), new Item.Settings().group(ItemGroupRegistry.SHEST_GROUP));
	public static final Block GOLD_SHEST_BLOCK = register("gold_shest", new ShestBlockGold(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(1.5F, 3.5F).build()), new Item.Settings().group(ItemGroupRegistry.SHEST_GROUP));
	public static final Block DIAMOND_SHEST_BLOCK = register("diamond_shest", new ShestBlockDiamond(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(1.5F, 3.5F).build()), new Item.Settings().group(ItemGroupRegistry.SHEST_GROUP));
	public static final Block EMERALD_SHEST_BLOCK = register("emerald_shest", new ShestBlockEmerald(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(1.5F, 3.5F).build()), new Item.Settings().group(ItemGroupRegistry.SHEST_GROUP));

	public BlockRegistry() {
		// NO-OP
	}

	public static void initialize() {

	}

	static <T extends Block> T register(String name, RenderLayer renderLayer, T block, Item.Settings settings) {
		return register(name, renderLayer, block, new BlockItem(block, settings));
	}

	static <T extends Block> T register(String name, RenderLayer renderLayer, T block, BlockItem item) {
		BlockRenderLayerMap.INSTANCE.putBlock(block, renderLayer);
		return register(name, block, item);
	}

	static <T extends Block> T register(String name, T block, BlockItem item) {
		T b = Registry.register(Registry.BLOCK, new Identifier(Shest.MOD_ID, name), block);
		if (item != null) {
			ItemRegistry.register(name, item);
		}
		return b;
	}

	static <T extends Block> T register(String name, T block, Item.Settings settings) {
		return register(name, block, new BlockItem(block, settings));
	}
}