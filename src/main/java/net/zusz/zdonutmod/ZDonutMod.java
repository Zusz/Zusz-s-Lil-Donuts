package net.zusz.zdonutmod;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.zusz.zdonutmod.block.ModBlocks;
import net.zusz.zdonutmod.block.entity.ModBlockEntities;
import net.zusz.zdonutmod.block.entity.renderer.MiniDonutMachineBlockEntityRenderer;
import net.zusz.zdonutmod.item.ModCreativeModeTabs;
import net.zusz.zdonutmod.item.ModItems;
import net.zusz.zdonutmod.screen.ModMenuTypes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.zusz.zdonutmod.screen.custom.MiniDonutMachineScreen;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ZDonutMod.MOD_ID)
public class ZDonutMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "zdonutmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ZDonutMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        //NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public static void onRegisterScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.MINI_DONUT_MACHINE_MENU.get(), MiniDonutMachineScreen::new);}

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting (ServerStartingEvent event){
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        /*@SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //ModItemProperties.addCustomItemProperties();
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MINI_DONUT_MACHINE.get(), RenderType.translucent());//this adds half-transparent textures
        }*/

        @SubscribeEvent
        public static void registerScreens( RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.MINI_DONUT_MACHINE_MENU.get(), MiniDonutMachineScreen::new);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.MINI_DONUT_MACHINE_BE.get(), MiniDonutMachineBlockEntityRenderer::new);
        }

    }
}
