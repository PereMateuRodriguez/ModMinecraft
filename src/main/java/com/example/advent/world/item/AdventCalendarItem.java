package com.example.advent.world.item;

import com.example.advent.world.menu.AdventCalendarMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class AdventCalendarItem extends Item {

    public AdventCalendarItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer sp) {
            MenuProvider provider = new SimpleMenuProvider(
                    (containerId, inv, p) -> new AdventCalendarMenu(containerId, inv),
                    Component.translatable("menu.adventcalendar.calendario")
            );
            NetworkHooks.openScreen(sp, provider);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
