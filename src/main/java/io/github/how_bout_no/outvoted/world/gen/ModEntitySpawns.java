package io.github.how_bout_no.outvoted.world.gen;

import io.github.how_bout_no.outvoted.config.OutvotedConfig;
import io.github.how_bout_no.outvoted.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntitySpawns {
    /**
     * Adds entity spawns to biomes
     */
    public static void spawnEntities() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            String biomename = biome.getRegistryName().toString();
            if (OutvotedConfig.COMMON.spawninferno.get()) {
                if (biome.getCategory() == Biome.Category.NETHER) {
                    if (!OutvotedConfig.COMMON.restrictinferno.get()) {
                        biome.getSpawns(EntityClassification.MONSTER)
                                .add(new Biome.SpawnListEntry(ModEntityTypes.INFERNO.get(), OutvotedConfig.COMMON.rateinferno.get(), 1, 1));
                    } else if (biomename.equals("minecraft:nether_wastes")) {
                        biome.getSpawns(EntityClassification.MONSTER)
                                .add(new Biome.SpawnListEntry(ModEntityTypes.INFERNO.get(), OutvotedConfig.COMMON.rateinferno.get(), 1, 1));
                    }
                }
            }
            if (OutvotedConfig.COMMON.spawnhunger.get()) {
                if (biome.getCategory() == Biome.Category.DESERT || biome.getCategory() == Biome.Category.SWAMP) {
                    biome.getSpawns(EntityClassification.MONSTER)
                            .add(new Biome.SpawnListEntry(ModEntityTypes.HUNGER.get(), OutvotedConfig.COMMON.ratehunger.get(), 1, 1));
                }
            }
            if (OutvotedConfig.COMMON.spawnkraken.get()) {
                if (biome.getDepth() == -1.8F && !biomename.equals("minecraft:deep_frozen_ocean")) { // Possibly makes modded deep oceans compatible? (If those even exist, and use vanilla values)
                    biome.getSpawns(EntityClassification.WATER_CREATURE)
                            .add(new Biome.SpawnListEntry(ModEntityTypes.KRAKEN.get(), OutvotedConfig.COMMON.ratekraken.get(), 1, 1));
                }
            }
        }
    }
}
