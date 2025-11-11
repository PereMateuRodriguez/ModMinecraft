package com.example.advent.net;

import com.example.advent.util.PlayerAdventData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

public class PacketClaimToday {
    public static void encode(PacketClaimToday pkt, FriendlyByteBuf buf) {}
    public static PacketClaimToday decode(FriendlyByteBuf buf) { return new PacketClaimToday(); }

    public static void handle(PacketClaimToday pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sp = ctx.get().getSender();
            if (sp == null) return;
            LocalDate now = LocalDate.now();
            if (now.getMonth()!=Month.DECEMBER) return;
            int day = now.getDayOfMonth();
            if (day < 1 || day > 24) return;
            int year = now.getYear();
            if (PlayerAdventData.hasClaimed(sp, year, day)) return;
            ItemStack cake = new ItemStack(Items.CAKE);
            if (!sp.getInventory().add(cake)) sp.drop(cake, false);
            PlayerAdventData.markClaimed(sp, year, day);
        });
        ctx.get().setPacketHandled(true);
    }
}

