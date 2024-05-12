package net.witixin.sigh;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = SighConstants.MOD_ID, value = Dist.CLIENT)
@Mod(SighConstants.MOD_ID)
public class SighForge {

    @SubscribeEvent
    public static void handleMobDeath(final LivingDeathEvent event){
        SighCommon.broadcastFriend(event.getEntity());
    }
}
