package com.hbn.outvoted.world.gen;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.config.OutvotedConfig;
import com.hbn.outvoted.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (OutvotedConfig.COMMON.spawninferno.get()) {
                if (biome.getCategory() == Biome.Category.NETHER) {
                    biome.getSpawns(EntityClassification.MONSTER)
                            .add(new Biome.SpawnListEntry(EntityType.BLAZE, 10, 3, 5));
                }
            }
            if (OutvotedConfig.COMMON.spawnhunger.get()) {
                if (biome.getCategory() == Biome.Category.DESERT) {
                    biome.getSpawns(EntityClassification.CREATURE)
                            .add(new Biome.SpawnListEntry(ModEntityTypes.HUNGER.get(), 90, 1, 2));
                } else if (biome.getCategory() == Biome.Category.SWAMP) {
                    biome.getSpawns(EntityClassification.CREATURE)
                            .add(new Biome.SpawnListEntry(ModEntityTypes.HUNGER.get(), 70, 1, 2));
                }
            }
            if (OutvotedConfig.COMMON.spawnkraken.get()) {
                if (biome.getCategory() == Biome.Category.OCEAN) {
                    biome.getSpawns(EntityClassification.WATER_CREATURE)
                            .add(new Biome.SpawnListEntry(ModEntityTypes.KRAKEN.get(), 10, 1, 1));
                }
            }
        }
    }
}
