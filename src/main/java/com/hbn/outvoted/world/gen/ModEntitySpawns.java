package com.hbn.outvoted.world.gen;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.entities.inferno.InfernoEntity;
import com.hbn.outvoted.init.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
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
            } else if (biome.getCategory() == Biome.Category.DESERT) {
                biome.getSpawns(EntityClassification.CREATURE)
                        .add(new Biome.SpawnListEntry(ModEntityTypes.HUNGER.get(), 90, 0, 1));
            }
        }
    }
}
