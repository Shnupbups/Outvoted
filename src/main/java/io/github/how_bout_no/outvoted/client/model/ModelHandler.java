package io.github.how_bout_no.outvoted.client.model;

import io.github.how_bout_no.outvoted.Outvoted;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Map;

@EventBusSubscriber(value = Dist.CLIENT, modid = Outvoted.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModelHandler {

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent e) {
        final Map<ResourceLocation, IBakedModel> registry = e.getModelRegistry();
        replaceShieldModel(registry, "wildfire");
    }

    private static void replaceShieldModel(Map<ResourceLocation, IBakedModel> registry, String material) {
        ModelResourceLocation location = new ModelResourceLocation(Outvoted.MOD_ID + ":" + material + "_shield",
                "inventory");
        IBakedModel originalModel = registry.get(location);
        registry.put((location), new ShieldModel(originalModel));
    }
}