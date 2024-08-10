package net.witixin.sigh.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Stray;
import net.witixin.sigh.SighClientHandler;
import net.witixin.sigh.SighCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderLayer.class)
public abstract class FixRenderLayersMixin {

    @Inject(method = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;renderColoredCutoutModel(Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer" +
                    "(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V", shift = At.Shift.BEFORE))
    private static void sigh_renderSetupAnim(EntityModel<LivingEntity> childModel, ResourceLocation location, PoseStack $$2,
                                             MultiBufferSource $$3, int $$4,
                                             LivingEntity livingEntity, float $$6, float $$7, float $$8, CallbackInfo callbackInfo) {
        if(SighClientHandler.KNOWN_VANILLA_RENDER_LAYERS.contains(location) && SighCommon.shouldMobShakeHead(livingEntity)) {

            if (livingEntity instanceof Stray || livingEntity instanceof Drowned || livingEntity instanceof Sheep) {
                if (SighCommon.shouldResetPart(livingEntity)) {
                    for (var part : ((AgeableListPartsInvoker) childModel).getHeadParts())
                        part.resetPose();
                }
                else {
                    for (var part : ((AgeableListPartsInvoker) childModel).getHeadParts()) {
                        part.zRot = 0.3F + Mth.sin(0.325F * (livingEntity.tickCount + Minecraft.getInstance().getFrameTime()));
                        part.xRot = 0.4f;
                    }
                }
            }
        }
    }

}
