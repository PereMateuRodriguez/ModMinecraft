package com.example.advent.world.menu;

import com.example.advent.registry.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class AdventCalendarMenu extends AbstractContainerMenu {

    // Constructor usado por SimpleMenuProvider: (containerId, playerInventory)
    public AdventCalendarMenu(int containerId, Inventory inv) {
        super(ModMenus.ADVENT_MENU.get(), containerId);
        // Si en el futuro añades slots, haz addSlot(...) aquí
    }

    // Requisito de AbstractContainerMenu: si el jugador puede seguir usando el menú
    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    // Requisito de AbstractContainerMenu: lógica de shift‑click; vacía por ahora
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
