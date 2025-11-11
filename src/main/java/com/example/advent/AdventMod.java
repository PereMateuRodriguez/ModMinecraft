package com.example.advent;

import com.example.advent.registry.ModItems;
import com.example.advent.registry.ModMenus;
import com.example.advent.net.NetworkHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AdventMod.MODID)
public class AdventMod {
    public static final String MODID = "adventcalendar";

    public AdventMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.REGISTER.register(modBus);
        ModMenus.REGISTER.register(modBus);

        modBus.addListener(this::clientSetup);
        modBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "ANY", (a, b) -> true));
    }

    private void clientSetup(final FMLClientSetupEvent event) { event.enqueueWork(ClientInit::init); }
    private void commonSetup(final FMLCommonSetupEvent event) { event.enqueueWork(NetworkHandler::register); }
}

