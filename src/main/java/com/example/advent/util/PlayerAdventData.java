package com.example.advent.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerAdventData {
    private static final String ROOT = "adventcalendar";
    private static String keyForYear(int year) { return "y" + year; }
    public static boolean hasClaimed(ServerPlayer p, int year, int day) {
        CompoundTag root = p.getPersistentData().getCompound(ROOT);
        int mask = root.getInt(keyForYear(year));
        int bit = 1 << (day - 1);
        return (mask & bit) != 0;
    }
    public static void markClaimed(ServerPlayer p, int year, int day) {
        CompoundTag data = p.getPersistentData();
        CompoundTag root = data.getCompound(ROOT);
        int mask = root.getInt(keyForYear(year));
        mask |= (1 << (day - 1));
        root.putInt(keyForYear(year), mask);
        data.put(ROOT, root);
    }
}

