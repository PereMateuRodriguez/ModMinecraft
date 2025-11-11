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
            ResourceLocation.fromNamespaceAndPath(AdventMod.MODID, "main"),
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
                PacketClaimToday::handle
        );

    }
}
