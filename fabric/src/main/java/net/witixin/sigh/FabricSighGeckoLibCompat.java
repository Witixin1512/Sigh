package net.witixin.sigh;

import net.minecraft.world.entity.LivingEntity;
import net.witixin.sigh.compat.GeckoLibCompatHandler;
import software.bernie.geckolib.event.GeoRenderEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class FabricSighGeckoLibCompat {


    public static void init() {
        GeoRenderEvent.Entity.Pre.EVENT.register(event -> {
            if (event.getEntity() instanceof LivingEntity livingEntity) {
                float partialTick = event.getPartialTick();
                if (SighCommon.shouldMobShakeHead(livingEntity)) {
                    boolean stopped = SighCommon.increaseShakingTime(livingEntity, partialTick);
                    GeckoLibCompatHandler.shakeHead(event.getModel(), event.getRenderer().getAnimatable(), partialTick);
                }
            }
            return true;
        });
    }
}
