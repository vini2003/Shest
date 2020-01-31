package shest.common;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.brain.task.PickUpItemTask;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.util.math.BlockPos;
import shest.entity.ShestBlockEntity;
import spinnery.common.BaseContainer;
import spinnery.common.BaseInventory;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;
import spinnery.widget.WWidget;

public class ShestContainer extends BaseContainer {
	public static final int SHEST_INVENTORY = 1;

	ShestBlockEntity shestBlockEntity = null;

	public ShestContainer(int synchronizationID, PlayerInventory playerInventory, BlockPos shestPos, int x, int y, int m) {
		super(synchronizationID, playerInventory);

		shestBlockEntity = ((ShestBlockEntity) getLinkedWorld().getBlockEntity(shestPos));

		WInterface mainInterface = new WInterface(this);

		getHolder().add(mainInterface);

		getInventories().put(SHEST_INVENTORY, shestBlockEntity);

		shestBlockEntity.addListener(this::onContentChanged);

		WSlot.addPlayerInventory(mainInterface, PLAYER_INVENTORY);

		WSlot.addArray(mainInterface, 0, SHEST_INVENTORY, x, y);

		for (WWidget widget : mainInterface.getWidgets()) {
			if (widget instanceof WSlot && ((WSlot) widget).getInventoryNumber() == SHEST_INVENTORY) {
				((WSlot) widget).setOverrideMaximumCount(true);
				((WSlot) widget).setMaximumCount(m);
			}
		}
	}
}
