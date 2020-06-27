package com.github.vini2003.shest.registry;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import com.github.vini2003.shest.client.ShestContainerScreen;

public class ShestScreens {
    public static void initialize() {
        ScreenProviderRegistry.INSTANCE.registerFactory(ShestContainer.SHEST_BLOCK_CONTAINER, ShestContainerScreen::new);
    }
}