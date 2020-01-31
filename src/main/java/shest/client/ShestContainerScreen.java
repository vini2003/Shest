package shest.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import shest.common.ShestContainer;
import spinnery.common.BaseContainer;
import spinnery.common.BaseContainerScreen;
import spinnery.widget.WInterface;
import spinnery.widget.WPosition;
import spinnery.widget.WSize;
import spinnery.widget.WSlot;
import spinnery.widget.WType;
import spinnery.widget.WWidget;

public class ShestContainerScreen extends BaseContainerScreen<ShestContainer> {
	public ShestContainerScreen(Text name, ShestContainer linkedContainer, PlayerEntity player, int x, int y, int m) {
		super(name, linkedContainer, player);

		WInterface mainInterface = new WInterface(WPosition.of(WType.FREE, 0, 0, 0), WSize.of(x * 18 + 8, y * 18 + 108), linkedContainer);

		mainInterface.setLabel(name);

		mainInterface.center();

		getHolder().add(mainInterface);

		WSlot.addArray(WPosition.of(WType.ANCHORED, 4, 19, 0, mainInterface), WSize.of(18, 18), mainInterface, 0, ShestContainer.SHEST_INVENTORY, x, y);

		WSlot.addArray(WPosition.of(WType.ANCHORED, ((x * 9 + 4) - (9 * 9)), y * 18 + 24 + 4 + 54, 0, mainInterface), WSize.of(18, 18), mainInterface, 0, ShestContainer.PLAYER_INVENTORY, 9, 1);

		WSlot.addArray(WPosition.of(WType.ANCHORED, ((x * 9 + 4) - (9 * 9)), y * 18 + 24, 0, mainInterface), WSize.of(18, 18), mainInterface, 9, ShestContainer.PLAYER_INVENTORY, 9, 3);

		for (WWidget widget : mainInterface.getWidgets()) {
			if (widget instanceof WSlot && ((WSlot) widget).getInventoryNumber() == ShestContainer.SHEST_INVENTORY) {
				((WSlot) widget).setOverrideMaximumCount(true);
				((WSlot) widget).setMaximumCount(m);
			}
		}
	}
}
