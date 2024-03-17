package com.therainbowville.minegasm.mixin;

import com.therainbowville.minegasm.events.ClientAttackEvent;
import com.therainbowville.minegasm.events.ClientBlockBreakEvent;
import com.therainbowville.minegasm.events.ClientBlockPlaceEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerController.class)
public class PlayerControllerMixin {
    private static Logger LOGGER = LogManager.getLogger();

    @Inject(method = "destroyBlock", at = @At("HEAD"), cancellable = true)
    public void onDestroyBlock(BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
        if (playerEntity != null) {
            Block block = playerEntity.level.getBlockState(blockPos).getBlock();
            ClientBlockBreakEvent event = new ClientBlockBreakEvent(playerEntity, blockPos, playerEntity.level.getBlockState(blockPos));
            MinecraftForge.EVENT_BUS.post(event);
        }
    }

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    public void onUseItemOn(ClientPlayerEntity player, ClientWorld world, Hand hand, BlockRayTraceResult result, CallbackInfoReturnable<ActionResultType> cir) {
        BlockPos blockPos = result.getBlockPos();
        // check if item is a block
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            ClientBlockPlaceEvent event = new ClientBlockPlaceEvent(player, blockPos, block);
            MinecraftForge.EVENT_BUS.post(event);
        }
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void onInteract(PlayerEntity player, Entity entity, Hand hand, CallbackInfoReturnable<ActionResultType> cir) {
        // check if entity is a living entity
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        LOGGER.info("Player is holding: {}", item);
        LOGGER.info("Interacting with: {}", entity.getName());
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void onAttack(PlayerEntity player, Entity entity, CallbackInfo ci) {
        LOGGER.info("Attacking: {}", entity.getName());
        ClientAttackEvent event = new ClientAttackEvent(player, entity);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
