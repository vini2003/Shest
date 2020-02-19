package shest.client;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import shest.common.ShestContainer;
import spinnery.common.BaseContainerScreen;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WInterface;
import spinnery.widget.WPanel;
import spinnery.widget.WSlot;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

public class ShestContainerScreen extends BaseContainerScreen<ShestContainer> {
	public ShestContainerScreen(ShestContainer container) {
		// Text name, ShestContainer linkedContainer, PlayerEntity player, int x, int y, int m
		super(container.name, container, container.player);

		int x = container.x;
		int y = container.y;
		int m = container.m;

		WInterface mainInterface = getInterface();

		WPanel mainPanel = mainInterface.createChild(WPanel.class, Position.of(0, 0, 0), x < 9 ? Size.of(9 * 18 + 8, y + 18 + 108) : Size.of(x * 18 + 8, y * 18 + 108)).setParent(mainInterface);

		mainPanel.setLabel(container.name);

		mainPanel.setOnAlign(WAbstractWidget::center);

		mainPanel.center();

		mainInterface.add(mainPanel);

		WSlot.addPlayerInventory(Position.of(mainPanel, ((mainPanel.getWidth()) / 2) - (int) (18 * 4.5f), y * 18 + 24, 1), Size.of(18, 18), mainInterface);

		if (x >= 9) {
			WSlot.addArray(Position.of(mainPanel, 4, 19, 1), Size.of(18, 18), mainInterface, 0, ShestContainer.SHEST_INVENTORY, x, y);
		} else {
			mainInterface.add(mainInterface.createChild(WSlot.class, Position.of(mainPanel, mainPanel.getWidth() / 2 - 9, 19, 1), Size.of(18, 18)).setSlotNumber(0).setInventoryNumber(ShestContainer.SHEST_INVENTORY));
		}

		for (WAbstractWidget widget : mainInterface.getWidgets()) {
			if (widget instanceof WSlot && ((WSlot) widget).getInventoryNumber() == ShestContainer.SHEST_INVENTORY) {
				((WSlot) widget).setOverrideMaximumCount(true);
				((WSlot) widget).setMaximumCount(m);
			}
		}
	}
}
