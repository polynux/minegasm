package com.therainbowville.minegasm.mixin;

import com.therainbowville.minegasm.events.ClientDamageEvent;
import com.therainbowville.minegasm.events.ClientExperienceEvent;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    private static Logger LOGGER = LogManager.getLogger();

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (amount > 0) {
            ClientDamageEvent event = new ClientDamageEvent((ClientPlayerEntity) (Object) this, source, amount);
            MinecraftForge.EVENT_BUS.post(event);
        }
    }

    @Inject(method = "setExperienceValues", at = @At("HEAD"), cancellable = true)
    public void onSetExperienceValues(float xpProgress, int totalXp, int experienceLevel, CallbackInfo ci) {
        LOGGER.info("Experience changed");
        int amount = (int) (xpProgress * 100);
        ClientExperienceEvent event = new ClientExperienceEvent(experienceLevel, amount);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
