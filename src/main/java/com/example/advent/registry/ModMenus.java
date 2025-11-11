package com.example.advent.registry;

import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.example.advent.AdventMod.MODID;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static final RegistryObject<MenuType<AdventCalendarMenu>> ADVENT_MENU =
            REGISTER.register("advent_menu",
                    () -> IForgeMenuType.create((windowId, inv, buf) -> new AdventCalendarMenu(windowId, inv, buf)));
}



