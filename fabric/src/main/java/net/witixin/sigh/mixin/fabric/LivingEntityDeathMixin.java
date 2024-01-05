package net.witixin.sigh.mixin.fabric;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.witixin.sigh.SighCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDeathMixin extends Entity {
    public LivingEntityDeathMixin(EntityType<?> entityType, Level level){
        super(entityType, level);
    }

    @Inject(method = "die(Lnet/minecraft/world/damagesource/DamageSource;)V", at=@At("TAIL"))
    private void sigh_onDeath(DamageSource source, CallbackInfo callbackInfo) {
        if (this.level().isClientSide) {
            SighCommon.broadcastFriend((LivingEntity) (Object)this);
        }
    }
}
