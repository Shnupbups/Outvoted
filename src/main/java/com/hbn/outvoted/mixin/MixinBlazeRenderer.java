package com.hbn.outvoted.mixin;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.config.OutvotedConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Replace Blaze textures with soul/blue variant when on soul sand, akin to Infernos
 */
@Mixin(BlazeRenderer.class)
public class MixinBlazeRenderer {

    @Inject(method = "getEntityTexture", at = @At("RETURN"), cancellable = true)
    private void soulTextures(BlazeEntity entity, CallbackInfoReturnable<ResourceLocation> cir) {
        if (!OutvotedConfig.COMMON.infernovariant.get()) return;
        Block block = entity.world.getBlockState(new BlockPos(entity.getPositionVec().add(0D, -0.1D, 0D))).getBlock();
        if (Outvoted.matchesBlock(block, Blocks.SOUL_SAND)) {
            cir.setReturnValue(new ResourceLocation(Outvoted.MOD_ID, "textures/entity/soul_blaze.png"));
        }
    }
}
