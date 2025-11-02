package com.sprakiles.echoingutility.trade;

import com.sprakiles.echoingutility.EchoingUtility;
import com.sprakiles.echoingutility.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class VillagerTradeHandler {

    public static void registerTrades() {
        // Master level (level 5) Cleric trade
        // Cleric = Villager with purple robe
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 48),           // Cost: 48 Emeralds
                    Optional.empty(),                             // No second item required
                    new ItemStack(ModItems.MODIFIED_AMETHYST_SHARD, 1),  // Result: Modified Amethyst Shard
                    1,                                            // Max uses: 1 trade per restock
                    30,                                           // XP reward to player
                    0.2f                                          // Price multiplier (affects discounts)
            ));
        });

        EchoingUtility.LOGGER.info("Registered Cleric trade for Modified Amethyst Shard (48 Emeralds)");
    }
}