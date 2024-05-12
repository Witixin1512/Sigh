package net.witixin.sigh;

import net.fabricmc.loader.api.FabricLoader;

public class FabricSighPlatform implements SighPlatform {
    @Override
    public boolean isModLoaded(String modid){
        return FabricLoader.getInstance().isModLoaded(modid);
    }
}
