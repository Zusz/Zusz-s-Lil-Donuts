package net.zusz.zdonutmod.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.zusz.zdonutmod.ZDonutMod;
import net.zusz.zdonutmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class MiniDonutRecipeCategory implements IRecipeCategory<MiniDonutMachineRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(ZDonutMod.MOD_ID, "mini_donut_machine");

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(ZDonutMod.MOD_ID,
            "textures/gui/mini_donut_machine/mini_donut_machine_gui_without_inventory.png");

    public static final RecipeType<MiniDonutMachineRecipe> MINI_DONUT_MACHINE_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, MiniDonutMachineRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MiniDonutRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.MINI_DONUT.asItem()));
    }

    @Override
    public RecipeType<MiniDonutMachineRecipe> getRecipeType(){return MINI_DONUT_MACHINE_RECIPE_RECIPE_TYPE;}

    @Override
    public Component getTitle() {
        return Component.translatable("block.zdonutmod.mini_donut_machine");
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MiniDonutMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 16).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 34).addIngredients(recipe.getOil());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 26, 52).addItemStack(recipe.getOutput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 16).addItemStack(recipe.getItemFromRow(1,1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 34).addItemStack(recipe.getItemFromRow(2,1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 52).addItemStack(recipe.getItemFromRow(3,1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 98, 16).addItemStack(recipe.getItemFromRow(1,2));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 98, 34).addItemStack(recipe.getItemFromRow(2,2));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 98, 52).addItemStack(recipe.getItemFromRow(3,2));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 16).addItemStack(recipe.getItemFromRow(1,3));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 34).addItemStack(recipe.getItemFromRow(2,3));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 52).addItemStack(recipe.getItemFromRow(3,3));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 16).addItemStack(recipe.getItemFromRow(1,4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 34).addItemStack(recipe.getItemFromRow(2,4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 52).addItemStack(recipe.getItemFromRow(3,4));
    }

    @Override
    @SuppressWarnings({"removal"})
    public IDrawable getBackground() {
        return background;
    }
}
