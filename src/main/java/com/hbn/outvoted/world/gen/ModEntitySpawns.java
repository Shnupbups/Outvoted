package com.hbn.outvoted.world.gen;

import com.hbn.outvoted.Outvoted;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {
    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() == Biome.Category.NETHER) {
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(EntityType.BLAZE, 10, 5, 7));
            }
        }
    }

}

