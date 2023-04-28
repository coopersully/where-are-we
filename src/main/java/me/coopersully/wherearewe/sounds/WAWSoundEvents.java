package me.coopersully.wherearewe.sounds;

import me.coopersully.wherearewe.WAW;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class WAWSoundEvents {
    public static final SoundEvent WHISPERS = SoundEvent.of(new Identifier(WAW.modId, "whispers"));

    public static void registerSoundEvents() {
        Registry.register(Registries.SOUND_EVENT, new Identifier(WAW.modId, "whispers"), WHISPERS);
    }
}
