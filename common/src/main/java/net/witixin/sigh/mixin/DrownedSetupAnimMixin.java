package net.witixin.sigh.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.DrownedOuterLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.witixin.sigh.SighCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderLayer.class)
public abstract class DrownedSetupAnimMixin {

    @Inject(method = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;coloredCutoutModelCopyLayerRender" +
            "(Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/resources/ResourceLocation;" +
            "Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V", shift = At.Shift.AFTER))
    private static void sigh_renderSetupAnim(EntityModel $$0,
                                       EntityModel childModel,
                                       ResourceLocation location,
                                       PoseStack $$3,
                                       MultiBufferSource $$4,
                                       int $$5,
                                       LivingEntity drowned,
                                       float $$7,
                                       float $$8,
                                       float $$9,
                                       float $$10,
                                       float $$11,
                                       float partialTick,
                                       float $$13,
                                       float $$14,
                                       float $$15, CallbackInfo callbackInfo) {
        if(location == DrownedOuterLayer.DROWNED_OUTER_LAYER_LOCATION && SighCommon.shouldMobShakeHead(drowned)) {
            if (SighCommon.shouldResetPart(drowned)) {
                ((DrownedModel)childModel).head.resetPose();
            }
            else {
                ((DrownedModel)childModel).head.zRot = 0.3F + Mth.sin(0.325F * (drowned.tickCount + partialTick));
                ((DrownedModel)childModel).head.xRot = 0.4f;

            }
        }
    }

}
