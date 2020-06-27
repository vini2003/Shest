package com.github.vini2003.shest;

import net.fabricmc.api.ClientModInitializer;
import com.github.vini2003.shest.registry.ScreenRegistry;

public class ShestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenRegistry.initialize();
	}
}
