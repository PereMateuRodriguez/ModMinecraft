package com.example.advent.client;

import com.example.advent.world.menu.AdventCalendarMenu;
import com.example.advent.net.NetworkHandler;
import com.example.advent.net.PacketClaimToday;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AdventCalendarScreen extends AbstractContainerScreen<AdventCalendarMenu> {

    // Fondo usando el cuadro "kebab" de Minecraft vanilla
    private static final ResourceLocation BG =
            ResourceLocation.parse("minecraft:textures/painting/kebab.png");

    // Ajusta el tamaño del GUI para centrar la pintura correctamente
    public AdventCalendarScreen(AdventCalendarMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 64;   // Tamaño real del PNG "kebab" es 64x48
        this.imageHeight = 48;
    }

    @Override
    protected void init() {
        super.init();
        int cols = 6;
        int rows = 4;
        int slotW = 14;
        int slotH = 12;
        int startX = this.leftPos + 2;
        int startY = this.topPos + 2;
        int gapX = 2;
        int gapY = 1;

        int day = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int bx = startX + c * (slotW + gapX);
                int by = startY + r * (slotH + gapY);
                final int dayIndex = day;
                Button btn = Button.builder(Component.literal(Integer.toString(dayIndex)), b -> {
                    NetworkHandler.CHANNEL.sendToServer(new PacketClaimToday(dayIndex));
                }).bounds(bx, by, slotW, slotH).build();

                this.addRenderableWidget(btn);
                day++;
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // Centra el cuadro en el fondo de la GUI
        graphics.blit(BG, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawString(this.font, this.title, 2, 2, 0xFFFFFF, false);
    }
}
