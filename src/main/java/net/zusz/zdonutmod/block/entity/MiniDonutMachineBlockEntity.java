package net.zusz.zdonutmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zusz.zdonutmod.block.custom.MiniDonutMachineBlock;
import net.zusz.zdonutmod.item.ModItems;
import net.zusz.zdonutmod.screen.custom.MiniDonutMachineMenu;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MiniDonutMachineBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(19) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 4);
            }
        }

    };



    private static final int INPUT_SLOT = 0;
    private static final int OIL_SLOT = 1;
    private static final int OIL_OUTPUT_SLOT = 2;
    private static final List<Integer> ROW_1 = new ArrayList<>(List.of(3, 4, 5, 6));
    private static final List<Integer> ROW_2 = new ArrayList<>(List.of(7, 8, 9, 10));
    private static final List<Integer> ROW_3 = new ArrayList<>(List.of(11, 12, 13, 14));
    private static final List<Integer> ROW_4 = new ArrayList<>(List.of(15, 16, 17, 18));




    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 300;



    public MiniDonutMachineBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MINI_DONUT_MACHINE_BE.get(), pos, blockState);

        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> MiniDonutMachineBlockEntity.this.progress;
                    case 1 -> MiniDonutMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: MiniDonutMachineBlockEntity.this.progress = value;
                    case 1: MiniDonutMachineBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }



    @Override
    public Component getDisplayName() {
        return Component.translatable("block.zcoffeecraft2.coffee_machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MiniDonutMachineMenu(i, inventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("mini_donut_machine.progress", progress);
        pTag.putInt("mini_donut_machine.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("mini_donut_machine.progress");
        maxProgress = pTag.getInt("mini_donut_machine.max_progress");

    }


    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if (hasIngredients() || !isRowEmpty(1) || !isRowEmpty(2) || !isRowEmpty(3)) {
            increaseCraftingProgress();
        } else {
            resetProgress();
        }

        if (hasCraftingFinished()) {
            for (int slot : ROW_3) {
                progressSlot(slot);
            }
            for (int slot : ROW_2) {
                progressSlot(slot);
            }
            for (int slot : ROW_1) {
                progressSlot(slot);
            }
            if (hasIngredients()) {
                progressSlot(0);
            }
            resetProgress();
        }
    }

    private void progressSlot(int slotId) {
        if (ROW_1.contains(slotId)) {
            if(itemHandler.getStackInSlot(slotId).getItem() == ModItems.RAW_MINI_DONUT.asItem()) {
                ItemStack stack = new ItemStack(ModItems.RAW_MINI_DONUT.asItem());
                if (isSlotEmpty(slotId + 4)) {
                    itemHandler.extractItem(slotId, 1, false);
                    itemHandler.setStackInSlot(slotId + 4, stack);
                }
            }
        }
        if (ROW_2.contains(slotId)) {
            if(itemHandler.getStackInSlot(slotId).getItem() == ModItems.RAW_MINI_DONUT.asItem()) {
                ItemStack stack = new ItemStack(ModItems.MINI_DONUT.asItem());
                if (isSlotEmpty(slotId + 4)) {
                    itemHandler.extractItem(slotId, 1, false);
                    itemHandler.setStackInSlot(slotId + 4, stack);
                }
            }
        }
        if (ROW_3.contains(slotId)) {
            if(itemHandler.getStackInSlot(slotId).getItem() == ModItems.MINI_DONUT.asItem()) {
                if (canInsertAmountIntoRow(4, 1)) {
                    ItemStack stack = new ItemStack (ModItems.MINI_DONUT.asItem(), itemHandler.getStackInSlot(slotId + 4).getCount() + 1);
                    System.out.println(itemHandler.getStackInSlot(slotId + 4).getCount() + 1);
                    System.out.println(itemHandler.getStackInSlot(slotId + 4));
                    itemHandler.extractItem(slotId, 1, false);
                    itemHandler.setStackInSlot(slotId + 4, stack);
                }
            }
        }
        if (slotId == 0) {
            if (isRowEmpty(1)) {
                itemHandler.extractItem(0, 1, false);
                for (int slot : ROW_1) {
                    itemHandler.setStackInSlot(slot, new ItemStack(ModItems.RAW_MINI_DONUT.asItem()));
                }
            }
        }
    }

    private boolean isRowEmpty(int row) {
        if (row == 1 || row == 2 || row == 3) {
            int startSlot = (row * 4) - 1;
            return (itemHandler.getStackInSlot(startSlot).isEmpty() && itemHandler.getStackInSlot(startSlot + 1).isEmpty() && itemHandler.getStackInSlot(startSlot + 2).isEmpty() && itemHandler.getStackInSlot(startSlot + 3).isEmpty());
        }
        return false;
    }

    private boolean hasIngredients() {
        return (itemHandler.getStackInSlot(0).getItem() == Items.WHEAT);
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 300;
    }
    private boolean hasCraftingFinished() {
        return progress >= this.maxProgress;
    }
    private void increaseCraftingProgress() {
        progress++;
    }


    private boolean canInsertItemIntoSlot(ItemStack output, int slotid) {

        return itemHandler.getStackInSlot(slotid).isEmpty() ||
                itemHandler.getStackInSlot(slotid).getItem() == output.getItem();
    }
    private boolean canInsertAmountIntoRow(int row, int amount) {
        if (row == 1 || row == 2 || row == 3 || row == 4) {
            int startSlot = (row * 4) - 1;
            return (canInsertAmountIntoSlot(amount, startSlot) &&
                    canInsertAmountIntoSlot(amount, startSlot + 1) &&
                    canInsertAmountIntoSlot(amount, startSlot + 2) &&
                    canInsertAmountIntoSlot(amount, startSlot + 3));
        }
        return false;
    }

    private boolean canInsertAmountIntoSlot(int count, int slotid) {
        int maxCount = itemHandler.getStackInSlot(slotid).isEmpty() ? 64 : itemHandler.getStackInSlot(slotid).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(slotid).getCount();

        return maxCount >= currentCount + count;
    }
    private boolean isSlotEmpty(int slotId) {
        return itemHandler.getStackInSlot(slotId).isEmpty();
    }




    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    private float rotation;
    public float getRenderingRotation() {
        if (level == null) return 0f;

        Direction facing = getBlockState().getValue(MiniDonutMachineBlock.FACING);

        return switch (facing) {
            case NORTH -> 0f;
            case SOUTH -> 180f;
            case WEST  -> 90f;
            case EAST  -> -90f;
            default -> 0f;

        };
    }
}
