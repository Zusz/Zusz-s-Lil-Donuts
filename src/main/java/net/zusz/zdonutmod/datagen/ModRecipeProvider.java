package net.zusz.zdonutmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        /*ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_ARABICA_COFFEE_BEAN.get())
                .unlockedBy("has_raw_arabica_coffee_bean", has(ModItems.RAW_ARABICA_COFFEE_BEAN)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ARABICA_COFFEE_BEAN.get(), 9)
                .requires(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)
                .unlockedBy("has_raw_arabica_coffee_bean_sack", has(ModBlocks.RAW_ARABICA_COFFEE_BEAN_SACK)).save(recipeOutput, "raw_arabica_coffee_bean_from_sack");

        oreSmelting(recipeOutput, MILK_BUCKET_SMELT, RecipeCategory.MISC, ModItems.STEAMED_MILK.get(), 0.25f, 2400, "coffee");*/
    }
}
