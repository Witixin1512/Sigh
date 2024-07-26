package net.witixin.sigh;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;

public class SighFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MidnightConfig.init(SighConstants.MOD_ID, SighConfig.class);
    }
}
