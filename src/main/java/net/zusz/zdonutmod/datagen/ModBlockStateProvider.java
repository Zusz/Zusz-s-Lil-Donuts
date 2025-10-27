package net.zusz.zdonutmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zusz.zdonutmod.ZDonutMod;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, ZDonutMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }


    private void cubeAllBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void cubeBottomTopBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), models().cubeBottomTop(
                name(deferredBlock),
                blockTexture(deferredBlock, "_side"),
                blockTexture(deferredBlock, "_bottom"),
                blockTexture(deferredBlock, "_top")
                ));
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private String name(DeferredBlock<?> block) {
        return block.getId().getPath();
    }
    private ResourceLocation blockTexture(DeferredBlock<?> block, String suffix) {
        return modLoc("block/" + name(block) + suffix);
    }
}
