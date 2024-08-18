package net.witixin.sigh;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;


public class SighCommon {
    
    public static void broadcastFriend(LivingEntity friend) {
        for(LivingEntity entity : friend.level().getEntitiesOfClass(friend.getClass(), friend.getBoundingBox().inflate(10.0), Entity::isAlive)) {
            SighCommon.onFriendDie(entity);
        }
    }

    private static final Set<Integer> entitiesToShake = new HashSet<>();
    private static final Int2IntMap timeSpentShaking = new Int2IntOpenHashMap();

    public static boolean shouldMobShakeHead(final LivingEntity livingEntity){
        return entitiesToShake.contains(livingEntity.getId());
    }

    public static boolean shouldResetPart(final LivingEntity livingEntity) {
        return timeSpentShaking.get(livingEntity.getId()) == 99;
    }

    public static boolean increaseShakingTime(LivingEntity livingEntity, float partialTick){
        if(partialTick - 1.0f > 0.001) return false;
        int entityId = livingEntity.getId();
        int amount = timeSpentShaking.get(entityId) + 1;
        if(amount == 100) {
            timeSpentShaking.remove(entityId);
            entitiesToShake.remove(entityId);
            return true;
        }
        timeSpentShaking.put(entityId, amount);
        return false;
    }

    public static void onFriendDie(LivingEntity livingEntity){
        if(!livingEntity.isSilent() && SighConstants.PLATFORM.shouldPlaySound()) {
            livingEntity.playSound(SoundEvents.VILLAGER_NO, 16.0f, 1.0F);
        }
        shakeHead(livingEntity);
    }

    private static void shakeHead(LivingEntity livingEntity){
        entitiesToShake.add(livingEntity.getId());
        timeSpentShaking.put(livingEntity.getId(), 0);
    }


}
