package com.hbn.outvoted.client.render;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.client.model.KrakenModel;
import com.hbn.outvoted.entity.KrakenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KrakenRenderer extends MobRenderer<KrakenEntity, KrakenModel<KrakenEntity>> {
    private static final ResourceLocation KRAKEN = new ResourceLocation(Outvoted.MOD_ID, "textures/entity/kraken.png");

    public KrakenRenderer(EntityRendererManager renderManager) {
        super(renderManager, new KrakenModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(KrakenEntity entity) {
        return KRAKEN;
    }
}