package net.zusz.zdonutmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zdonutmod.ZDonutMod;
import net.zusz.zdonutmod.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZDonutMod.MOD_ID);

    public static final Supplier<CreativeModeTab> COFFEE_ITEMS_TAB = CREATIVE_MODE_TAB.register("coffee_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MINI_DONUT.get()))
                    .title(Component.translatable("creativetab.zdonutmod.donut_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.MINI_DONUT_MACHINE);
                        output.accept(ModItems.SUNFLOWER_OIL);
                        output.accept(ModItems.MINI_DONUT);
                        output.accept(ModItems.RAW_MINI_DONUT);

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}