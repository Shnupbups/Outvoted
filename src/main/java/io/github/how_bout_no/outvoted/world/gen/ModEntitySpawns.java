package io.github.how_bout_no.outvoted.world.gen;

import io.github.how_bout_no.outvoted.Outvoted;
import io.github.how_bout_no.outvoted.config.OutvotedConfig;
import io.github.how_bout_no.outvoted.entity.HungerEntity;
import io.github.how_bout_no.outvoted.entity.KrakenEntity;
import io.github.how_bout_no.outvoted.entity.WildfireEntity;
import io.github.how_bout_no.outvoted.init.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Outvoted.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntitySpawns {

    /**
     * Adds entity spawns to biomes
     */
    public static void spawnEntities() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            String biomename = biome.getRegistryName().toString();
            if (OutvotedConfig.COMMON.spawnwildfire.get()) {
                if (biome.getCategory() == Biome.Category.NETHER) {
                    if (!OutvotedConfig.COMMON.restrictwildfire.get() || biomename.equals("minecraft:nether")) {
                        biome.getSpawns(EntityClassification.MONSTER)
                                .add(new Biome.SpawnListEntry(ModEntityTypes.WILDFIRE.get(), OutvotedConfig.COMMON.ratewildfire.get(), 1, 1));
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

    /**
     * Checks entities in an area to force limit spawn count
     * Probably awful practice, but this is a quick and dirty way to force 1 mob in an area
     */
    @SubscribeEvent
    public static void checkMobs(LivingSpawnEvent.CheckSpawn event) {
        double area = 6.0; // Value for x, y, and z expansion to check for entities
        Entity e = event.getEntity();
        if (OutvotedConfig.COMMON.spawnkraken.get() || OutvotedConfig.COMMON.spawnhunger.get()) {
            if (e instanceof KrakenEntity || e instanceof HungerEntity) {
                if (event.getSpawnReason() == SpawnReason.NATURAL) {
                    List<Entity> entities = event.getWorld().getEntitiesWithinAABBExcludingEntity(event.getEntity(), event.getEntity().getBoundingBox().expand(area, area, area).expand(-area, -area, -area));
                    if (!entities.isEmpty()) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
        if (OutvotedConfig.COMMON.spawnwildfire.get()) {
            if (e instanceof WildfireEntity) {
                if (event.getSpawnReason() == SpawnReason.NATURAL && event.getWorld().getDifficulty() != Difficulty.HARD) {
                    List<Entity> entities = event.getWorld().getEntitiesWithinAABBExcludingEntity(event.getEntity(), event.getEntity().getBoundingBox().expand(area, area, area).expand(-area, -area, -area));
                    if (entities.stream().anyMatch(entity -> entity instanceof WildfireEntity)) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
    }

    /**
     * Adds Blazes to Wildfire spawns and adds Wildfires to Mob Spawners
     */
    @SubscribeEvent
    public static void changeMobs(LivingSpawnEvent.SpecialSpawn event) {
        Entity e = event.getEntity();
        if (OutvotedConfig.COMMON.spawnwildfire.get()) {
            if (e instanceof WildfireEntity) {
                if (event.getSpawnReason() == SpawnReason.NATURAL) {
                    World world = event.getEntity().getEntityWorld();
                    int max = 3;
                    switch (world.getDifficulty()) {
                        case NORMAL:
                            max = 4;
                            break;
                        case HARD:
                            max = 5;
                            break;
                    }
                    int min = max - 1;
                    int rand = new Random().nextInt(max - min) + min;
                    for (int i = 1; i <= rand; i++) {
                        BlazeEntity blaze = EntityType.BLAZE.create(world);
                        blaze.setPositionAndRotation(e.getPosXRandom(2.0D), e.getPosY(), e.getPosZRandom(2.0D), e.rotationYaw, e.rotationPitch);
                        while (!world.isAirBlock(blaze.getPosition())) { // Should prevent spawning inside of blocks
                            blaze.setPositionAndRotation(e.getPosXRandom(2.0D), e.getPosY(), e.getPosZRandom(2.0D), e.rotationYaw, e.rotationPitch);
                        }
                        world.addEntity(blaze);
                    }
                }
            }
            if (e instanceof BlazeEntity) {
                if (event.getSpawnReason() == SpawnReason.SPAWNER) {
                    if (Math.random() > 0.85) {
                        World world = event.getEntity().getEntityWorld();

                        WildfireEntity wildfire = ModEntityTypes.WILDFIRE.get().create(world);
                        wildfire.setPositionAndRotation(e.getPosX(), e.getPosY(), e.getPosZ(), e.rotationYaw, e.rotationPitch);

                        world.addEntity(wildfire);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
