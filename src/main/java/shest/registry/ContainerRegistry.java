package shest.registry;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;
import shest.common.ShestContainer;

public class ContainerRegistry {
	public static final Identifier SHEST_BLOCK_CONTAINER = new Identifier("shest", "shest");

	public static void initialize() {
		ContainerProviderRegistry.INSTANCE.registerFactory(SHEST_BLOCK_CONTAINER,
				(syncId, id, player, buffer) -> new ShestContainer(syncId, buffer.readText(), player.inventory, buffer.readBlockPos(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	}
}