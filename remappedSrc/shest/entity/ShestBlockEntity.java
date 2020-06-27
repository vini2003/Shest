package shest.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import shest.block.ShestBlock;
import shest.inventory.ShestInventory;
import shest.registry.EntityRegistry;
import spinnery.common.BaseInventory;
import spinnery.util.InventoryUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShestBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Inventory, InventoryChangedListener {
	public int tier = 0;

	DefaultedList<ItemStack> stacks;

	List<InventoryChangedListener> listeners = new ArrayList<>();

	public ShestBlockEntity() {
		super(EntityRegistry.ENTITY_SHEST);
	}

	public void initialize(int tier) {
		this.tier = tier;
		this.stacks = DefaultedList.ofSize(ShestBlock.Manager.getSize(tier), ItemStack.EMPTY);
	}


	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		this.tier = tag.getInt("tier");
		initialize(tier);
		if (!tag.contains("inventory")) {
			InventoryUtilities.readUnsafe(this, tag);
		} else {
			BaseInventory inventory = InventoryUtilities.read(tag);
			this.stacks = DefaultedList.ofSize(inventory.getInvSize(), ItemStack.EMPTY);
			for (int i = 0; i < inventory.getInvSize(); ++i) {
				this.setInvStack(i, inventory.getInvStack(i));
			}
		}
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		InventoryUtilities.write(this, tag);
		tag.putInt("tier", tier);
		super.toTag(tag);
		return tag;
	}

	@Override
	public void fromClientTag(CompoundTag tag) {
		super.fromTag(tag);
		this.tier = tag.getInt("tier");
		initialize(tier);
		if (!tag.contains("inventory")) {
			InventoryUtilities.readUnsafe(this, tag);
		} else {
			BaseInventory inventory = InventoryUtilities.read(tag);
			this.stacks = DefaultedList.ofSize(inventory.getInvSize(), ItemStack.EMPTY);
			for (int i = 0; i < inventory.getInvSize(); ++i) {
				this.setInvStack(i, inventory.getInvStack(i));
			}
		}
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		InventoryUtilities.write(this, tag);
		tag.putInt("tier", tier);
		super.toTag(tag);
		return tag;
	}

	@Override
	public int getInvSize() {
		return stacks.size();
	}

	@Override
	public boolean isInvEmpty() {
		return stacks.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public ItemStack getInvStack(int slot) {
		return stacks.get(slot);
	}

	@Override
	public ItemStack takeInvStack(int slot, int amount) {
		markDirty();
		ItemStack stack = stacks.get(slot).split(amount);
		onInvChange(this);
		return stack;
	}

	@Override
	public ItemStack removeInvStack(int slot) {
		markDirty();
		ItemStack stack = stacks.remove(slot);
		onInvChange(this);
		return stack;
	}

	@Override
	public void setInvStack(int slot, ItemStack stack) {
		markDirty();
		stacks.set(slot, stack);
		onInvChange(this);
	}

	@Override
	public boolean canPlayerUseInv(PlayerEntity player) {
		return true;
	}

	@Override
	public void clear() {
		markDirty();
		stacks.clear();
	}

	@Override
	public void onInvChange(Inventory inventory) {
		if (world != null) {
			listeners.forEach(InventoryChangedListener -> InventoryChangedListener.onInvChange(inventory));
		}
	}

	public void addListener(InventoryChangedListener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}

	public void removeListener(InventoryChangedListener... listeners) {
		this.listeners.removeAll(Arrays.asList(listeners));
	}
}
