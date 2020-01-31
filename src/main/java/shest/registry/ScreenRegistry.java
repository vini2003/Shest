package shest.registry;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import shest.client.ShestContainerScreen;
import shest.common.ShestContainer;

public class ScreenRegistry {
	public static void initialize() {
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerRegistry.SHEST_BLOCK_CONTAINER,
				(id, identifier, player, buf) -> {
					BlockPos pos = buf.readBlockPos();
					int x = buf.readInt();
					int y = buf.readInt();
					int m = buf.readInt();
					Text text = buf.readText();
					return new ShestContainerScreen(text, new ShestContainer(id, player.inventory, pos, x, y, m), player, x, y, m);
				});
	}
}