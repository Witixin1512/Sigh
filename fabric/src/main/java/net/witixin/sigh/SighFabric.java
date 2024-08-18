package net.witixin.sigh;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class SighFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MidnightConfig.init(SighConstants.MOD_ID, SighConfig.class);

        if (FabricLoader.getInstance().isModLoaded("geckolib")) {
            FabricSighGeckoLibCompat.init();
        }
    }
}
