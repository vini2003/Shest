package shest.block;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import shest.entity.ShestBlockEntity;
import shest.registry.ContainerRegistry;

public class ShestBlock extends Block implements BlockEntityProvider {
	public ShestBlockEntity shestBlockEntity;
	public int tier = 0;

	public ShestBlock(Settings settings, int tier) {
		super(settings);
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
			ContainerProviderRegistry.INSTANCE.openContainer(ContainerRegistry.SHEST_BLOCK_CONTAINER, player, (buffer) -> { buffer.writeBlockPos(pos); buffer.writeInt(Manager.getWidth(tier)); buffer.writeInt(Manager.getHeight(tier)); buffer.writeInt(Manager.getMaximum(tier)); buffer.writeText(new TranslatableText(this.getTranslationKey())); });
		}
		return ActionResult.SUCCESS;
	}


	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
		if (!player.isCreative()) {
			for (int i = 0; i <  ((ShestBlockEntity) blockEntity).getInvSize(); ++i) {
				ItemStack stackB = ((ShestBlockEntity) blockEntity).getInvStack(i).copy();

				do {
					int intA = Math.min(stackB.getCount(), stackB.getMaxCount());

					ItemStack stackC = stackB.copy();
					stackC.setCount(intA);
					stackB.decrement(intA);
					Block.dropStack(world, pos, stackC.copy());
				} while (!stackB.isEmpty());
			}

			Block.dropStack(world, pos, new ItemStack(this.asItem()));
		}

	}

	public static class Manager {
		public static int getWidth(int tier) {
			switch (tier) {
				case -1:
					return 1;
				case 3:
					return 12;
				case 4:
					return 15;
				default:
					return 9;
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
				default:
					return 9;
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
