package shest.client;


import shest.common.ShestContainer;
import spinnery.client.screen.BaseContainerScreen;
import spinnery.widget.WAbstractWidget;
import spinnery.widget.WInterface;
import spinnery.widget.WPanel;
import spinnery.widget.WSlot;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

import static shest.common.ShestContainer.SHEST_INVENTORY;

public class ShestContainerScreen extends BaseContainerScreen<ShestContainer> {
    public ShestContainerScreen(ShestContainer container) {
        super(container.name, container, container.player);

        int x = container.x;
        int y = container.y;
        int m = container.m;

        WInterface mainInterface = getInterface();

        WPanel mainPanel = mainInterface.createChild(WPanel::new, Position.of(0, 0, 0), x < 9 ? Size.of(9 * 18 + 16, y + 18 + 108) : Size.of(x * 18 + 16, y * 18 + 108)).setParent(mainInterface);

        mainPanel.setLabel(container.name);

        mainPanel.setOnAlign(WAbstractWidget::center);

        mainPanel.center();

        mainInterface.add(mainPanel);

        WSlot.addPlayerInventory(Position.of(mainPanel, ((mainPanel.getWidth()) / 2) - (int) (18 * 4.5f), y * 18 + 24, 1), Size.of(18, 18), mainInterface);

        if (x >= 9) {
            WSlot.addArray(Position.of(mainPanel, 8, 19, 1), Size.of(18, 18), mainInterface, 0, SHEST_INVENTORY, x, y);
        } else {
            mainInterface.add(mainInterface.createChild(WSlot::new, Position.of(mainPanel, mainPanel.getWidth() / 2 - 9, 19, 1), Size.of(18, 18)).setSlotNumber(0).setInventoryNumber(SHEST_INVENTORY));
        }

        for (WAbstractWidget widget : mainInterface.getWidgets()) {
            if (!(widget instanceof WSlot)) continue;
            WSlot slot = (WSlot) widget;
            if (slot.getInventoryNumber() == SHEST_INVENTORY) {
                slot.setOverrideMaximumCount(true);
                slot.setMaximumCount(m);
            }
        }
    }
}
