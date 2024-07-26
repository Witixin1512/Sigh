package net.witixin.sigh;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.witixin.sigh.mixin.AgeableListPartsInvoker;

public class SighClientHandler {

    public static void handleHeadShaking(LivingEntity entity, float bob, float partialTick, EntityModel<? extends LivingEntity> model,
                                         boolean stopShaking){
        if(model instanceof AgeableListModel ageableListModel) {
            for(ModelPart part : ((AgeableListPartsInvoker) ageableListModel).getHeadParts()) {
               shakePart(part, stopShaking, bob);
            }
        } else if(model instanceof HierarchicalModel hierarchicalModel) {
            if(hierarchicalModel.root().hasChild("head")) {
                ModelPart head = hierarchicalModel.root().getChild("head");
                shakePart(head, stopShaking, bob);
            }
        } else if(model instanceof LlamaModel llamaModel) {
            shakePart(llamaModel.head, stopShaking, bob);
        } else if(model instanceof RabbitModel rabbitModel) {
            shakePart(rabbitModel.head, stopShaking, bob);
            shakePart(rabbitModel.nose, stopShaking, bob);
            shakePart(rabbitModel.leftEar, stopShaking, bob);
            shakePart(rabbitModel.rightEar, stopShaking, bob);
        }
    }

    private static void shakePart(ModelPart part, final boolean stopShaking, final float bob) {
        if (stopShaking) part.resetPose();
        else {
            part.zRot = 0.3F + Mth.sin(0.325F * bob);
            part.xRot = 0.4F;
        }
    }
}
