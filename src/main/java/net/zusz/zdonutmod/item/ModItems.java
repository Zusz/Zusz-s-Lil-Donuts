package net.zusz.zdonutmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zdonutmod.ZDonutMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ZDonutMod.MOD_ID);

    public static final DeferredItem<Item> MINI_DONUT = ITEMS.register("mini_donut",//unique items
            () -> new Item(new Item.Properties().food(ModFoodProperties.MINI_DONUT)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
