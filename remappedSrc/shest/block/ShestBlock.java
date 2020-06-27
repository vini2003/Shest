package shest.block;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import shest.entity.ShestBlockEntity;
import shest.registry.BlockRegistry;
import shest.registry.ContainerRegistry;

public class ShestBlock extends Block implements BlockEntityProvider {
	public ShestBlockEntity shestBlockEntity;
	public int tier;

	public ShestBlock(int tier) {
		super(BlockRegistry.SHEST_BLOCK_SETTINGS);
		this.tier = tier;
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		shestBlockEntity = new ShestBlockEntity();
		shestBlockEntity.initialize(tier);
		return shestBlockEntity;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			ContainerProviderRegistry.INSTANCE.openContainer(ContainerRegistry.SHEST_BLOCK_CONTAINER, player, (buffer) -> {
				buffer.writeText(new TranslatableText(this.getTranslationKey()));
				buffer.writeBlockPos(pos);
				buffer.writeInt(Manager.getWidth(tier));
				buffer.writeInt(Manager.getHeight(tier));
				buffer.writeInt(Manager.getMaximum(tier));
			});
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			ShestBlockEntity blockEntity = (ShestBlockEntity) world.getBlockEntity(pos);

			for (int i = 0; i < blockEntity.getInvSize(); ++i) {
				ItemStack stackB = blockEntity.getInvStack(i).copy();

				do {
					int intA = Math.min(stackB.getCount(), stackB.getMaxCount());

					ItemStack stackC = stackB.copy();

					stackC.setCount(intA);
					stackB.decrement(intA);

					ItemScatterer.spawn(world, pos, DefaultedList.copyOf(ItemStack.EMPTY, stackC.copy()));
				} while (!stackB.isEmpty());
			}

			super.onBlockRemoved(state, world, pos, newState, moved);
		}
	}

	public static class Manager {
		public static int getWidth(int tier) {
			switch (tier) {
				case -1:
					return 1;
				case 0:
					return 9;
				case 1:
					return 9;
				case 2:
					return 12;
				case 3:
					return 15;
				case 4:
					return 18;
				default:
					return 0;
			}
		}

		public static int getHeight(int tier) {
			switch (tier) {
				case -1:
					return 1;
				case 0:
					return 3;
				case 1:
					return 6;
				case 2:
					return 6;
				case 3:
					return 6;
				case 4:
					return 6;
				default:
					return 0;
			}
		}

		public static int getSize(int tier) {
			return getWidth(tier) * getHeight(tier);
		}

		public static int getMaximum(int tier) {
			switch (tier) {
				default:
					return 256;
			}
		}
	}
}
