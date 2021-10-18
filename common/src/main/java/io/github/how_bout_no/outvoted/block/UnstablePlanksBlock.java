package io.github.how_bout_no.outvoted.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class UnstablePlanksBlock extends FallingBlock {
    public UnstablePlanksBlock(Settings settings) {
        super(settings);
    }

    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return this.material.getColor().getRenderColor(0);
    }
}
