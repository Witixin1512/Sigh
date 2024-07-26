package net.witixin.sigh.mixin;

import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AgeableListModel.class)
public interface AgeableListPartsInvoker {

    @Invoker("headParts")
    Iterable<ModelPart> getHeadParts();
}
