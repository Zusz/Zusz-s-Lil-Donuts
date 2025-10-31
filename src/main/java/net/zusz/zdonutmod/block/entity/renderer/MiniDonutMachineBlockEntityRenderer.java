package net.zusz.zdonutmod.block.entity.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.zusz.zdonutmod.block.entity.MiniDonutMachineBlockEntity;

public class MiniDonutMachineBlockEntityRenderer implements BlockEntityRenderer<MiniDonutMachineBlockEntity> {

    public MiniDonutMachineBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(MiniDonutMachineBlockEntity miniDonutMachineBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();


        if (miniDonutMachineBlockEntity.getFacing() == Direction.EAST) {

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 9,  0.655f, 0.12f, 0.40f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 6,  0.655f, 0.12f, 0.55f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 3,  0.655f, 0.12f, 0.70f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 10,  0.5f, 0.12f, 0.40f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 7,  0.5f, 0.12f, 0.55f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 4,  0.5f, 0.12f, 0.70f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 11,  0.345f, 0.12f, 0.40f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 8,  0.345f, 0.12f, 0.55f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 5,  0.345f, 0.12f, 0.70f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 12,  0.345f, 0.04f, 0.18f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 13,  0.5f, 0.04f, 0.18f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 14,  0.655f, 0.04f, 0.18f);

        }

        if (miniDonutMachineBlockEntity.getFacing() == Direction.WEST) {

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 9,  0.345f, 0.12f, 0.60f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 6,  0.345f, 0.12f, 0.45f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 3,  0.345f, 0.12f, 0.30f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 10,  0.5f, 0.12f, 0.60f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 7,  0.5f, 0.12f, 0.45f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 4,  0.5f, 0.12f, 0.30f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 11,  0.655f, 0.12f, 0.60f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 8,  0.655f, 0.12f, 0.45f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 5,  0.655f, 0.12f, 0.30f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 12,  0.345f, 0.04f, 0.82f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 13,  0.5f, 0.04f, 0.82f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 14,  0.655f, 0.04f, 0.82f);

        }

        if (miniDonutMachineBlockEntity.getFacing() == Direction.SOUTH) {

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 9,  0.60f, 0.12f, 0.345f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 6,  0.45f, 0.12f, 0.345f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 3,  0.30f, 0.12f, 0.345f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 10,  0.60f, 0.12f, 0.50f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 7,  0.45f, 0.12f, 0.50f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 4,  0.3f, 0.12f, 0.50f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 11,  0.60f, 0.12f, 0.655f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 8,  0.45f, 0.12f, 0.655f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 5,  0.30f, 0.12f, 0.655f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 12,  0.82f, 0.04f, 0.345f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 13,  0.82f, 0.04f, 0.5f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 14,  0.82f, 0.04f, 0.655f);

        }

        if (miniDonutMachineBlockEntity.getFacing() == Direction.NORTH) {

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 9,  0.40f, 0.12f, 0.655f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 6,  0.55f, 0.12f, 0.655f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 3,  0.70f, 0.12f, 0.655f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 10,  0.40f, 0.12f, 0.50f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 7,  0.55f, 0.12f, 0.50f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 4,  0.70f, 0.12f, 0.50f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 11,  0.40f, 0.12f, 0.345f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 8,  0.55f, 0.12f, 0.345f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 5,  0.70f, 0.12f, 0.345f);

            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 12,  0.18f, 0.04f, 0.655f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 13,  0.18f, 0.04f, 0.5f);
            renderSlotItem(miniDonutMachineBlockEntity, poseStack, multiBufferSource, itemRenderer, 14,  0.18f, 0.04f, 0.345f);

        }
    }

    private void renderSlotItem(MiniDonutMachineBlockEntity be, PoseStack poseStack, MultiBufferSource buffer,
                                ItemRenderer renderer, int slot, float x, float y, float z) {
        ItemStack stack = be.itemHandler.getStackInSlot(slot);
        if (stack.isEmpty()) return;

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(0.13f, 0.13f, 0.13f);
        poseStack.mulPose(Axis.YP.rotationDegrees(be.getRenderingRotation()));
        poseStack.mulPose(Axis.XP.rotationDegrees(90f));

        renderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                getLightLevel(be.getLevel(), be.getBlockPos()),
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                be.getLevel(),
                1
        );
        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
