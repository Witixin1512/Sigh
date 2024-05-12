package net.witixin.sigh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceLoader;

public class SighConstants {

    public static final String MOD_ID = "sigh";
    public static final String MOD_NAME = "Sigh";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static final SighPlatform PLATFORM = ServiceLoader.load(SighPlatform.class).findFirst().orElseThrow();
}
