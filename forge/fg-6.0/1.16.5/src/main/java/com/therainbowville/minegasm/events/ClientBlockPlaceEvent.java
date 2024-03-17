package com.therainbowville.minegasm.events;

import net.minecraft.block.Block;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class ClientBlockPlaceEvent extends Event {
    private final ClientPlayerEntity player;
    private final BlockPos blockPos;
    private final Block block;

    public ClientBlockPlaceEvent(ClientPlayerEntity player, BlockPos blockPos, Block block) {
        this.player = player;
        this.blockPos = blockPos;
        this.block = block;
    }

    public ClientPlayerEntity getPlayer() {
        return player;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public Block getBlock() {
        return block;
    }
}
