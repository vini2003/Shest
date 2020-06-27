package com.github.vini2003.shest.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import com.github.vini2003.shest.block.ShestBlock;
import com.github.vini2003.shest.registry.ShestEntities;
import spinnery.common.inventory.BaseInventory;
import spinnery.common.utility.InventoryUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShestBlockEntity extends BlockEntity implements BlockEntityClientSerializable, Inventory, InventoryChangedListener {
    public int tier = 0;

    DefaultedList<ItemStack> stacks;

    List<InventoryChangedListener> listeners = new ArrayList<>();

    public ShestBlockEntity() {
        super(ShestEntities.ENTITY_SHEST);
    }

    public void initialize(int tier) {
        this.tier = tier;
        this.stacks = DefaultedList.ofSize(ShestBlock.Manager.getSize(tier), ItemStack.EMPTY);
    }


    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.tier = tag.getInt("tier");
        initialize(tier);
        if (!tag.contains("inventory")) {
            InventoryUtilities.readUnsafe(this, tag);
        } else {
            BaseInventory inventory = InventoryUtilities.read(tag);
            this.stacks = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
            for (int i = 0; i < inventory.size(); ++i) {
                this.setStack(i, inventory.getStack(i));
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
        super.fromTag(null, tag);
        this.tier = tag.getInt("tier");
        initialize(tier);
        if (!tag.contains("inventory")) {
            InventoryUtilities.readUnsafe(this, tag);
        } else {
            BaseInventory inventory = InventoryUtilities.read(tag);
            this.stacks = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
            for (int i = 0; i < inventory.size(); ++i) {
                this.setStack(i, inventory.getStack(i));
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
    public int size() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        return stacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        markDirty();
        ItemStack stack = stacks.get(slot).split(amount);
        onInventoryChanged(this);
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        markDirty();
        ItemStack stack = stacks.remove(slot);
        onInventoryChanged(this);
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        markDirty();
        stacks.set(slot, stack);
        onInventoryChanged(this);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        markDirty();
        stacks.clear();
    }

    @Override
    public void onInventoryChanged(Inventory inventory) {
        if (world != null) {
            listeners.forEach(InventoryChangedListener -> InventoryChangedListener.onInventoryChanged(inventory));
        }
    }

    public void addListener(InventoryChangedListener... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    public void removeListener(InventoryChangedListener... listeners) {
        this.listeners.removeAll(Arrays.asList(listeners));
    }
}
