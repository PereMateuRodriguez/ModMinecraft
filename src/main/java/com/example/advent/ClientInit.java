package com.example.advent;

import com.example.advent.client.AdventCalendarScreen;
import com.example.advent.registry.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;

public class ClientInit {
    public static void init() {
        MenuScreens.register(ModMenus.ADVENT_MENU.get(), AdventCalendarScreen::new);
    }
}

