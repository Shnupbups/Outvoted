package com.hbn.outvoted;

import com.hbn.outvoted.init.ModEntityTypes;
import com.hbn.outvoted.init.ModItems;
import com.hbn.outvoted.util.ServerEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final ItemGroup TAB = new ItemGroup("modTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.DIAMOND_BLOCK);
        }
    };
}
