package io.github.how_bout_no.outvoted.data;

import io.github.how_bout_no.outvoted.init.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(final DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        getBuilder(ModTags.HUNGER_CAN_BURROW).add(BlockTags.SAND)
                .add(Blocks.GRAVEL, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.MYCELIUM);
    }
}
