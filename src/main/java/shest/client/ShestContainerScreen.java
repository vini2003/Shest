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
	public ShestContainerScreen(Text name, ShestContainer linkedContainer, PlayerEntity player, int x, int y, int m) {
		super(name, linkedContainer, player);

		WInterface mainInterface = getInterface();

		WPanel mainPanel = mainInterface.getFactory().build(WPanel.class, Position.of(mainInterface), x < 9 ? Size.of(9 * 18 + 8, y + 18 + 108) : Size.of(x * 18 + 8, y * 18 + 108)).setParent(mainInterface);

		mainPanel.setLabel(name);

		mainPanel.center();

		mainInterface.add(mainPanel);
		WSlot.addPlayerInventory(Position.of(mainPanel, ((mainPanel.getWidth()) / 2) - (int) (18 * 4.5f), y * 18 + 24, 0), Size.of(18, 18), mainInterface);

		if (x >= 9) {
			WSlot.addArray(Position.of(mainPanel, 4, 19, 0), Size.of(18, 18), mainInterface, 0, ShestContainer.SHEST_INVENTORY, x, y);
		} else {
			mainInterface.add(mainInterface.getFactory().build(WSlot.class, Position.of(mainPanel, mainPanel.getWidth() / 2 - 9, 19, 0), Size.of(18, 18)).setSlotNumber(0).setInventoryNumber(ShestContainer.SHEST_INVENTORY));
		}

		for (WAbstractWidget widget : mainInterface.getWidgets()) {
			if (widget instanceof WSlot && ((WSlot) widget).getInventoryNumber() == ShestContainer.SHEST_INVENTORY) {
				((WSlot) widget).setOverrideMaximumCount(true);
				((WSlot) widget).setMaximumCount(m);
			}
		}
	}
}
