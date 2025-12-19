package com.example.advent;

import com.example.advent.net.NetworkHandler;
import com.example.advent.registry.ModItems;
import com.example.advent.registry.ModMenus;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(AdventMod.MODID)
public class AdventMod {
    public static final String MODID = "adventcalendar";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Índice 0 = día 24 ... índice 6 = día 30
    public static final List<ItemStack> ADVENT_REWARDS = new ArrayList<>();

    @SuppressWarnings({"removal", "deprecation"})
    public AdventMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.REGISTER.register(modBus);
        ModMenus.REGISTER.register(modBus);

        modBus.addListener(this::clientSetup);
        modBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> "ANY", (remote, isNetwork) -> true)
        );

        createRewards();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ClientInit::init);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::register);
    }

    private static ItemStack crearCarta(String titulo, String texto) {
        ItemStack libro = new ItemStack(Items.WRITTEN_BOOK);
        CompoundTag nbt = new CompoundTag();

        nbt.putString("title", titulo);
        nbt.putString("author", "Calendario de Anto");
        nbt.putInt("generation", 0);

        ListTag pages = new ListTag();
        String paginaJson = Component.Serializer.toJson(Component.literal(texto));
        pages.add(StringTag.valueOf(paginaJson));

        nbt.put("pages", pages);
        libro.setTag(nbt);

        return libro;
    }

    private static void createRewards() {
        ADVENT_REWARDS.clear();

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 24",
                "“Antes de salir al mundo, siempre hacemos lo mismo: buscamos lo que abre puertas. Ahí empieza hoy tu aventura.”"
        ));

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 25",
                "“Hoy te guía quien nos quiere sin pedir nada: sigue la pista de los perretes. Donde se preparan sus paseos, está tu foto.”"
        ));

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 26",
                "“Hoy la pista te mira de vuelta. Ve donde te arreglas y donde el día empieza con agua.”"
        ));

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 27",
                "“Donde caen los abrazos después de un día largo, hay un rincón que sabe nuestros secretos. Busca entre lo blandito.”"
        ));

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 28",
                "“Hay un lugar donde compartimos hambre, risas y planes. Hoy tu foto está cerca de donde se comparte la comida.”"
        ));

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 29",
                "“Cuando se apagan las luces, quedan las promesas. Busca donde la noche guarda lo importante: al lado de la cama.”"
        ));

        ADVENT_REWARDS.add(crearCarta(
                "Pista Día 30",
                "“Hoy no hay más piezas: simplemente monta el puzzle.”"
        ));

        LOGGER.info("Recompensas cargadas: {}", ADVENT_REWARDS.size());
    }
}
