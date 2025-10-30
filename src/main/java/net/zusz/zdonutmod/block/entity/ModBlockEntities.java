package net.zusz.zdonutmod.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zusz.zdonutmod.ZDonutMod;
import net.zusz.zdonutmod.block.ModBlocks;
import net.zusz.zdonutmod.block.custom.MiniDonutMachineBlock;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ZDonutMod.MOD_ID);

    public static final Supplier<BlockEntityType<MiniDonutMachineBlockEntity>> MINI_DONUT_MACHINE_BE =
            BLOCK_ENTITIES.register("coffee_machine_be", () -> BlockEntityType.Builder.of(
                    MiniDonutMachineBlockEntity::new, ModBlocks.MINI_DONUT_MACHINE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
