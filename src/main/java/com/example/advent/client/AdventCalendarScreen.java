package com.example.advent.client;

import com.example.advent.net.NetworkHandler;
import com.example.advent.net.PacketClaimToday;
import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class AdventCalendarScreen extends AbstractContainerScreen<AdventCalendarMenu> {

    public AdventCalendarScreen(AdventCalendarMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        // Tamaño ventana
        this.imageWidth = 176;
        this.imageHeight = 130;

        // Ocultar textos automáticos (evita duplicados)
        this.titleLabelY = 1000;
        this.inventoryLabelY = 1000;
    }

    @Override
    protected void init() {
        super.init();

        // Botones centrados
        int startX = this.leftPos + 28;
        int startY = this.topPos + 30;

        int slotW = 24;
        int slotH = 24;
        int gap = 8;
        int cols = 4;

        int currentDay = 24;
        int maxDay = 30;

        int col = 0;
        int row = 0;

        while (currentDay <= maxDay) {
            final int dayIndex = currentDay;

            int bx = startX + col * (slotW + gap);
            int by = startY + row * (slotH + gap);

            this.addRenderableWidget(
                    Button.builder(Component.literal("" + dayIndex), b -> {
                        // Enviar al servidor para que el servidor entregue el libro (sin ghost items)
                        NetworkHandler.CHANNEL.sendToServer(new PacketClaimToday(dayIndex));
                        this.onClose();
                    }).bounds(bx, by, slotW, slotH).build()
            );

            currentDay++;
            col++;
            if (col >= cols) {
                col = 0;
                row++;
            }
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // Fondo Rojo
        graphics.fill(this.leftPos, this.topPos, this.leftPos + this.imageWidth, this.topPos + this.imageHeight, 0xFF8B0000);
        // Fondo Interior
        graphics.fill(this.leftPos + 4, this.topPos + 4, this.leftPos + this.imageWidth - 4, this.topPos + this.imageHeight - 4, 0xFFA52A2A);
        // Línea Dorada
        graphics.fill(this.leftPos + 2, this.topPos + 20, this.leftPos + this.imageWidth - 2, this.topPos + 22, 0xFFFFD700);

        // Título
        graphics.drawCenteredString(this.font, "§lCalendario de Anto", this.leftPos + this.imageWidth / 2, this.topPos + 8, 0xFFFFD700);
    }
}
