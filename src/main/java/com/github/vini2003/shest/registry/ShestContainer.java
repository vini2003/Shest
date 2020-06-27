package com.github.vini2003.shest.registry;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;

public class ShestContainer {
    public static final Identifier SHEST_BLOCK_CONTAINER = new Identifier("com/github/vini2003/shest", "com/github/vini2003/shest");

    public static void initialize() {
        ContainerProviderRegistry.INSTANCE.registerFactory(SHEST_BLOCK_CONTAINER,
                (syncId, id, player, buffer) -> new com.github.vini2003.shest.common.ShestContainer(syncId, buffer.readText(), player.inventory, buffer.readBlockPos(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
    }
}