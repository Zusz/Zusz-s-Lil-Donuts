package net.zusz.zdonutmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zusz.zdonutmod.block.ModBlocks;
import net.zusz.zdonutmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MINI_DONUT_MACHINE)
                .pattern(" S ")
                .pattern(" W ")
                .pattern("IRI")
                .define('S', ModItems.SUNFLOWER_OIL)
                .define('W', Items.WHEAT)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_sunflower_oil", has(ModItems.SUNFLOWER_OIL)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUNFLOWER_OIL, 1)
                .requires(Items.SUNFLOWER, 3)
                .requires(Items.GLASS_BOTTLE, 1)
                .unlockedBy("has_sunflower", has(Items.SUNFLOWER)).save(recipeOutput, "crafting_sunflower_oil");
/*
        oreSmelting(recipeOutput, MILK_BUCKET_SMELT, RecipeCategory.MISC, ModItems.STEAMED_MILK.get(), 0.25f, 2400, "coffee");*/
    }
}
