package com.example.advent.world.menu;

import com.example.advent.registry.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class AdventCalendarMenu extends AbstractContainerMenu {

    // Constructor normal (SimpleMenuProvider lo usa)
    public AdventCalendarMenu(int containerId, Inventory inv) {
        super(ModMenus.ADVENT_MENU.get(), containerId);
    }

    // Constructor necesario para IForgeMenuType.create(...)
    public AdventCalendarMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
