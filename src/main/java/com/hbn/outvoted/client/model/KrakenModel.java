package com.hbn.outvoted.client.model;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.entity.KrakenEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;
import software.bernie.geckolib.entity.IAnimatedEntity;

@OnlyIn(Dist.CLIENT)
public class KrakenModel<T extends KrakenEntity & IAnimatedEntity> extends AnimatedEntityModel<T> {

    private final AnimatedModelRenderer monster;
    private final AnimatedModelRenderer body;
    private final AnimatedModelRenderer tentacles;
    private final AnimatedModelRenderer tent1;
    private final AnimatedModelRenderer tent2;
    private final AnimatedModelRenderer tent3;
    private final AnimatedModelRenderer tent4;
    private final AnimatedModelRenderer tongue;

    public KrakenModel() {
        textureWidth = 256;
        textureHeight = 160;
        monster = new AnimatedModelRenderer(this);
        monster.setRotationPoint(0.0F, 16.0F, 4.0F);
        setRotationAngle(monster, 1.5708F, 0.0F, 0.0F);

        monster.setModelRendererName("monster");
        this.registerModelRenderer(monster);

        body = new AnimatedModelRenderer(this);
        body.setRotationPoint(0.0F, 4.5F, 0.0F);
        monster.addChild(body);
        body.setTextureOffset(0, 56).addBox(-8.0F, -16.5F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
        body.setTextureOffset(0, 100).addBox(-8.0F, -0.5F, -8.0F, 16.0F, 20.0F, 16.0F, 0.0F, false);
        body.setModelRendererName("body");
        this.registerModelRenderer(body);

        tentacles = new AnimatedModelRenderer(this);
        tentacles.setRotationPoint(0.0F, 12.0F, 0.0F);
        monster.addChild(tentacles);

        tentacles.setModelRendererName("tentacles");
        this.registerModelRenderer(tentacles);

        tent1 = new AnimatedModelRenderer(this);
        tent1.setRotationPoint(-4.0F, -16.0F, 0.0F);
        tentacles.addChild(tent1);
        tent1.setTextureOffset(48, 0).addBox(-8.0F, -52.0F, 0.0F, 12.0F, 44.0F, 12.0F, 0.0F, false);
        tent1.setModelRendererName("tent1");
        this.registerModelRenderer(tent1);

        tent2 = new AnimatedModelRenderer(this);
        tent2.setRotationPoint(4.0F, -16.0F, 0.0F);
        tentacles.addChild(tent2);
        tent2.setTextureOffset(96, 0).addBox(-4.0F, -52.0F, 0.0F, 12.0F, 44.0F, 12.0F, 0.0F, false);
        tent2.setModelRendererName("tent2");
        this.registerModelRenderer(tent2);

        tent3 = new AnimatedModelRenderer(this);
        tent3.setRotationPoint(4.0F, -16.0F, 0.0F);
        tentacles.addChild(tent3);
        tent3.setTextureOffset(0, 0).addBox(-4.0F, -52.0F, -12.0F, 12.0F, 44.0F, 12.0F, 0.0F, false);
        tent3.setModelRendererName("tent3");
        this.registerModelRenderer(tent3);

        tent4 = new AnimatedModelRenderer(this);
        tent4.setRotationPoint(-4.0F, -16.0F, 0.0F);
        tentacles.addChild(tent4);
        tent4.setTextureOffset(144, 0).addBox(-8.0F, -52.0F, -12.0F, 12.0F, 44.0F, 12.0F, 0.0F, false);
        tent4.setModelRendererName("tent4");
        this.registerModelRenderer(tent4);

        tongue = new AnimatedModelRenderer(this);
        tongue.setRotationPoint(0.0F, -9.0F, 0.0F);
        monster.addChild(tongue);
        tongue.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        tongue.setModelRendererName("tongue");
        this.registerModelRenderer(tongue);

        this.rootBones.add(monster);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.monster.rotateAngleX = headPitch * ((float) Math.PI / 180F) - 1.7F;
        this.monster.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
    }

    @Override
    public ResourceLocation getAnimationFileLocation() {
        return new ResourceLocation(Outvoted.MOD_ID, "animations/kraken.animation.json");
    }
}
