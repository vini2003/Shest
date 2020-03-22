package shest.registry;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import shest.client.ShestContainerScreen;
import shest.common.ShestContainer;

public class ScreenRegistry {
	public static void initialize() {
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerRegistry.SHEST_BLOCK_CONTAINER, ShestContainerScreen::new);
	}
}