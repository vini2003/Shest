package com.github.vini2003.shest;

import net.fabricmc.api.ClientModInitializer;
import com.github.vini2003.shest.registry.ShestScreens;

public class ShestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ShestScreens.initialize();
    }
}
