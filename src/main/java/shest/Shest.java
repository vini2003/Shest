package shest;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shest.registry.*;

public class Shest implements ModInitializer {
    public static final String LOG_ID = "Shest";
    public static final String MOD_ID = LOG_ID.toLowerCase();
    public static Logger logger = LogManager.getLogger("shest");

    @Override
    public void onInitialize() {
        ShestItemGroups.initialize();
        ShestItems.initialize();
        ShestBlocks.initialize();
        ShestEntities.initialize();
        ShestContainer.initialize();
    }
}
