package com.example.advent.world.menu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import static com.example.advent.registry.ModMenus.ADVENT_MENU;

public class AdventCalendarMenu extends AbstractContainerMenu {
    public AdventCalendarMenu(int id, Inventory inv) {
        super(ADVENT_MENU.get(), id);
    }

    public AdventCalendarMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv);
    }

    @Override
    public boolean stillValid(Player player) { return true; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
