package com.hbn.outvoted.client.render;

import com.hbn.outvoted.Outvoted;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ShieldRenderer extends ItemStackTileEntityRenderer {

    private final ShieldModel modelShield = new ShieldModel();
    public static final Material base = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Outvoted.MOD_ID, "entity/inferno_shield_base"));

    public void render(ItemStack itemStackIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.scale(1.0F, -1.0F, -1.0F);
        Material material = base;

        IVertexBuilder ivertexbuilder = material.getSprite().wrapBuffer(ItemRenderer.getBuffer(bufferIn, this.modelShield.getRenderType(material.getAtlasLocation()), false, itemStackIn.hasEffect()));
        this.modelShield.func_228294_b_().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
        this.modelShield.func_228293_a_().render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStackIn.pop();
    }


    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            event.addSprite(base.getTextureLocation());
        }
    }

}