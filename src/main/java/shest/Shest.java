package shest;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shest.registry.BlockRegistry;
import shest.registry.ContainerRegistry;
import shest.registry.EntityRegistry;
import shest.registry.ItemGroupRegistry;
import shest.registry.ItemRegistry;

public class Shest implements ModInitializer {
	public static final String LOG_ID = "Shest";
	public static final String MOD_ID = LOG_ID.toLowerCase();
	public static Logger logger = LogManager.getLogger("shest");

	@Override
	public void onInitialize() {
		ItemGroupRegistry.initialize();
		ItemRegistry.initialize();
		BlockRegistry.initialize();
		EntityRegistry.initialize();
		ContainerRegistry.initialize();
	}
}
