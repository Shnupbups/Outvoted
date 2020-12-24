package com.hbn.outvoted.client.render;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.client.model.HungerModel;
import com.hbn.outvoted.entity.HungerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HungerRenderer extends MobRenderer<HungerEntity, HungerModel<HungerEntity>> {
    private static final ResourceLocation SAND = new ResourceLocation(Outvoted.MOD_ID, "textures/entity/hunger.png");
    private static final ResourceLocation RED_SAND = new ResourceLocation(Outvoted.MOD_ID, "textures/entity/hunger_red.png");
    private static final ResourceLocation SWAMP = new ResourceLocation(Outvoted.MOD_ID, "textures/entity/hunger_swamp.png");

    public HungerRenderer(EntityRendererManager renderManager) {
        super(renderManager, new HungerModel<>(), 0.0F);
    }

    @Override
    public ResourceLocation getEntityTexture(HungerEntity entity) {
        if (entity.getVariant() == 1) {
            return RED_SAND;
        } else if (entity.getVariant() == 2) {
            return SWAMP;
        } else {
            return SAND;
        }
    }
}
