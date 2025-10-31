package net.zusz.zdonutmod.screen.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.zusz.zdonutmod.block.ModBlocks;
import net.zusz.zdonutmod.block.entity.MiniDonutMachineBlockEntity;
import net.zusz.zdonutmod.screen.ModMenuTypes;

import java.util.ArrayList;
import java.util.List;

public class MiniDonutMachineMenu extends AbstractContainerMenu {

    public final MiniDonutMachineBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;



    public MiniDonutMachineMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public MiniDonutMachineMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.MINI_DONUT_MACHINE_MENU.get(), pContainerId);
        this.blockEntity = ((MiniDonutMachineBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 0, 26, 16));//Input
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 1, 26, 34));//Oil In
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 2, 26, 52));//Oil Out

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 3, 80, 16));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 4, 80, 34));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 5, 80, 52));

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 6, 98, 16));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 7, 98, 34));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 8, 98, 52));

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 9, 116, 16));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 10, 116, 34));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 11, 116, 52));

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 12, 134, 16));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 13, 134, 34));
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 14, 134, 52));


        addDataSlots(data);

        //System.out.println(blockEntity.itemHandler.getSlots());
    }
    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int arrowPixelSize = 24;

        return maxProgress != 0 && progress!= 0 ? progress * arrowPixelSize / maxProgress : 0;
    }


    private static final int INPUT_SLOT = 0;
    private static final int OIL_SLOT = 1;
    private static final int OIL_OUTPUT_SLOT = 2;

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    //amount of slots!!!
    private static final int TE_INVENTORY_SLOT_COUNT = 15;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // Moving from player to machine
            if (isValidForInputSlot(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + INPUT_SLOT, TE_INVENTORY_FIRST_SLOT_INDEX + INPUT_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isValidForOilSlot(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + OIL_SLOT, TE_INVENTORY_FIRST_SLOT_INDEX + OIL_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                return ItemStack.EMPTY;
            }

        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // Moving from machine to player
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slot index: " + index);
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }



    private boolean isValidForInputSlot(ItemStack sourceStack) {
        List<Item> validItems = new ArrayList<>(List.of(
            Items.WHEAT
        ));

        return validItems.contains(sourceStack.getItem());
    }
    private boolean isValidForOilSlot(ItemStack sourceStack) {
        List<Item> validItems = new ArrayList<>(List.of(
                Items.HONEY_BOTTLE //Change later when oil is added!!!
        ));

        return validItems.contains(sourceStack.getItem());
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.MINI_DONUT_MACHINE.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public ItemStackHandler getItemHandler() {
        //System.out.println(blockEntity.itemHandler.getSlots());
        return blockEntity.itemHandler;
    }
}
