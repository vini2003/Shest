package shest.registry;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import shest.Shest;

public class ShestItems {
    public static final Item.Settings SHEST_ITEM_SETTINGS = new Item.Settings().group(ShestItemGroups.SHEST_GROUP);

    public ShestItems() {
        // NO-OP
    }

    public static void initialize() {

    }

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(Shest.MOD_ID, name), item);
    }
}