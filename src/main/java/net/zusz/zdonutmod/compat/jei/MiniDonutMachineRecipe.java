package net.zusz.zdonutmod.compat.jei;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class MiniDonutMachineRecipe {
    private final Ingredient input;
    private final Ingredient oil;
    private final ItemStack output;
    private final ItemStack row1item1;
    private final ItemStack row1item2;
    private final ItemStack row1item3;
    private final ItemStack row2item1;
    private final ItemStack row2item2;
    private final ItemStack row2item3;
    private final ItemStack row3item1;
    private final ItemStack row3item2;
    private final ItemStack row3item3;
    private final ItemStack row4item1;
    private final ItemStack row4item2;
    private final ItemStack row4item3;


    public MiniDonutMachineRecipe(Ingredient input, Ingredient oil, ItemStack output, ItemStack row1item1, ItemStack row1item2, ItemStack row1item3, ItemStack row2item1, ItemStack row2item2, ItemStack row2item3, ItemStack row3item1, ItemStack row3item2, ItemStack row3item3, ItemStack row4item1, ItemStack row4item2, ItemStack row4item3) {
        this.input = input;
        this.oil = oil;
        this.output = output;
        this.row1item1 = row1item1;
        this.row1item2 = row1item2;
        this.row1item3 = row1item3;
        this.row2item1 = row2item1;
        this.row2item2 = row2item2;
        this.row2item3 = row2item3;
        this.row3item1 = row3item1;
        this.row3item2 = row3item2;
        this.row3item3 = row3item3;
        this.row4item1 = row4item1;
        this.row4item2 = row4item2;
        this.row4item3 = row4item3;
    }

    public Ingredient getInput(){return input;}
    public Ingredient getOil(){return oil;}
    public ItemStack getOutput(){return output;}
    public ItemStack getItemFromRow(int item, int row) {
        switch (row) {
            case 1 -> {
                switch (item) {
                    case 1 -> {
                        return row1item1;
                    }
                    case 2 -> {
                        return row1item2;
                    }
                    case 3 -> {
                        return row1item3;
                    }
                }
            }
            case 2 -> {
                switch (item) {
                    case 1 -> {
                        return row2item1;
                    }
                    case 2 -> {
                        return row2item2;
                    }
                    case 3 -> {
                        return row2item3;
                    }
                }
            }
            case 3 -> {
                switch (item) {
                    case 1 -> {
                        return row3item1;
                    }
                    case 2 -> {
                        return row3item2;
                    }
                    case 3 -> {
                        return row3item3;
                    }
                }
            }
            case 4 -> {
                switch (item) {
                    case 1 -> {
                        return row4item1;
                    }
                    case 2 -> {
                        return row4item2;
                    }
                    case 3 -> {
                        return row4item3;
                    }
                }
            }

        }
        return ItemStack.EMPTY;
    }
}
