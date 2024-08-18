package net.witixin.sigh.compat;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;

import java.util.Optional;

public class GeckoLibCompatHandler {
    public static <T extends Entity> void shakeHead(BakedGeoModel model, T animatable, float partialTick) {
        Optional<GeoBone> headBone = model.getBone("head");
            if (headBone.isPresent()) {
            GeoBone head = headBone.get();
            float bob = animatable.tickCount + partialTick;
            float zRot = 0.3F + Mth.sin(0.325F * bob);
            float xRot = 0.4F;
            head.markRotationAsChanged();
            rotateBoneRecursively(head, xRot, zRot);
        }
    }

    private static void rotateBoneRecursively(GeoBone bone, float xRot, float zRot) {
        bone.setRotX(xRot);
        bone.setRotZ(zRot);
        for (GeoBone child : bone.getChildBones()) {
            rotateBoneRecursively(child, xRot, zRot);
        }
    }
}


