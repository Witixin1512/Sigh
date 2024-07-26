package net.witixin.sigh;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = SighConstants.MOD_ID, value = Dist.CLIENT)
@Mod(SighConstants.MOD_ID)
public class SighForge {

    public static ForgeConfigSpec.BooleanValue SHOULD_PLAY_SOUND;

    public SighForge() {
        setupConfig();
    }

    @SubscribeEvent
    public static void handleMobDeath(final LivingDeathEvent event){
        SighCommon.broadcastFriend(event.getEntity());
    }

    private static void setupConfig() {

        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

        configBuilder.comment("Determines whether the Sigh sound should be played when a mob dies.");
        SHOULD_PLAY_SOUND = configBuilder.define("play_sound", false);
        ForgeConfigSpec forgeConfigSpec = configBuilder.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, forgeConfigSpec, "sigh.toml");
    }
}
