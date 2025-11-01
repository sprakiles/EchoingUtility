package com.sprakiles.echoingutility;

import com.sprakiles.echoingutility.block.ModBlocks;
import com.sprakiles.echoingutility.block.entity.ModBlockEntities;
import com.sprakiles.echoingutility.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

public class Echoingutility implements ModInitializer {
    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModBlockEntities.register();
        ModScreenHandlers.register();
    }
}
