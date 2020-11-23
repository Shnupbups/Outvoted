package com.hbn.outvoted.world.gen;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.config.OutvotedConfig;
import com.hbn.outvoted.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    /**
     * Adds entity spawns to biomes
     */
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            String biomename = biome.getRegistryName().toString();
            if (OutvotedConfig.COMMON.spawninferno.get()) {
                if (biome.getCategory() == Biome.Category.NETHER) {
                    biome.getSpawns(EntityClassification.MONSTER)
                            .add(new Biome.SpawnListEntry(EntityType.BLAZE, OutvotedConfig.COMMON.rateblaze.get(), 3, 4));
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
                    //if (biomename.equals("minecraft:deep_ocean") || biomename.equals("minecraft:deep_warm_ocean") || biomename.equals("minecraft:deep_lukewarm_ocean") || biomename.equals("minecraft:deep_cold_ocean")) {
                    biome.getSpawns(EntityClassification.WATER_CREATURE)
                            .add(new Biome.SpawnListEntry(ModEntityTypes.KRAKEN.get(), OutvotedConfig.COMMON.ratekraken.get(), 1, 1));
                }
            }
        }
    }
}
