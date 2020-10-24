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
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (biome.getCategory() == Biome.Category.NETHER) {
                biome.getSpawns(EntityClassification.CREATURE)
                        .add(new Biome.SpawnListEntry(EntityType.BLAZE, 10, 5, 8));
            }
        }
    }

    // Idk why but this works!
    @SubscribeEvent
    public static void changeInferno(LivingSpawnEvent.SpecialSpawn event) {
        Entity e = event.getEntity();
        if (e instanceof BlazeEntity) {
            if (event.getSpawnReason() == SpawnReason.NATURAL) {
                if (Math.random() > 0.8) {
                    event.setCanceled(true);

                    World world = event.getEntity().getEntityWorld();

                    InfernoEntity inferno = ModEntityTypes.INFERNO.get().create(world);
                    inferno.setPositionAndRotation(e.getPosX(), e.getPosY(), e.getPosZ(), e.rotationYaw, e.rotationPitch);

                    world.addEntity(inferno);
                }
            }
        }
    }
}
