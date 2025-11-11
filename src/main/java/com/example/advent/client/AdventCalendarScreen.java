package com.example.advent.client;

import com.example.advent.net.NetworkHandler;
import com.example.advent.net.PacketClaimToday;
import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import java.time.LocalDate;
import java.time.Month;

public class AdventCalendarScreen extends AbstractContainerScreen<AdventCalendarMenu> {
    private Button claimButton;

    public AdventCalendarScreen(AdventCalendarMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 248; this.imageHeight = 166;
    }

    @Override protected void init() {
        super.init();
        LocalDate now = LocalDate.now();
        boolean claimable = now.getMonth()==Month.DECEMBER && now.getDayOfMonth()>=1 && now.getDayOfMonth()<=24;
        int cx = this.leftPos + 10, cy = this.topPos + 20;
        this.claimButton = Button.builder(Component.translatable("button.adventcalendar.claim"),
            b -> NetworkHandler.sendToServer(new PacketClaimToday()))
            .bounds(cx, cy, 120, 20).build();
        this.claimButton.active = claimable;
        addRenderableWidget(this.claimButton);
    }

    @Override protected void renderBg(GuiGraphics g, float pt, int mx, int my) {
        int x1 = this.leftPos, y1 = this.topPos;
        g.fill(x1, y1, x1+this.imageWidth, y1+this.imageHeight, 0xAA000000);
        int startX = x1 + 10, startY = y1 + 50, w = 22, h = 18, pad = 4;
        LocalDate now = LocalDate.now();
        int today = (now.getMonth()==Month.DECEMBER) ? now.getDayOfMonth() : -1;
        for (int i=1;i<=24;i++){
            int col=(i-1)%8, row=(i-1)/8;
            int bx=startX+col*(w+pad), by=startY+row*(h+pad);
            int color = (i==today)?0xFF00AA00:0xFF555555;
            g.fill(bx, by, bx+w, by+h, color);
            g.drawString(this.font, String.valueOf(i), bx+6, by+5, 0xFFFFFFFF, true);
        }
    }

    @Override protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
        g.drawString(this.font, this.title, 10, 6, 0xFFFFFF);
    }
}

