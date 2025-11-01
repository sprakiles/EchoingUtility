package com.sprakiles.echoingutility.item;

import com.sprakiles.echoingutility.EchoingUtility;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    // Declare items - will be initialized in static block
    public static final Item MODIFIED_AMETHYST_SHARD;

    // Static initializer block to register items properly
    static {
        // Create identifier - use the string directly to avoid double namespace
        Identifier id = Identifier.of("echoingutility", "modified_amethyst_shard");
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        // Create item with registry key set in settings BEFORE construction
        MODIFIED_AMETHYST_SHARD = Registry.register(
                Registries.ITEM,
                key,
                new CondensedCatalystItem(
                        new Item.Settings()
                                .registryKey(key)  // CRITICAL: Set registry key before item construction
                                .maxCount(1)
                                .rarity(Rarity.EPIC)
                )
        );

        EchoingUtility.LOGGER.info("Registered item: {}", id);
    }

    public static void registerModItems() {
        EchoingUtility.LOGGER.info("Registering mod items for " + EchoingUtility.MOD_ID);
        // Items are already registered in static block, this just logs
    }
}