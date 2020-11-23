package com.hbn.outvoted.world.gen;

import com.hbn.outvoted.Outvoted;
import com.hbn.outvoted.config.OutvotedConfig;
import com.hbn.outvoted.entities.InfernoEntity;
import com.hbn.outvoted.entities.KrakenEntity;
import com.hbn.outvoted.init.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChangeEntitySpawns {
    /**
     * Checks Kraken entities in an area to limit spawn count
     */
    @SubscribeEvent
    public static void checkMobs(LivingSpawnEvent.CheckSpawn event) { // Below is probably bad practice, but I don't know of any other way to force 1 mob
        double area = 6.0; // Value for x, 2*y, and z expansion to check for entities; a variable in case it causes lag or something
        Entity e = event.getEntity();
        if (OutvotedConfig.COMMON.spawnkraken.get()) {
            if (e instanceof KrakenEntity) {
                if (event.getSpawnReason() == SpawnReason.NATURAL) {
                    List<Entity> entities = event.getWorld().getEntitiesWithinAABBExcludingEntity(event.getEntity(), event.getEntity().getBoundingBox().expand(area, area / 2, area).expand(-area, -area / 2, -area));
                    if (!entities.isEmpty()) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
    }

    /**
     * Add Inferno entities to large enough Blaze groups and to Mob Spawners
     */
    @SubscribeEvent
    public static void changeMobs(LivingSpawnEvent.SpecialSpawn event) {
        double area = 6.0D; // Value for x, 2*y, and z expansion to check for entities; a variable in case it causes lag or something
        Entity e = event.getEntity();
        if (OutvotedConfig.COMMON.spawninferno.get()) {
            if (e instanceof BlazeEntity) {
                if (event.getSpawnReason() == SpawnReason.NATURAL) {
                    List<Entity> allentities = event.getWorld().getEntitiesWithinAABBExcludingEntity(e, e.getBoundingBox().expand(area, area / 2, area).expand(-area, -area / 2, -area));
                    List<Entity> entities = new ArrayList<Entity>();
                    for (Entity entity : allentities) {
                        if (entity instanceof BlazeEntity) {
                            entities.add(entity);
                        }
                    }
                    if (entities.size() > 1 && allentities.stream().noneMatch(entity -> entity instanceof InfernoEntity)) {
                        World world = event.getEntity().getEntityWorld();
                        InfernoEntity inferno = ModEntityTypes.INFERNO.get().create(world);
                        //inferno.setPositionAndRotation(e.getPosXRandom(1.0D), e.getPosY(), e.getPosZRandom(1.0D), e.rotationYaw, e.rotationPitch);
                        inferno.setPositionAndRotation(e.getPosX(), e.getPosY(), e.getPosZ(), e.rotationYaw, e.rotationPitch);

                        world.addEntity(inferno);
                    }
                } else if (event.getSpawnReason() == SpawnReason.SPAWNER) {
                    if (Math.random() > 0.8) {
                        World world = event.getEntity().getEntityWorld();

                        InfernoEntity inferno = ModEntityTypes.INFERNO.get().create(world);
                        inferno.setPositionAndRotation(e.getPosXRandom(1.0D), e.getPosY(), e.getPosZRandom(2.0D), e.rotationYaw, e.rotationPitch);

                        world.addEntity(inferno);
                    }
                }
            }
        }
    }
}
