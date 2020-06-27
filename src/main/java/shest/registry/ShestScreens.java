package shest.registry;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import shest.client.ShestContainerScreen;

public class ShestScreens {
    public static void initialize() {
        ScreenProviderRegistry.INSTANCE.registerFactory(ShestContainer.SHEST_BLOCK_CONTAINER, ShestContainerScreen::new);
    }
}