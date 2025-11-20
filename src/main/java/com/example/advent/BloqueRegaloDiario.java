package com.example.advent;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;

public class BloqueRegaloDiario extends Block {

    public BloqueRegaloDiario(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // Solo ejecutar en el servidor
        if (!level.isClientSide) {
            // Comprobar que es un jugador
            if (entity instanceof ServerPlayer player) {
                long diaActual = level.getDayTime() / 24000L;

                // Datos persistentes del jugador (Forge)
                CompoundTag persistentData = player.getPersistentData();
                String TAG_KEY = "UltimoDiaRegaloEspada";
                long ultimoDia = persistentData.getLong(TAG_KEY);

                if (diaActual > ultimoDia) {
                    // Dar espada de diamante
                    ItemStack espada = new ItemStack(Items.DIAMOND_SWORD);
                    if (!player.getInventory().add(espada)) {
                        player.drop(espada, false);
                    }
                    persistentData.putLong(TAG_KEY, diaActual);
                    player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("¡Has recibido tu espada diaria!"),
                            true
                    );
                }
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
