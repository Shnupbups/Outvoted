package io.github.how_bout_no.outvoted.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HollowLogBlock extends PillarBlock {
    public static final VoxelShape NORTH_FACE = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 16.0d, 1.0d);
    public static final VoxelShape EAST_FACE = Block.createCuboidShape(15.0d, 0.0d, 0.0d, 16.0d, 16.0d, 16.0d);
    public static final VoxelShape SOUTH_FACE = Block.createCuboidShape(0.0d, 0.0d, 15.0d, 16.0d, 16.0d, 16.0d);
    public static final VoxelShape WEST_FACE = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 1.0d, 16.0d, 16.0d);
    public static final VoxelShape BOTTOM_FACE = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d);
    public static final VoxelShape TOP_FACE = Block.createCuboidShape(0.0d, 15.0d, 0.0d, 16.0d, 16.0d, 16.0d);
    public static final VoxelShape Y_SHAPE = VoxelShapes.combine(VoxelShapes.combine(NORTH_FACE, EAST_FACE, BooleanBiFunction.OR), VoxelShapes.combine(SOUTH_FACE, WEST_FACE, BooleanBiFunction.OR), BooleanBiFunction.OR);
    public static final VoxelShape X_SHAPE = VoxelShapes.combine(VoxelShapes.combine(NORTH_FACE, BOTTOM_FACE, BooleanBiFunction.OR), VoxelShapes.combine(SOUTH_FACE, TOP_FACE, BooleanBiFunction.OR), BooleanBiFunction.OR);
    public static final VoxelShape Z_SHAPE = VoxelShapes.combine(VoxelShapes.combine(EAST_FACE, BOTTOM_FACE, BooleanBiFunction.OR), VoxelShapes.combine(WEST_FACE, TOP_FACE, BooleanBiFunction.OR), BooleanBiFunction.OR);

    public HollowLogBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch(state.get(AXIS)) {
            case X -> X_SHAPE;
            case Y -> Y_SHAPE;
            case Z -> Z_SHAPE;
        };
    }
}
