package me.coopersully.wherearewe.events;

import me.coopersully.wherearewe.status_effects.WAWStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.LightType;
import org.jetbrains.annotations.NotNull;

public class DarknessEffectHandler {
    public static void handlePlayerEffect(@NotNull ServerPlayerEntity player) {

        if (player.isInvulnerable()) return;
        if (player.hasStatusEffect(WAWStatusEffects.LIGHT_LOSS)) return;

        int lightLevel = player.world.getLightLevel(LightType.BLOCK, player.getBlockPos());

        if (lightLevel <= 5) { // Almost complete darkness
            player.addStatusEffect(new StatusEffectInstance(WAWStatusEffects.LIGHT_LOSS, 60, 5 - lightLevel));
        }
    }
}
