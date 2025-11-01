package net.zusz.zdonutmod.compat.jei;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.zusz.zdonutmod.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class MiniDonutMachineRecipeManager {
    public static List<MiniDonutMachineRecipe> getAllRecipes() {
        List<MiniDonutMachineRecipe>MiniDonutMachineRecipes = new ArrayList<>();
        MiniDonutMachineRecipes.add(new MiniDonutMachineRecipe(
                Ingredient.of(Items.WHEAT),
                Ingredient.EMPTY,
                ItemStack.EMPTY,
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY
        ));

        MiniDonutMachineRecipes.add(new MiniDonutMachineRecipe(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                ItemStack.EMPTY,
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY
        ));

        MiniDonutMachineRecipes.add(new MiniDonutMachineRecipe(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.RAW_MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY
        ));

        MiniDonutMachineRecipes.add(new MiniDonutMachineRecipe(
                Ingredient.EMPTY,
                Ingredient.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem()),
                new ItemStack(ModItems.MINI_DONUT.asItem())
                ));

        return MiniDonutMachineRecipes;
    }
}
