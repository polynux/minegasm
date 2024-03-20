package com.therainbowville.minegasm.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class ClientHealEvent extends Event {

        private final float amount;
        private static final Minecraft minecraft = Minecraft.getInstance();

        public ClientHealEvent(float amount) {
            this.amount = amount;
        }

        public float getAmount() {
            return amount;
        }

        public PlayerEntity getPlayer() {
            return minecraft.player;
        }
}
