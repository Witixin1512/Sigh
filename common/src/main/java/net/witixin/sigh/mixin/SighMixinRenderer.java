package net.witixin.sigh.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.witixin.sigh.SighClientHandler;
import net.witixin.sigh.SighCommon;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true, print = true)
@Mixin(LivingEntityRenderer.class)
public abstract class SighMixinRenderer extends EntityRenderer {

    private SighMixinRenderer(EntityRendererProvider.Context $$0){
        super($$0);
    }

    @Shadow
    abstract float getBob(LivingEntity entity, float partialTick);

    @Shadow
    abstract EntityModel<? extends LivingEntity> getModel();

    @Inject(method = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V", shift = At.Shift.AFTER))
    private void sigh_injectAfterSetupAnim(LivingEntity entity, float $$1, float partialTick, PoseStack $$3, MultiBufferSource $$4, int $$5, CallbackInfo callbackInfo){
        if(SighCommon.shouldMobShakeHead(entity)) {
            SighClientHandler.handleHeadShaking(entity, getBob(entity, partialTick), partialTick, getModel());

        }
    }
}
