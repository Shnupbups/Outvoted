package io.github.how_bout_no.outvoted.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Map;
import java.util.Random;

public class InfestedLogBlock extends PillarBlock implements TermiteInfested {
    private final Block regularLog;
    private final Block hollowLog;
    private static final Map<BlockState, BlockState> REGULAR_TO_INFESTED_STATE = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_REGULAR_STATE = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_HOLLOW_STATE = Maps.newIdentityHashMap();

    public InfestedLogBlock(Block regularLog, Block hollowLog, Settings settings) {
        super(settings.strength(regularLog.getHardness() / 2.0F, 0.75F));
        this.regularLog = regularLog;
        this.hollowLog = hollowLog;
        REGULAR_TO_INFESTED_BLOCK.put(regularLog, this);
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            TermiteInfested.spawnTermite(world, pos);
        }
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (world instanceof ServerWorld) {
            TermiteInfested.spawnTermite((ServerWorld)world, pos);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        this.tickEating(state, world, pos, random);
    }

    @Override
    public Block getRegularBlock() {
        return regularLog;
    }

    @Override
    public Block getEatenBlock() {
        return hollowLog;
    }

    @Override
    public Map<BlockState, BlockState> getInfestedToRegularStateMap() {
        return INFESTED_TO_REGULAR_STATE;
    }

    @Override
    public Map<BlockState, BlockState> getInfestedToEatenStateMap() {
        return INFESTED_TO_HOLLOW_STATE;
    }

    @Override
    public Map<BlockState, BlockState> getRegularToInfestedStateMap() {
        return REGULAR_TO_INFESTED_STATE;
    }
}
