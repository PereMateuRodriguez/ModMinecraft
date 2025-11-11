package com.example.advent.net;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class PacketClaimToday {
    private final int day;

    // Constructor usado en el cliente
    public PacketClaimToday(int day) {
        this.day = day;
    }

    // Constructor usado al recibir el paquete en el servidor
    public PacketClaimToday(FriendlyByteBuf buf) {
        this.day = buf.readInt();
    }

    // Codifica el número de día en el buffer
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.day);
    }

    // Decodifica el paquete (usado en registerMessage)
    public static PacketClaimToday decode(FriendlyByteBuf buf) {
        return new PacketClaimToday(buf);
    }

    // Maneja el paquete en el lado servidor
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Aquí pones tu lógica de recompensa por día
            // Ejemplo: System.out.println("El jugador reclamó el día: " + day);
            // Integración con PlayerAdventData:
            // PlayerAdventData.claim(ctx.get().getSender(), day);
        });
        ctx.get().setPacketHandled(true);
    }
}
