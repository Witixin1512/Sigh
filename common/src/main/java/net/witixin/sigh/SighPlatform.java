package net.witixin.sigh;

public interface SighPlatform {

    boolean isModLoaded(String modid);

    boolean shouldPlaySound();
}
