package com.example.advent.net;

import com.example.advent.AdventMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class NetworkHandler {
    private static final String PROTOCOL = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(AdventMod.MODID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    private static int id = 0;

    // Llama a esto en el commonSetup: event.enqueueWork(NetworkHandler::register)
    public static void register() {
        CHANNEL.registerMessage(
                id++,
                PacketClaimToday.class,
                PacketClaimToday::encode,
                PacketClaimToday::decode,
                PacketClaimToday::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }

    // Para enviar desde el cliente al servidor
    public static <MSG> void sendToServer(MSG msg) {
        CHANNEL.sendToServer(msg);
    }
}

