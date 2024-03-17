package com.therainbowville.minegasm.events;

import net.minecraft.block.BlockState;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class ClientBlockBreakEvent extends Event {
    private final ClientPlayerEntity player;
    private final BlockPos blockPos;
    private final BlockState blockState;

    public ClientBlockBreakEvent(ClientPlayerEntity player, BlockPos blockPos, BlockState blockState) {
        this.player = player;
        this.blockPos = blockPos;
        this.blockState = blockState;
    }

    public ClientPlayerEntity getPlayer() {
        return player;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockState getBlockState() {
        return blockState;
    }
}
