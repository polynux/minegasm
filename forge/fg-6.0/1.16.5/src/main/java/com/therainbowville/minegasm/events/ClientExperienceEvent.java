package com.therainbowville.minegasm.events;

import net.minecraftforge.eventbus.api.Event;

public class ClientExperienceEvent extends Event {
    private int level;
    private int amount;

    public ClientExperienceEvent(int level, int amount) {
        this.level = level;
        this.amount = amount;
    }

    public int getLevel() {
        return level;
    }

    public int getAmount() {
        return amount;
    }
}
