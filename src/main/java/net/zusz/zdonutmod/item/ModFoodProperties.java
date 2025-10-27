package net.zusz.zdonutmod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties MINI_DONUT = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.5f)
            .build();
}
