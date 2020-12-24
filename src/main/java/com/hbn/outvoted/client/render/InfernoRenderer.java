package com.hbn.outvoted.client.render;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.client.model.InfernoModel;
import com.hbn.outvoted.config.OutvotedConfig;
import com.hbn.outvoted.entity.InfernoEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InfernoRenderer extends MobRenderer<InfernoEntity, InfernoModel<InfernoEntity>> {
    private static final ResourceLocation NETHER = new ResourceLocation(Outvoted.MOD_ID, "textures/entity/inferno.png");
    private static final ResourceLocation SOUL = new ResourceLocation(Outvoted.MOD_ID, "textures/entity/inferno_soul.png");

    public InfernoRenderer(EntityRendererManager renderManager) {
        super(renderManager, new InfernoModel<>(), 0.5F);
    }

    @Override
    protected int getBlockLight(InfernoEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(InfernoEntity entity) {
        if (entity.getVariant() == 0 || !OutvotedConfig.COMMON.infernovariant.get()) {
            return NETHER;
        } else {
            return SOUL;
        }
    }
}