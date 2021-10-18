package io.github.how_bout_no.outvoted.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public interface TermiteInfested {
    Map<Block, Block> REGULAR_TO_INFESTED_BLOCK = Maps.newIdentityHashMap();

    static void spawnTermite(ServerWorld world, BlockPos pos) {
        /*
        TermiteEntity termiteEntity = (TermiteEntity) ModEntityTypes.TERMITE.create(world);
        termiteEntity.refreshPositionAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.spawnEntity(termiteEntity);
        termiteEntity.playSpawnEffects();
        */
    }

    static BlockState copyProperties(Map<BlockState, BlockState> stateMap, BlockState fromState, Supplier<BlockState> toStateSupplier) {
        return stateMap.computeIfAbsent(fromState, (infestedState) -> {
            BlockState blockState = toStateSupplier.get();

            Property property;
            for(Iterator var3 = infestedState.getProperties().iterator(); var3.hasNext(); blockState = blockState.contains(property) ? blockState.with(property, infestedState.get(property)) : blockState) {
                property = (Property)var3.next();
            }

            return blockState;
        });
    }

    static Block fromRegularBlock(Block regularBlock) {
        return REGULAR_TO_INFESTED_BLOCK.get(regularBlock);
    }

    static boolean isInfestable(BlockState state) {
        return REGULAR_TO_INFESTED_BLOCK.containsKey(state.getBlock());
    }

    default BlockState toRegularState(BlockState infestedState) {
        return copyProperties(this.getInfestedToRegularStateMap(), infestedState, () -> {
            return this.getRegularBlock().getDefaultState();
        });
    }

    default BlockState toEatenState(BlockState infestedState) {
        return copyProperties(getInfestedToEatenStateMap(), infestedState, () -> {
            return this.getEatenBlock().getDefaultState();
        });
    }

    default BlockState fromRegularState(BlockState regularState) {
        return TermiteInfested.copyProperties(getRegularToInfestedStateMap(), regularState, () -> {
            return REGULAR_TO_INFESTED_BLOCK.get(regularState.getBlock()).getDefaultState();
        });
    }

    default void tickEating(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float f = random.nextFloat();
        if (f < 0.2F) {
            world.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, state), pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), random.nextInt(5)+2, 0.0d, 0.0d, 0.0d, 0.15d);
            world.playSound(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
            world.setBlockState(pos, this.toEatenState(state));
            TermiteInfested.spawnTermite(world, pos);
        } else if (f < 0.8) {
            world.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, state), pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), random.nextInt(5), 0.0d, 0.0d, 0.0d, 0.15d);
            world.playSound(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, random.nextFloat(), 1.0f, true);
        }
    }

    Block getRegularBlock();

    Block getEatenBlock();

    Map<BlockState, BlockState> getInfestedToRegularStateMap();

    Map<BlockState, BlockState> getInfestedToEatenStateMap();

    Map<BlockState, BlockState> getRegularToInfestedStateMap();
}
