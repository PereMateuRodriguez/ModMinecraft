package com.example.advent;

import com.example.advent.net.NetworkHandler;
import com.example.advent.registry.ModItems;
import com.example.advent.registry.ModMenus;
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

        // Registros (DeferredRegister) al bus del mod
        ModItems.REGISTER.register(modBus);
        ModMenus.REGISTER.register(modBus);

        // Listeners de setup
        modBus.addListener(this::clientSetup);
        modBus.addListener(this::commonSetup);

        // Extension point de compatibilidad (acepta cualquier versión en red/guardado)
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "ANY", (remote, isNetwork) -> true));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ClientInit::init);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::register);
    }
}
