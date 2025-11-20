package com.example.advent.client;

import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.ArrayList;
import java.util.List;

public class AdventCalendarScreen extends AbstractContainerScreen<AdventCalendarMenu> {

    // Genera la lista completa de recompensas
    private final List<ItemStack> ADVENT_REWARDS = createRewards();

    private static List<ItemStack> createRewards() {
        List<ItemStack> list = new ArrayList<>();

        list.add(new ItemStack(Items.SNIFFER_EGG));                      // Día 1: Huevo de Sniffer
        list.add(new ItemStack(Items.CHERRY_SAPLING, 2));                // Día 2: Brotes de cerezo x2
        list.add(new ItemStack(Items.NETHERITE_PICKAXE));                // Día 3: Pico de netherite
        list.add(new ItemStack(Items.GOAT_HORN));                        // Día 4: Cuerno de cabra
        list.add(new ItemStack(Items.NETHERITE_INGOT, 10));              // Día 5: 10x lingote netherite
        list.add(new ItemStack(Items.CACTUS));                           // Día 6: Cactus
        list.add(new ItemStack(Items.FIREWORK_ROCKET, 64));              // Día 7: 1 stack cohetes

        // Día 8: libro escrito con mensaje "test"
        ItemStack day8Book = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag bookTag8 = day8Book.getOrCreateTag();
        bookTag8.putString("title", "Carta");
        bookTag8.putString("author", "Calendario Navideño");
        ListTag pages8 = new ListTag();
        pages8.add(net.minecraft.nbt.StringTag.valueOf("{\"text\":\"test\"}"));
        bookTag8.put("pages", pages8);
        list.add(day8Book);

        list.add(new ItemStack(Items.OCHRE_FROGLIGHT));                  // Día 9: Luces de rana
        list.add(new ItemStack(Items.ELYTRA));                           // Día 10: Elytras
        list.add(new ItemStack(Items.HONEY_BOTTLE));                     // Día 11: Miel
        list.add(new ItemStack(Items.INFESTED_STONE));                   // Día 12: Piedra infestada
        list.add(new ItemStack(Items.HORSE_SPAWN_EGG, 2));               // Día 13: 2x huevo de caballo
        list.add(new ItemStack(Items.SCUTE));                            // Día 14: Caparazón de tortuga
        list.add(new ItemStack(Items.COAL));                             // Día 15: Carbón
        list.add(new ItemStack(Items.WHITE_CANDLE));                     // Día 16: Vela blanca
        list.add(new ItemStack(Items.AXOLOTL_BUCKET));                   // Día 17: Alojote
        list.add(new ItemStack(Items.NETHERITE_INGOT, 10));              // Día 18: 10x lingote netherite
        list.add(new ItemStack(Items.CAMEL_SPAWN_EGG));                  // Día 19: Huevo de camello
        list.add(new ItemStack(Items.GLOBE_BANNER_PATTERN));             // Día 20: Patrón de estandarte "Globe"
        list.add(new ItemStack(Items.POTION));                           // Día 21: Poción básica
        list.add(new ItemStack(Items.INFESTED_STONE));                   // Día 22: Piedra infestada
        list.add(new ItemStack(Items.STICK));                            // Día 23: Palo

        // Día 24: libro escrito con mensaje "test1"
        ItemStack day24Book = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag bookTag24 = day24Book.getOrCreateTag();
        bookTag24.putString("title", "Pista Final");
        bookTag24.putString("author", "Calendario Navideño");
        ListTag pages24 = new ListTag();
        pages24.add(net.minecraft.nbt.StringTag.valueOf("{\"text\":\"test1\"}"));
        bookTag24.put("pages", pages24);
        list.add(day24Book);

        return list;
    }

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
                if (day <= ADVENT_REWARDS.size()) {
                    final int dayIndex = day;
                    Button btn = Button.builder(
                                    Component.literal("🎁 " + dayIndex),
                                    b -> {
                                        if (this.minecraft != null && this.minecraft.player != null) {
                                            ItemStack reward = ADVENT_REWARDS.get(dayIndex - 1).copy();
                                            this.minecraft.player.getInventory().add(reward);
                                        }
                                    }
                            ).bounds(bx, by, slotW, slotH)
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
