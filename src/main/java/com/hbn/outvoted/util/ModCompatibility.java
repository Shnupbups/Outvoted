package com.hbn.outvoted.util;

import net.minecraftforge.fml.ModList;

public class ModCompatibility {
    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }
}
