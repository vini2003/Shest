package com.github.vini2003.shest;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.vini2003.shest.registry.BlockRegistry;
import com.github.vini2003.shest.registry.ContainerRegistry;
import com.github.vini2003.shest.registry.EntityRegistry;
import com.github.vini2003.shest.registry.ItemGroupRegistry;
import com.github.vini2003.shest.registry.ItemRegistry;

public class Shest implements ModInitializer {
	public static final String LOG_ID = "Shest";
	public static final String MOD_ID = LOG_ID.toLowerCase();
	public static Logger logger = LogManager.getLogger("com.github.vini2003.shest");

	@Override
	public void onInitialize() {
		ItemGroupRegistry.initialize();
		ItemRegistry.initialize();
		BlockRegistry.initialize();
		EntityRegistry.initialize();
		ContainerRegistry.initialize();
	}
}
