package com.example.advent.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import com.example.advent.world.menu.AdventCalendarMenu;

public class AdventCalendarItem extends Item {
    public AdventCalendarItem(Properties props) { super(props); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer sp) {
            SimpleMenuProvider provider = new SimpleMenuProvider(
                (id, inv, p) -> new AdventCalendarMenu(id, inv),
                net.minecraft.network.chat.Component.translatable("screen.adventcalendar.title")
            );
            NetworkHooks.openScreen(sp, provider, buf -> {});
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }
}

