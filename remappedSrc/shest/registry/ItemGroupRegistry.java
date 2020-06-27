package com.github.vini2003.shest.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import com.github.vini2003.shest.Shest;

public class ItemGroupRegistry {
	public static ItemGroup SHEST_GROUP;

	public static void initialize() {
		SHEST_GROUP = FabricItemGroupBuilder.build(new Identifier(Shest.MOD_ID, "com.github.vini2003.shest"), () -> new ItemStack(BlockRegistry.EMERALD_SHEST_BLOCK));
	}
}