package io.github.how_bout_no.outvoted.block;

import com.google.common.collect.Maps;
import io.github.how_bout_no.outvoted.entity.TermiteEntity;
import io.github.how_bout_no.outvoted.init.ModEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class InfestedLogBlock extends PillarBlock {
    private final Block regularLog;
    private final Block hollowLog;
    private static final Map<Block, Block> REGULAR_TO_INFESTED_BLOCK = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> REGULAR_TO_INFESTED_STATE = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_REGULAR_STATE = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_HOLLOW_STATE = Maps.newIdentityHashMap();

    public InfestedLogBlock(Block regularLog, Block hollowLog, Settings settings) {
        super(settings.strength(regularLog.getHardness() / 2.0F, 0.75F));
        this.regularLog = regularLog;
        this.hollowLog = hollowLog;
        REGULAR_TO_INFESTED_BLOCK.put(regularLog, this);
    }

    public Block getRegularLog() {
        return this.regularLog;
    }

    public Block getHollowLog() {
        return this.hollowLog;
    }

    public static boolean isInfestable(BlockState block) {
        return REGULAR_TO_INFESTED_BLOCK.containsKey(block.getBlock());
    }

    private void spawnTermite(ServerWorld world, BlockPos pos) {
        /*
        TermiteEntity termiteEntity = (TermiteEntity) ModEntityTypes.TERMITE.create(world);
        termiteEntity.refreshPositionAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
        world.spawnEntity(termiteEntity);
        termiteEntity.playSpawnEffects();
        */
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            this.spawnTermite(world, pos);
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (world instanceof ServerWorld) {
            this.spawnTermite((ServerWorld)world, pos);
        }
    }

    public static BlockState fromRegularState(BlockState regularState) {
        return copyProperties(REGULAR_TO_INFESTED_STATE, regularState, () -> {
            return REGULAR_TO_INFESTED_BLOCK.get(regularState.getBlock()).getDefaultState();
        });
    }

    public BlockState toRegularState(BlockState infestedState) {
        return copyProperties(INFESTED_TO_REGULAR_STATE, infestedState, () -> {
            return this.getRegularLog().getDefaultState();
        });
    }

    public BlockState toHollowState(BlockState infestedState) {
        return copyProperties(INFESTED_TO_HOLLOW_STATE, infestedState, () -> {
            return this.getHollowLog().getDefaultState();
        });
    }

    private static BlockState copyProperties(Map<BlockState, BlockState> stateMap, BlockState fromState, Supplier<BlockState> toStateSupplier) {
        return stateMap.computeIfAbsent(fromState, (infestedState) -> {
            BlockState blockState = toStateSupplier.get();

            Property property;
            for(Iterator var3 = infestedState.getProperties().iterator(); var3.hasNext(); blockState = blockState.contains(property) ? blockState.with(property, infestedState.get(property)) : blockState) {
                property = (Property)var3.next();
            }

            return blockState;
        });
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        float f = random.nextFloat();
        if (f < 0.2F) {
            world.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, state), pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), random.nextInt(5)+2, 0.0d, 0.0d, 0.0d, 0.15d);
            world.playSound(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
            world.setBlockState(pos, this.toHollowState(state));
            this.spawnTermite(world, pos);
        } else if (f < 0.8) {
            world.spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, state), pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), random.nextInt(5), 0.0d, 0.0d, 0.0d, 0.15d);
            world.playSound(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, random.nextFloat(), 1.0f, true);
        }
    }
}
