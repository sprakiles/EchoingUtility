package com.sprakiles.echoingutility.item;

import com.sprakiles.echoingutility.EchoingUtility;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup ECHOING_UTILITY_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(EchoingUtility.MOD_ID, "echoing_utility"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.MODIFIED_AMETHYST_SHARD))
                    .displayName(Text.translatable("itemgroup.echoingutility"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.MODIFIED_AMETHYST_SHARD);
                    })
                    .build()
    );

    public static void registerItemGroups() {
        EchoingUtility.LOGGER.info("Registering Item Groups for " + EchoingUtility.MOD_ID);
    }
}
