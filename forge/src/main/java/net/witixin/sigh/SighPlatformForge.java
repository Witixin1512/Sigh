package net.witixin.sigh;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.LoadingModList;

public class SighPlatformForge implements SighPlatform {
    @Override
    public boolean isModLoaded(String modid){
        return ModList.get().isLoaded(modid) || LoadingModList.get().getModFileById(modid) != null;
    }
}
