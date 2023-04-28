package me.coopersully.wherearewe.events;

import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import org.jetbrains.annotations.NotNull;

public class PlayerSpawnTorchHandler {
    public static void placeTorchAtPlayerFeet(@NotNull ServerPlayerEntity player) {

        BlockPos playerPos = player.getBlockPos();
        int lightLevel = player.world.getLightLevel(LightType.BLOCK, playerPos);

        if (lightLevel > 5) return;

        if (!player.world.getBlockState(playerPos).isReplaceable()) {
            // Spawn floating light of some sort
            player.sendMessage(Text.of("Your spawn-point could not be validated; fend for yourself."));
            return;
        }

        player.world.setBlockState(playerPos, Blocks.TORCH.getDefaultState());
    }
}
