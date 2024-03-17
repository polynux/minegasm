package com.therainbowville.minegasm.events;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.eventbus.api.Event;

public class ClientDamageEvent extends Event {

        private final Entity entity;
        private final DamageSource source;
        private final float amount;

        public ClientDamageEvent(Entity entity, DamageSource source, float amount) {
            this.entity = entity;
            this.source = source;
            this.amount = amount;
        }

        public Entity getEntity() {
            return entity;
        }

        public DamageSource getSource() {
            return source;
        }

        public float getAmount() {
            return amount;
        }
}
