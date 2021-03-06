package shest.inventory;

import net.minecraft.item.ItemStack;
import spinnery.common.BaseInventory;

public class ShestInventory extends BaseInventory {
	public ShestInventory(int size) {
		super(size);
	}

	public ShestInventory(ItemStack... items) {
		super(items);
	}

	@Override
	public ItemStack getInvStack(int slot) {
		return slot >= 0 && slot < this.stacks.size() ? this.stacks.get(slot) : ItemStack.EMPTY;
	}
}
