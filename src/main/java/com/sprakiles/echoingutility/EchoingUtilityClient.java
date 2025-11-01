package com.sprakiles.echoingutility;

import com.sprakiles.echoingutility.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;

public class EchoingUtilityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = client.player;
            if (player == null) return;

            ItemStack heldItem = player.getMainHandStack();
            if (heldItem.isEmpty()) return;

            if (heldItem.getItem() == ModItems.MODIFIED_AMETHYST_SHARD) {
                ClientWorld world = client.world;
                if (world == null) return;

                var random = world.getRandom();

                if (random.nextFloat() < 0.7f) {
                    double x = player.getX() + (random.nextDouble() - 0.5) * 0.6;
                    double y = player.getY() + 0.5 + random.nextDouble() * 0.8;
                    double z = player.getZ() + (random.nextDouble() - 0.5) * 0.6;

                    double vx = (random.nextDouble() - 0.5) * 0.05;
                    double vy = random.nextDouble() * 0.15;
                    double vz = (random.nextDouble() - 0.5) * 0.05;

                    if (random.nextBoolean()) {
                        world.addImportantParticleClient(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, vx, vy, vz);
                    } else {
                        world.addImportantParticleClient(ParticleTypes.PORTAL, x, y, z, vx, vy, vz);
                    }

                    if (random.nextFloat() < 0.2f) {
                        world.addImportantParticleClient(ParticleTypes.WARPED_SPORE, x, y, z, vx, vy, vz);
                    }
                }
            }
        });
    }
}
