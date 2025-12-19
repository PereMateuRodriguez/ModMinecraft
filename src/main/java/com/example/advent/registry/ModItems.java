package com.example.advent.registry;

import com.example.advent.AdventMod;
import com.example.advent.world.item.AdventCalendarItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER =
            DeferredRegister.create(ForgeRegistries.ITEMS, AdventMod.MODID);

    public static final RegistryObject<Item> CONSTRUIR_CALENDARIO =
            REGISTER.register("construir_calendario",
                    () -> new AdventCalendarItem(new Item.Properties()));
}
