package shest;

import net.fabricmc.api.ClientModInitializer;
import shest.registry.ShestScreens;

public class ShestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ShestScreens.initialize();
    }
}
