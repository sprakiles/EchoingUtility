package com.sprakiles.echoingutility.item;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CondensedCatalystItem - Temporal Echo implemented with a central tick handler.
 * Void Anchor (death prevention) is handled in EchoingUtility.java via ALLOW_DEATH event.
 */
public class CondensedCatalystItem extends Item {

    // Map of pending echoes: player UUID -> EchoData
    private static final Map<UUID, EchoData> PENDING_ECHOES = new ConcurrentHashMap<>();

    private static boolean TICK_HANDLER_REGISTERED = false;

    public CondensedCatalystItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    // TEMPORAL ECHO - Right-click ability
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
            // Check if player already has a pending echo
            if (PENDING_ECHOES.containsKey(serverPlayer.getUuid())) {
                user.sendMessage(Text.literal("You must wait for your previous echo to return.")
                        .formatted(Formatting.RED), true);
                return ActionResult.PASS;
            }

            // Store current position and health
            Vec3d echoPos = user.getPos();
            float echoHealth = user.getHealth();

            // Visual feedback - spawn particles at echo location
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.PORTAL,
                        echoPos.x, echoPos.y, echoPos.z,
                        30, 0.5, 1, 0.5, 0.1);
            }

            // Message
            user.sendMessage(Text.literal("Temporal Echo created! Returning in 10 seconds...")
                    .formatted(Formatting.LIGHT_PURPLE), true);

            // Put into pending echoes for 200 ticks (10 seconds)
            PENDING_ECHOES.put(serverPlayer.getUuid(), new EchoData(echoPos, echoHealth, 200));

            // Consume the catalyst
            stack.decrement(1);
        }

        return ActionResult.SUCCESS;
    }

    /**
     * Public static method to be called once during Mod Initialization (EchoingUtility.onInitialize)
     * to prevent the item registration NullPointerException.
     */
    public static void registerTickHandlerIfNeeded() {
        if (TICK_HANDLER_REGISTERED) return;
        synchronized (CondensedCatalystItem.class) {
            if (TICK_HANDLER_REGISTERED) return;
            ServerTickEvents.END_SERVER_TICK.register(server -> {
                try {
                    tickServer(server);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            });
            TICK_HANDLER_REGISTERED = true;
        }
    }

    // Called every server tick
    private static void tickServer(MinecraftServer server) {
        if (PENDING_ECHOES.isEmpty()) return;

        PENDING_ECHOES.entrySet().removeIf(entry -> {
            UUID uuid = entry.getKey();
            EchoData data = entry.getValue();

            data.ticksRemaining -= 1;
            if (data.ticksRemaining <= 0) {
                // Time to return the player
                ServerPlayerEntity player = server.getPlayerManager().getPlayer(uuid);
                if (player != null && player.isAlive()) {
                    ServerWorld world = (ServerWorld) player.getWorld();

                    // Teleport using requestTeleport (most compatible method)
                    player.requestTeleport(data.pos.x, data.pos.y, data.pos.z);

                    // Restore health (cap at max)
                    player.setHealth(Math.min(data.health, player.getMaxHealth()));

                    // Visual effects at return location
                    world.spawnParticles(ParticleTypes.REVERSE_PORTAL,
                            data.pos.x, data.pos.y + 1, data.pos.z,
                            50, 0.5, 1, 0.5, 0.3);

                    player.sendMessage(Text.literal("Returned to your Echo!").formatted(Formatting.AQUA), true);
                }
                // Remove entry
                return true;
            } else {
                // Keep entry
                return false;
            }
        });
    }

    // Simple holder for echo data
    private static final class EchoData {
        final Vec3d pos;
        final float health;
        int ticksRemaining;

        EchoData(Vec3d pos, float health, int ticksRemaining) {
            this.pos = pos;
            this.health = health;
            this.ticksRemaining = ticksRemaining;
        }
    }
}