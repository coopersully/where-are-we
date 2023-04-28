package me.coopersully.wherearewe.status_effects;

import me.coopersully.wherearewe.sounds.WAWSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;

public class LightLossEffect extends StatusEffect {
    public LightLossEffect(StatusEffectCategory type, int color) {
        super(type, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayerEntity player) {

            // Play whisper sounds
            float intensity = 0.25F * amplifier;
            player.getWorld().playSoundAtBlockCenter(player.getBlockPos(), WAWSoundEvents.WHISPERS, SoundCategory.HOSTILE, intensity, intensity, true);

            // Damage the player
            player.setHealth(player.getHealth() - (1f * amplifier));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Define how often the update effect should be applied
        return duration % 20 == 0;
    }
}
