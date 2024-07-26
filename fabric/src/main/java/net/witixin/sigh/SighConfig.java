package net.witixin.sigh;

import eu.midnightdust.lib.config.MidnightConfig;

public class SighConfig extends MidnightConfig {
    @MidnightConfig.Entry
    public static boolean shouldPlayVillagerSighSound = false;
}
