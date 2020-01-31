package shest;

import net.fabricmc.api.ClientModInitializer;
import shest.registry.ScreenRegistry;

public class ShestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenRegistry.initialize();
	}
}
