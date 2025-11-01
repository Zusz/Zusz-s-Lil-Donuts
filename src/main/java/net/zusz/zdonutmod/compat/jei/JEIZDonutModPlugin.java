package net.zusz.zdonutmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.zusz.zdonutmod.ZDonutMod;
import net.zusz.zdonutmod.block.ModBlocks;
import net.zusz.zdonutmod.screen.custom.MiniDonutMachineScreen;

import java.util.List;

import static net.zusz.zdonutmod.compat.jei.MiniDonutRecipeCategory.MINI_DONUT_MACHINE_RECIPE_RECIPE_TYPE;

@JeiPlugin
public class JEIZDonutModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(ZDonutMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MiniDonutRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()
        ));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<MiniDonutMachineRecipe> miniDonutMachineRecipes = MiniDonutMachineRecipeManager.getAllRecipes();
        registration.addRecipes(MINI_DONUT_MACHINE_RECIPE_RECIPE_TYPE, miniDonutMachineRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MiniDonutMachineScreen.class, 49, 35, 22, 20, MINI_DONUT_MACHINE_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MINI_DONUT_MACHINE.asItem()),
                MINI_DONUT_MACHINE_RECIPE_RECIPE_TYPE);
    }
}
