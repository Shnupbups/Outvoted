package com.hbn.outvoted.init;

import com.hbn.outvoted.Outvoted;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class ModTags {
    public static final Tag<Block> HUNGER_CAN_BURROW = new BlockTags.Wrapper(new ResourceLocation(Outvoted.MOD_ID + ":hunger_can_burrow"));
}
