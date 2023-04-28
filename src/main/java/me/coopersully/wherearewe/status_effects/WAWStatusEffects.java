package me.coopersully.wherearewe.status_effects;

import me.coopersully.wherearewe.WAW;
import me.coopersully.wherearewe.status_effects.LightLossEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class WAWStatusEffects {
    public static final StatusEffect LIGHT_LOSS = new LightLossEffect(StatusEffectCategory.HARMFUL, 0x6b3e26);

    public static void registerStatusEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(WAW.modId, "light_loss"), LIGHT_LOSS);
    }
}
