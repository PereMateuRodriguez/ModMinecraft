package com.example.advent.net;

import com.example.advent.AdventMod;
import com.example.advent.util.PlayerAdventData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.time.LocalDate;
import java.util.function.Supplier;

public class PacketClaimToday {
    private final int day;

    // Cliente
    public PacketClaimToday(int day) {
        this.day = day;
    }

    // Servidor (decode)
    public PacketClaimToday(FriendlyByteBuf buf) {
        this.day = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.day);
    }

    public static PacketClaimToday decode(FriendlyByteBuf buf) {
        return new PacketClaimToday(buf);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            // Solo 24..30
            if (day < 24 || day > 30) return;

            // Día 24 -> índice 0, día 30 -> índice 6
            int rewardIndex = day - 24;
            if (rewardIndex < 0 || rewardIndex >= AdventMod.ADVENT_REWARDS.size()) return;

            int year = LocalDate.now().getYear();

            // Evitar reclamar dos veces (usa tu PlayerAdventData)
            if (PlayerAdventData.hasClaimed(player, year, day)) {
                player.sendSystemMessage(Component.literal("§cYa has reclamado el día " + day + "."));
                return;
            }

            ItemStack reward = AdventMod.ADVENT_REWARDS.get(rewardIndex).copy();
            player.getInventory().add(reward);

            PlayerAdventData.markClaimed(player, year, day);

            player.sendSystemMessage(Component.literal("§6¡Regalo del día " + day + " para Anto!"));
        });

        ctx.get().setPacketHandled(true);
    }
}
