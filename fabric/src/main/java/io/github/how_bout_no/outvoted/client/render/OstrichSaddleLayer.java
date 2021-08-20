package io.github.how_bout_no.outvoted.client.render;

import io.github.how_bout_no.outvoted.Outvoted;
import io.github.how_bout_no.outvoted.entity.OstrichEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderer.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderer.geo.IGeoRenderer;

public class OstrichSaddleLayer extends GeoLayerRenderer<OstrichEntity> {
    private static final Identifier SADDLE = new Identifier(Outvoted.MOD_ID, "textures/entity/ostrich/ostrich_saddle.png");

    public OstrichSaddleLayer(IGeoRenderer<OstrichEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, OstrichEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isSaddled()) {
            matrixStackIn.push();
            matrixStackIn.scale(1.01f, 1.01f, 1.01f);
            renderCopyModel((GeoModelProvider<OstrichEntity>) this.getEntityModel(), SADDLE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, partialTicks, 1f, 1f, 1f);
            matrixStackIn.pop();
        }
    }
}