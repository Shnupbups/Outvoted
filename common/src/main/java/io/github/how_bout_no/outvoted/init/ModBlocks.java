package io.github.how_bout_no.outvoted.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.how_bout_no.outvoted.Outvoted;
import io.github.how_bout_no.outvoted.block.*;
import io.github.how_bout_no.outvoted.block.ModBlockItems.ModBlockItem;
import io.github.how_bout_no.outvoted.block.ModBlockItems.ModDecoBlockItem;
import io.github.how_bout_no.outvoted.block.ModBlockItems.ModSignItem;
import io.github.how_bout_no.outvoted.block.ModBlockItems.ModTallBlockItem;
import io.github.how_bout_no.outvoted.block.trees.BaobabTree;
import io.github.how_bout_no.outvoted.block.trees.PalmTree;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Outvoted.MOD_ID, Registry.BLOCK_KEY);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(Outvoted.MOD_ID, Registry.ITEM_KEY);

    // Blocks
    public static final RegistrySupplier<Block> BURROW = BLOCKS.register("burrow", () -> new BurrowBlock(Block.Settings.copy(Blocks.SAND)));

    public static final RegistrySupplier<Block> PALM_PLANKS = BLOCKS.register("palm_planks", () -> new Block(Block.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistrySupplier<Block> PALM_LOG = BLOCKS.register("palm_log", () -> createLogBlock(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> PALM_LEAVES = BLOCKS.register("palm_leaves", () -> new LeavesBlock(Block.Settings.copy(Blocks.JUNGLE_LEAVES)));
    public static final RegistrySupplier<Block> PALM_SAPLING = BLOCKS.register("palm_sapling", () -> new ModSaplingBlock(new PalmTree(), Block.Settings.copy(Blocks.JUNGLE_SAPLING), BlockTags.SAND));
    public static final RegistrySupplier<Block> PALM_WOOD = BLOCKS.register("palm_wood", () -> new PillarBlock(Block.Settings.copy(Blocks.JUNGLE_WOOD)));
    public static final RegistrySupplier<Block> STRIPPED_PALM_LOG = BLOCKS.register("stripped_palm_log", () -> new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_JUNGLE_LOG)));
    public static final RegistrySupplier<Block> STRIPPED_PALM_WOOD = BLOCKS.register("stripped_palm_wood", () -> new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_JUNGLE_WOOD)));
    public static final RegistrySupplier<Block> PALM_STAIRS = BLOCKS.register("palm_stairs", () -> new ModReplaceBlocks.Stairs(PALM_PLANKS.get().getDefaultState(), Block.Settings.copy(Blocks.JUNGLE_STAIRS)));
    public static final RegistrySupplier<Block> PALM_SLAB = BLOCKS.register("palm_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_SLAB)));
    public static final RegistrySupplier<Block> PALM_BUTTON = BLOCKS.register("palm_button", () -> new ModReplaceBlocks.WoodenButton(AbstractBlock.Settings.copy(Blocks.JUNGLE_BUTTON)));
    public static final RegistrySupplier<Block> PALM_PRESSURE_PLATE = BLOCKS.register("palm_pressure_plate", () -> new ModReplaceBlocks.PressurePlate(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.JUNGLE_FENCE_GATE)));
    public static final RegistrySupplier<Block> PALM_FENCE = BLOCKS.register("palm_fence", () -> new FenceBlock(Block.Settings.copy(Blocks.JUNGLE_FENCE)));
    public static final RegistrySupplier<Block> PALM_FENCE_GATE = BLOCKS.register("palm_fence_gate", () -> new FenceGateBlock(Block.Settings.copy(Blocks.JUNGLE_FENCE_GATE)));
    public static final RegistrySupplier<Block> PALM_TRAPDOOR = BLOCKS.register("palm_trapdoor", () -> new ModReplaceBlocks.Trapdoor(Block.Settings.copy(Blocks.JUNGLE_TRAPDOOR)));
    public static final RegistrySupplier<Block> PALM_DOOR = BLOCKS.register("palm_door", () -> new ModReplaceBlocks.Door(Block.Settings.copy(Blocks.JUNGLE_DOOR)));
    public static final RegistrySupplier<Block> PALM_SIGN = BLOCKS.register("palm_sign", () -> new SignBlock(Block.Settings.copy(Blocks.JUNGLE_SIGN), ModSignType.PALM));
    public static final RegistrySupplier<Block> PALM_WALL_SIGN = BLOCKS.register("palm_wall_sign", () -> new WallSignBlock(Block.Settings.copy(Blocks.JUNGLE_WALL_SIGN), ModSignType.PALM));

    public static final RegistrySupplier<Block> BAOBAB_PLANKS = BLOCKS.register("baobab_planks", () -> new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.ORANGE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> BAOBAB_LOG = BLOCKS.register("baobab_log", () -> createLogBlock(MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final RegistrySupplier<Block> BAOBAB_LEAVES = BLOCKS.register("baobab_leaves", () -> new LeavesBlock(Block.Settings.copy(Blocks.ACACIA_LEAVES)));
    public static final RegistrySupplier<Block> BAOBAB_SAPLING = BLOCKS.register("baobab_sapling", () -> new ModSaplingBlock(new BaobabTree(), Block.Settings.copy(Blocks.ACACIA_SAPLING)));
    public static final RegistrySupplier<Block> BAOBAB_WOOD = BLOCKS.register("baobab_wood", () -> new PillarBlock(Block.Settings.copy(Blocks.ACACIA_WOOD)));
    public static final RegistrySupplier<Block> STRIPPED_BAOBAB_LOG = BLOCKS.register("stripped_baobab_log", () -> new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_ACACIA_LOG)));
    public static final RegistrySupplier<Block> STRIPPED_BAOBAB_WOOD = BLOCKS.register("stripped_baobab_wood", () -> new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_ACACIA_WOOD)));
    public static final RegistrySupplier<Block> BAOBAB_STAIRS = BLOCKS.register("baobab_stairs", () -> new ModReplaceBlocks.Stairs(BAOBAB_PLANKS.get().getDefaultState(), Block.Settings.copy(Blocks.ACACIA_STAIRS)));
    public static final RegistrySupplier<Block> BAOBAB_SLAB = BLOCKS.register("baobab_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB)));
    public static final RegistrySupplier<Block> BAOBAB_BUTTON = BLOCKS.register("baobab_button", () -> new ModReplaceBlocks.WoodenButton(AbstractBlock.Settings.copy(Blocks.ACACIA_BUTTON)));
    public static final RegistrySupplier<Block> BAOBAB_PRESSURE_PLATE = BLOCKS.register("baobab_pressure_plate", () -> new ModReplaceBlocks.PressurePlate(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.ACACIA_FENCE_GATE)));
    public static final RegistrySupplier<Block> BAOBAB_FENCE = BLOCKS.register("baobab_fence", () -> new FenceBlock(Block.Settings.copy(Blocks.ACACIA_FENCE)));
    public static final RegistrySupplier<Block> BAOBAB_FENCE_GATE = BLOCKS.register("baobab_fence_gate", () -> new FenceGateBlock(Block.Settings.copy(Blocks.ACACIA_FENCE_GATE)));
    public static final RegistrySupplier<Block> BAOBAB_TRAPDOOR = BLOCKS.register("baobab_trapdoor", () -> new ModReplaceBlocks.Trapdoor(Block.Settings.copy(Blocks.ACACIA_TRAPDOOR)));
    public static final RegistrySupplier<Block> BAOBAB_DOOR = BLOCKS.register("baobab_door", () -> new ModReplaceBlocks.Door(Block.Settings.copy(Blocks.ACACIA_DOOR)));
    public static final RegistrySupplier<Block> BAOBAB_SIGN = BLOCKS.register("baobab_sign", () -> new SignBlock(Block.Settings.copy(Blocks.ACACIA_SIGN), ModSignType.BAOBAB));
    public static final RegistrySupplier<Block> BAOBAB_WALL_SIGN = BLOCKS.register("baobab_wall_sign", () -> new WallSignBlock(Block.Settings.copy(Blocks.ACACIA_WALL_SIGN), ModSignType.BAOBAB));

    public static final RegistrySupplier<Block> HOLLOW_OAK_LOG = BLOCKS.register("hollow_oak_log", () -> createHollowLogBlock(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> HOLLOW_SPRUCE_LOG = BLOCKS.register("hollow_spruce_log", () -> createHollowLogBlock(MapColor.SPRUCE_BROWN, MapColor.BROWN));
    public static final RegistrySupplier<Block> HOLLOW_BIRCH_LOG = BLOCKS.register("hollow_birch_log", () -> createHollowLogBlock(MapColor.PALE_YELLOW, MapColor.OFF_WHITE));
    public static final RegistrySupplier<Block> HOLLOW_JUNGLE_LOG = BLOCKS.register("hollow_jungle_log", () -> createHollowLogBlock(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> HOLLOW_ACACIA_LOG = BLOCKS.register("hollow_acacia_log", () -> createHollowLogBlock(MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final RegistrySupplier<Block> HOLLOW_DARK_OAK_LOG = BLOCKS.register("hollow_dark_oak_log", () -> createHollowLogBlock(MapColor.BROWN, MapColor.BROWN));
    public static final RegistrySupplier<Block> HOLLOW_PALM_LOG = BLOCKS.register("hollow_palm_log", () -> createHollowLogBlock(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> HOLLOW_BAOBAB_LOG = BLOCKS.register("hollow_baobab_log", () -> createHollowLogBlock(MapColor.ORANGE, MapColor.STONE_GRAY));

    public static final RegistrySupplier<Block> INFESTED_OAK_LOG = BLOCKS.register("infested_oak_log", () -> createInfestedLogBlock(Blocks.OAK_LOG, HOLLOW_OAK_LOG.get(), MapColor.OAK_TAN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> INFESTED_SPRUCE_LOG = BLOCKS.register("infested_spruce_log", () -> createInfestedLogBlock(Blocks.SPRUCE_LOG, HOLLOW_SPRUCE_LOG.get(), MapColor.SPRUCE_BROWN, MapColor.BROWN));
    public static final RegistrySupplier<Block> INFESTED_BIRCH_LOG = BLOCKS.register("infested_birch_log", () -> createInfestedLogBlock(Blocks.BIRCH_LOG, HOLLOW_BIRCH_LOG.get(), MapColor.PALE_YELLOW, MapColor.OFF_WHITE));
    public static final RegistrySupplier<Block> INFESTED_JUNGLE_LOG = BLOCKS.register("infested_jungle_log", () -> createInfestedLogBlock(Blocks.JUNGLE_LOG, HOLLOW_JUNGLE_LOG.get(), MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> INFESTED_ACACIA_LOG = BLOCKS.register("infested_acacia_log", () -> createInfestedLogBlock(Blocks.ACACIA_LOG, HOLLOW_ACACIA_LOG.get(), MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final RegistrySupplier<Block> INFESTED_DARK_OAK_LOG = BLOCKS.register("infested_dark_oak_log", () -> createInfestedLogBlock(Blocks.DARK_OAK_LOG, HOLLOW_DARK_OAK_LOG.get(), MapColor.BROWN, MapColor.BROWN));
    public static final RegistrySupplier<Block> INFESTED_PALM_LOG = BLOCKS.register("infested_palm_log", () -> createInfestedLogBlock(PALM_LOG.get(), HOLLOW_PALM_LOG.get(), MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final RegistrySupplier<Block> INFESTED_BAOBAB_LOG = BLOCKS.register("infested_baobab_log", () -> createInfestedLogBlock(BAOBAB_LOG.get(), HOLLOW_BAOBAB_LOG.get(), MapColor.ORANGE, MapColor.STONE_GRAY));

    public static final RegistrySupplier<Block> UNSTABLE_OAK_PLANKS = BLOCKS.register("unstable_oak_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> UNSTABLE_SPRUCE_PLANKS = BLOCKS.register("unstable_spruce_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistrySupplier<Block> UNSTABLE_BIRCH_PLANKS = BLOCKS.register("unstable_birch_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistrySupplier<Block> UNSTABLE_JUNGLE_PLANKS = BLOCKS.register("unstable_jungle_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistrySupplier<Block> UNSTABLE_ACACIA_PLANKS = BLOCKS.register("unstable_acacia_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistrySupplier<Block> UNSTABLE_DARK_OAK_PLANKS = BLOCKS.register("unstable_dark_oak_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistrySupplier<Block> UNSTABLE_PALM_PLANKS = BLOCKS.register("unstable_palm_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(PALM_PLANKS.get())));
    public static final RegistrySupplier<Block> UNSTABLE_BAOBAB_PLANKS = BLOCKS.register("unstable_baobab_planks", () -> new UnstablePlanksBlock(AbstractBlock.Settings.copy(BAOBAB_PLANKS.get())));

    public static final RegistrySupplier<Block> INFESTED_OAK_PLANKS = BLOCKS.register("infested_oak_planks", () -> new InfestedPlanksBlock(Blocks.OAK_PLANKS, UNSTABLE_OAK_PLANKS.get(), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> INFESTED_SPRUCE_PLANKS = BLOCKS.register("infested_spruce_planks", () -> new InfestedPlanksBlock(Blocks.SPRUCE_PLANKS, UNSTABLE_SPRUCE_PLANKS.get(), AbstractBlock.Settings.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistrySupplier<Block> INFESTED_BIRCH_PLANKS = BLOCKS.register("infested_birch_planks", () -> new InfestedPlanksBlock(Blocks.BIRCH_PLANKS, UNSTABLE_BIRCH_PLANKS.get(), AbstractBlock.Settings.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistrySupplier<Block> INFESTED_JUNGLE_PLANKS = BLOCKS.register("infested_jungle_planks", () -> new InfestedPlanksBlock(Blocks.JUNGLE_PLANKS, UNSTABLE_JUNGLE_PLANKS.get(), AbstractBlock.Settings.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistrySupplier<Block> INFESTED_ACACIA_PLANKS = BLOCKS.register("infested_acacia_planks", () -> new InfestedPlanksBlock(Blocks.ACACIA_PLANKS, UNSTABLE_ACACIA_PLANKS.get(), AbstractBlock.Settings.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistrySupplier<Block> INFESTED_DARK_OAK_PLANKS = BLOCKS.register("infested_dark_oak_planks", () -> new InfestedPlanksBlock(Blocks.DARK_OAK_PLANKS, UNSTABLE_DARK_OAK_PLANKS.get(), AbstractBlock.Settings.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistrySupplier<Block> INFESTED_PALM_PLANKS = BLOCKS.register("infested_palm_planks", () -> new InfestedPlanksBlock(PALM_PLANKS.get(), UNSTABLE_PALM_PLANKS.get(), AbstractBlock.Settings.copy(PALM_PLANKS.get())));
    public static final RegistrySupplier<Block> INFESTED_BAOBAB_PLANKS = BLOCKS.register("infested_baobab_planks", () -> new InfestedPlanksBlock(BAOBAB_PLANKS.get(), UNSTABLE_BAOBAB_PLANKS.get(), AbstractBlock.Settings.copy(BAOBAB_PLANKS.get())));
    
    // Block items
    public static final RegistrySupplier<Item> BURROW_ITEM = BLOCK_ITEMS.register("burrow", () -> new ModBlockItem(BURROW.get(), new Item.Settings()));

    public static final RegistrySupplier<Item> PALM_PLANKS_ITEM = BLOCK_ITEMS.register("palm_planks", () -> new ModBlockItem(PALM_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_LOG_ITEM = BLOCK_ITEMS.register("palm_log", () -> new ModBlockItem(PALM_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_LEAVES_ITEM = BLOCK_ITEMS.register("palm_leaves", () -> new ModBlockItem(PALM_LEAVES.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_SAPLING_ITEM = BLOCK_ITEMS.register("palm_sapling", () -> new ModDecoBlockItem(PALM_SAPLING.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_WOOD_ITEM = BLOCK_ITEMS.register("palm_wood", () -> new ModBlockItem(PALM_WOOD.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> STRIPPED_PALM_LOG_ITEM = BLOCK_ITEMS.register("stripped_palm_log", () -> new ModBlockItem(STRIPPED_PALM_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> STRIPPED_PALM_WOOD_ITEM = BLOCK_ITEMS.register("stripped_palm_wood", () -> new ModBlockItem(STRIPPED_PALM_WOOD.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_STAIRS_ITEM = BLOCK_ITEMS.register("palm_stairs", () -> new ModBlockItem(PALM_STAIRS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_SLAB_ITEM = BLOCK_ITEMS.register("palm_slab", () -> new ModBlockItem(PALM_SLAB.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_BUTTON_ITEM = BLOCK_ITEMS.register("palm_button", () -> new ModBlockItem(PALM_BUTTON.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_PRESSURE_PLATE_ITEM = BLOCK_ITEMS.register("palm_pressure_plate", () -> new ModBlockItem(PALM_PRESSURE_PLATE.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_FENCE_ITEM = BLOCK_ITEMS.register("palm_fence", () -> new ModBlockItem(PALM_FENCE.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_FENCE_GATE_ITEM = BLOCK_ITEMS.register("palm_fence_gate", () -> new ModBlockItem(PALM_FENCE_GATE.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_TRAPDOOR_ITEM = BLOCK_ITEMS.register("palm_trapdoor", () -> new ModBlockItem(PALM_TRAPDOOR.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_DOOR_ITEM = BLOCK_ITEMS.register("palm_door", () -> new ModTallBlockItem(PALM_DOOR.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> PALM_SIGN_ITEM = BLOCK_ITEMS.register("palm_sign", () -> new ModSignItem(new Item.Settings(), PALM_SIGN.get(), PALM_WALL_SIGN.get()));

    public static final RegistrySupplier<Item> BAOBAB_PLANKS_ITEM = BLOCK_ITEMS.register("baobab_planks", () -> new ModBlockItem(BAOBAB_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_LOG_ITEM = BLOCK_ITEMS.register("baobab_log", () -> new ModBlockItem(BAOBAB_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_LEAVES_ITEM = BLOCK_ITEMS.register("baobab_leaves", () -> new ModBlockItem(BAOBAB_LEAVES.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_SAPLING_ITEM = BLOCK_ITEMS.register("baobab_sapling", () -> new ModDecoBlockItem(BAOBAB_SAPLING.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_WOOD_ITEM = BLOCK_ITEMS.register("baobab_wood", () -> new ModBlockItem(BAOBAB_WOOD.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> STRIPPED_BAOBAB_LOG_ITEM = BLOCK_ITEMS.register("stripped_baobab_log", () -> new ModBlockItem(STRIPPED_BAOBAB_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> STRIPPED_BAOBAB_WOOD_ITEM = BLOCK_ITEMS.register("stripped_baobab_wood", () -> new ModBlockItem(STRIPPED_BAOBAB_WOOD.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_STAIRS_ITEM = BLOCK_ITEMS.register("baobab_stairs", () -> new ModBlockItem(BAOBAB_STAIRS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_SLAB_ITEM = BLOCK_ITEMS.register("baobab_slab", () -> new ModBlockItem(BAOBAB_SLAB.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_BUTTON_ITEM = BLOCK_ITEMS.register("baobab_button", () -> new ModBlockItem(BAOBAB_BUTTON.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_PRESSURE_PLATE_ITEM = BLOCK_ITEMS.register("baobab_pressure_plate", () -> new ModBlockItem(BAOBAB_PRESSURE_PLATE.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_FENCE_ITEM = BLOCK_ITEMS.register("baobab_fence", () -> new ModBlockItem(BAOBAB_FENCE.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_FENCE_GATE_ITEM = BLOCK_ITEMS.register("baobab_fence_gate", () -> new ModBlockItem(BAOBAB_FENCE_GATE.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_TRAPDOOR_ITEM = BLOCK_ITEMS.register("baobab_trapdoor", () -> new ModBlockItem(BAOBAB_TRAPDOOR.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_DOOR_ITEM = BLOCK_ITEMS.register("baobab_door", () -> new ModTallBlockItem(BAOBAB_DOOR.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> BAOBAB_SIGN_ITEM = BLOCK_ITEMS.register("baobab_sign", () -> new ModSignItem(new Item.Settings(), BAOBAB_SIGN.get(), BAOBAB_WALL_SIGN.get()));

    public static final RegistrySupplier<Item> HOLLOW_OAK_LOG_ITEM = BLOCK_ITEMS.register("hollow_oak_log", () -> new ModBlockItem(HOLLOW_OAK_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_SPRUCE_LOG_ITEM = BLOCK_ITEMS.register("hollow_spruce_log", () -> new ModBlockItem(HOLLOW_SPRUCE_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_BIRCH_LOG_ITEM = BLOCK_ITEMS.register("hollow_birch_log", () -> new ModBlockItem(HOLLOW_BIRCH_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_JUNGLE_LOG_ITEM = BLOCK_ITEMS.register("hollow_jungle_log", () -> new ModBlockItem(HOLLOW_JUNGLE_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_ACACIA_LOG_ITEM = BLOCK_ITEMS.register("hollow_acacia_log", () -> new ModBlockItem(HOLLOW_ACACIA_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_DARK_OAK_LOG_ITEM = BLOCK_ITEMS.register("hollow_dark_oak_log", () -> new ModBlockItem(HOLLOW_DARK_OAK_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_PALM_LOG_ITEM = BLOCK_ITEMS.register("hollow_palm_log", () -> new ModBlockItem(HOLLOW_PALM_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> HOLLOW_BAOBAB_LOG_ITEM = BLOCK_ITEMS.register("hollow_baobab_log", () -> new ModBlockItem(HOLLOW_BAOBAB_LOG.get(), new Item.Settings()));

    public static final RegistrySupplier<Item> INFESTED_OAK_LOG_ITEM = BLOCK_ITEMS.register("infested_oak_log", () -> new ModBlockItem(INFESTED_OAK_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_SPRUCE_LOG_ITEM = BLOCK_ITEMS.register("infested_spruce_log", () -> new ModBlockItem(INFESTED_SPRUCE_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_BIRCH_LOG_ITEM = BLOCK_ITEMS.register("infested_birch_log", () -> new ModBlockItem(INFESTED_BIRCH_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_JUNGLE_LOG_ITEM = BLOCK_ITEMS.register("infested_jungle_log", () -> new ModBlockItem(INFESTED_JUNGLE_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_ACACIA_LOG_ITEM = BLOCK_ITEMS.register("infested_acacia_log", () -> new ModBlockItem(INFESTED_ACACIA_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_DARK_OAK_LOG_ITEM = BLOCK_ITEMS.register("infested_dark_oak_log", () -> new ModBlockItem(INFESTED_DARK_OAK_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_PALM_LOG_ITEM = BLOCK_ITEMS.register("infested_palm_log", () -> new ModBlockItem(INFESTED_PALM_LOG.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_BAOBAB_LOG_ITEM = BLOCK_ITEMS.register("infested_baobab_log", () -> new ModBlockItem(INFESTED_BAOBAB_LOG.get(), new Item.Settings()));

    public static final RegistrySupplier<Item> UNSTABLE_OAK_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_oak_planks", () -> new ModBlockItem(UNSTABLE_OAK_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_SPRUCE_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_spruce_planks", () -> new ModBlockItem(UNSTABLE_SPRUCE_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_BIRCH_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_birch_planks", () -> new ModBlockItem(UNSTABLE_BIRCH_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_JUNGLE_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_jungle_planks", () -> new ModBlockItem(UNSTABLE_JUNGLE_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_ACACIA_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_acacia_planks", () -> new ModBlockItem(UNSTABLE_ACACIA_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_DARK_OAK_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_dark_oak_planks", () -> new ModBlockItem(UNSTABLE_DARK_OAK_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_PALM_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_palm_planks", () -> new ModBlockItem(UNSTABLE_PALM_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> UNSTABLE_BAOBAB_PLANKS_ITEM = BLOCK_ITEMS.register("unstable_baobab_planks", () -> new ModBlockItem(UNSTABLE_BAOBAB_PLANKS.get(), new Item.Settings()));

    public static final RegistrySupplier<Item> INFESTED_OAK_PLANKS_ITEM = BLOCK_ITEMS.register("infested_oak_planks", () -> new ModBlockItem(INFESTED_OAK_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_SPRUCE_PLANKS_ITEM = BLOCK_ITEMS.register("infested_spruce_planks", () -> new ModBlockItem(INFESTED_SPRUCE_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_BIRCH_PLANKS_ITEM = BLOCK_ITEMS.register("infested_birch_planks", () -> new ModBlockItem(INFESTED_BIRCH_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_JUNGLE_PLANKS_ITEM = BLOCK_ITEMS.register("infested_jungle_planks", () -> new ModBlockItem(INFESTED_JUNGLE_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_ACACIA_PLANKS_ITEM = BLOCK_ITEMS.register("infested_acacia_planks", () -> new ModBlockItem(INFESTED_ACACIA_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_DARK_OAK_PLANKS_ITEM = BLOCK_ITEMS.register("infested_dark_oak_planks", () -> new ModBlockItem(INFESTED_DARK_OAK_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_PALM_PLANKS_ITEM = BLOCK_ITEMS.register("infested_palm_planks", () -> new ModBlockItem(INFESTED_PALM_PLANKS.get(), new Item.Settings()));
    public static final RegistrySupplier<Item> INFESTED_BAOBAB_PLANKS_ITEM = BLOCK_ITEMS.register("infested_baobab_planks", () -> new ModBlockItem(INFESTED_BAOBAB_PLANKS.get(), new Item.Settings()));
    
    private static PillarBlock createLogBlock(MapColor topColor, MapColor barkColor) {
        return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }

    private static HollowLogBlock createHollowLogBlock(MapColor topColor, MapColor barkColor) {
        return new HollowLogBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
        }).strength(1.0F, 0.75F).sounds(BlockSoundGroup.WOOD));
    }

    private static InfestedLogBlock createInfestedLogBlock(Block regularLog, Block hollowLog, MapColor topColor, MapColor barkColor) {
        return new InfestedLogBlock(regularLog, hollowLog, AbstractBlock.Settings.of(Material.WOOD, (state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }
}
