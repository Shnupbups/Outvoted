package com.hbn.outvoted.world.gen;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.entities.InfernoEntity;
import com.hbn.outvoted.init.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(BiomeLoadingEvent event) {
        if (event.getName().toString().equals("minecraft:nether_wastes")) {
            event.getSpawns().withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.BLAZE, 10, 5, 7));
        }
    }

    @SubscribeEvent
    public static void changeInferno(LivingSpawnEvent.SpecialSpawn event) {
        Entity e = event.getEntity();
        if (e instanceof BlazeEntity) {
            if (event.getSpawnReason() == SpawnReason.NATURAL) {
                if (Math.random() > 0.8) {
                    World world = event.getEntity().getEntityWorld();

                    InfernoEntity inferno = ModEntityTypes.INFERNO.get().create(world);
                    inferno.setPositionAndRotation(e.getPosX(), e.getPosY(), e.getPosZ(), e.rotationYaw, e.rotationPitch);

                    world.addEntity(inferno);
                }
            }
        }
    }
}
