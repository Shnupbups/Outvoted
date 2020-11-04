package com.hbn.outvoted;

import com.hbn.outvoted.config.OutvotedConfig;
import com.hbn.outvoted.init.ModEntityTypes;
import com.hbn.outvoted.init.ModItems;
import com.hbn.outvoted.util.ServerEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("outvoted")
public class Outvoted {
    public static final String MOD_ID = "outvoted";

    public Outvoted() {
        final FMLJavaModLoadingContext modLoadingContext = FMLJavaModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new ServerEvents());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, OutvotedConfig.COMMON_SPEC);
    }

    public static final ItemGroup TAB = new ItemGroup("modTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.INFERNO_HELMET.get());
        }
    };
}
