package shest.registry;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import shest.Shest;

public class ShestItemGroups {
    public static ItemGroup SHEST_GROUP;

    public static void initialize() {
        SHEST_GROUP = FabricItemGroupBuilder.build(new Identifier(Shest.MOD_ID, "shest"), () -> new ItemStack(ShestBlocks.EMERALD_SHEST_BLOCK));
    }
}