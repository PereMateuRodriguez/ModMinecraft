package com.example.advent.client;

import com.example.advent.world.menu.AdventCalendarMenu;
import com.example.advent.net.NetworkHandler;
import com.example.advent.net.PacketClaimToday;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class AdventCalendarScreen extends AbstractContainerScreen<AdventCalendarMenu> {

    public AdventCalendarScreen(AdventCalendarMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    protected void init() {
        super.init();
        int cols = 6, rows = 4, slotW = 26, slotH = 26, gapX = 6, gapY = 9;
        int totalWidth = cols * slotW + (cols - 1) * gapX;
        int totalHeight = rows * slotH + (rows - 1) * gapY;
        int marginX = 18;
        int marginY = 54;

        this.imageWidth = totalWidth + marginX * 2;
        this.imageHeight = totalHeight + marginY + 18;

        int startX = this.leftPos + marginX;
        int startY = this.topPos + marginY;

        int day = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int bx = startX + c * (slotW + gapX);
                int by = startY + r * (slotH + gapY);
                if (day <= 24) {
                    final int dayIndex = day;
                    Button btn = Button.builder(
                                    Component.literal("🎁 " + dayIndex),
                                    b -> NetworkHandler.CHANNEL.sendToServer(new PacketClaimToday(dayIndex))
                            )
                            .bounds(bx, by, slotW, slotH)
                            .build();
                    this.addRenderableWidget(btn);
                    day++;
                }
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xCCFFFFFF);
        graphics.hLine(leftPos, leftPos + imageWidth, topPos, 0xFF2196F3);
        graphics.hLine(leftPos, leftPos + imageWidth, topPos + imageHeight, 0xFF2196F3);
        graphics.vLine(leftPos, topPos, topPos + imageHeight, 0xFF2196F3);
        graphics.vLine(leftPos + imageWidth, topPos, topPos + imageHeight, 0xFF2196F3);
        graphics.drawCenteredString(this.font, "🎄", leftPos + imageWidth / 2, topPos + 12, 0xFFD700);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawCenteredString(this.font, "Calendario Navideño", leftPos + imageWidth / 2, topPos + 24, 0xFF2196F3);
        graphics.drawCenteredString(this.font, "¡Selecciona un día para abrir tu regalo!", leftPos + imageWidth / 2, topPos + 38, 0xFF4CAF50);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBg(graphics, partialTicks, mouseX, mouseY);
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderLabels(graphics, mouseX, mouseY);
    }
}
