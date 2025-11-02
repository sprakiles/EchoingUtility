package com.sprakiles.echoingutility;

import com.sprakiles.echoingutility.item.CondensedCatalystItem;
import com.sprakiles.echoingutility.item.ModItems;
import com.sprakiles.echoingutility.item.ModItemGroup;
import com.sprakiles.echoingutility.trade.VillagerTradeHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoingUtility implements ModInitializer {
    public static final String MOD_ID = "echoingutility";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Echoing Utility");

        // Register items and item groups
        ModItems.registerModItems();
        ModItemGroup.registerItemGroups();

        // Register villager trades
        VillagerTradeHandler.registerTrades();

        // Register tick handler for Temporal Echo ability
        CondensedCatalystItem.registerTickHandlerIfNeeded();
        LOGGER.info("Temporal Echo tick handler registered");

        // Death prevention handler (Void Anchor ability)
        ServerLivingEntityEvents.ALLOW_DEATH.register((LivingEntity entity, DamageSource source, float damageAmount) -> {
            if (!(entity instanceof ServerPlayerEntity player)) {
                return true;
            }

            for (int i = 0; i < player.getInventory().size(); i++) {
                ItemStack stack = player.getInventory().getStack(i);
                if (stack.getItem() == ModItems.MODIFIED_AMETHYST_SHARD) {
                    player.setHealth(player.getMaxHealth() * 0.5f);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 4));

                    if (player.getWorld() instanceof ServerWorld sw) {
                        sw.spawnParticles(ParticleTypes.PORTAL,
                                player.getX(), player.getY() + 1, player.getZ(),
                                100, 0.5, 1, 0.5, 0.5);
                    }

                    player.sendMessage(Text.literal("The Modified Amethyst Shard saved you from death!").formatted(Formatting.LIGHT_PURPLE), true);
                    stack.decrement(1);
                    LOGGER.info("Modified Amethyst Shard prevented death for player: {}", player.getName().getString());
                    return false;
                }
            }
            return true;
        });

        LOGGER.info("Echoing Utility initialized successfully");
    }
}