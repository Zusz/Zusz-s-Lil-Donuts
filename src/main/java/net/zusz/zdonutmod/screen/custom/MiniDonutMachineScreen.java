package net.zusz.zdonutmod.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.zusz.zdonutmod.ZDonutMod;

public class MiniDonutMachineScreen extends AbstractContainerScreen<MiniDonutMachineMenu> {
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ZDonutMod.MOD_ID,"textures/gui/mini_donut_machine/mini_donut_machine_gui_with_inventory.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ZDonutMod.MOD_ID,"textures/gui/arrow_progress.png");
    private static final ResourceLocation OIL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ZDonutMod.MOD_ID,"textures/gui/mini_donut_machine/oil_meter.png");

    public MiniDonutMachineScreen(MiniDonutMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderOil(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE,x + 49, y + 35, 0, 0, menu.getScaledArrowProgress(), 16, 24, 16);
        }
    }
    private void renderOil(GuiGraphics guiGraphics, int x, int y) {
            guiGraphics.blit(OIL_TEXTURE,x + 49, y + 53, 0, 0, menu.getScaledOil(), 16, 24, 16);

    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        //System.out.println(menu.getItemHandler().getSlots());
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);

    }
}
