package com.example.advent.client;

import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class AdventCalendarScreen extends AbstractContainerScreen<AdventCalendarMenu> {
    public AdventCalendarScreen(AdventCalendarMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics gfx, float partialTick, int mouseX, int mouseY) {
        // Dibuja tu fondo aquí (bind de textura y blit) o deja vacío si no usas textura
    }
}
