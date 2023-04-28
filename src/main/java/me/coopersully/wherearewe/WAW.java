package me.coopersully.wherearewe;

import me.coopersully.wherearewe.events.DarknessEffectHandler;
import me.coopersully.wherearewe.events.PlayerSpawnTorchHandler;
import me.coopersully.wherearewe.sounds.WAWSoundEvents;
import me.coopersully.wherearewe.status_effects.WAWStatusEffects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WAW implements ModInitializer {

    public static String modId = "where-are-we";
    public static final Logger LOGGER = LoggerFactory.getLogger(modId);

    @Override
    public void onInitialize() {

        WAWSoundEvents.registerSoundEvents();
        WAWStatusEffects.registerStatusEffects();

        // Register darkness handler
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                DarknessEffectHandler.handlePlayerEffect(player);
            }
        });

        // Register torch at feet on spawning
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            PlayerSpawnTorchHandler.placeTorchAtPlayerFeet(newPlayer);
        });

        // Prevent players from sleeping through the night
        EntitySleepEvents.ALLOW_RESETTING_TIME.register(player -> false);

        // Apply regeneration on sleep
        EntitySleepEvents.START_SLEEPING.register((entity, sleepingPos) -> {
            StatusEffectInstance sleepRegen = new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0);
            entity.addStatusEffect(sleepRegen);
        });
        EntitySleepEvents.STOP_SLEEPING.register((entity, sleepingPos) -> {
            StatusEffectInstance sleepRegen = new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0);
            entity.addStatusEffect(sleepRegen);
        });

    }

}
