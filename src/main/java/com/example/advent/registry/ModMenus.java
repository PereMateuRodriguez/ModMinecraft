package com.example.advent.registry;

import com.example.advent.AdventMod;
import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, AdventMod.MODID);

    public static final RegistryObject<MenuType<AdventCalendarMenu>> ADVENT_MENU =
            REGISTER.register("advent_menu", () -> new MenuType<>(AdventCalendarMenu::new, FeatureFlags.DEFAULT_FLAGS));
}

