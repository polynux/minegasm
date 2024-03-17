package com.therainbowville.minegasm.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class ClientAttackEvent extends Event {
    private final PlayerEntity player;
    private final Entity target;

    public ClientAttackEvent(PlayerEntity player, Entity target) {
        this.player = player;
        this.target = target;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public Entity getTarget() {
        return target;
    }
}
