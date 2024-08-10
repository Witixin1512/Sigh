package net.witixin.sigh;

import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceLoader;
import java.util.Set;

public class SighConstants {

    public static final String MOD_ID = "sigh";
    public static final String MOD_NAME = "Sigh";

    public static final SighPlatform PLATFORM = ServiceLoader.load(SighPlatform.class).findFirst().orElseThrow();
}
