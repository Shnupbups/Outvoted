package io.github.how_bout_no.outvoted.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

import java.util.UUID;

public class EntityUtils {
    /**
     * Sets entity max health to that of its appropriate config value
     */
    public static void setConfigHealth(LivingEntity entity, double value) {
        IAttributeInstance max_health = entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
        max_health.applyModifier(new AttributeModifier(UUID.fromString("84b09787-fa1c-46f0-92f8-ebac1f08839e"), "Max health", value - 20.0D, AttributeModifier.Operation.ADDITION));
        entity.setHealth(entity.getMaxHealth());
    }
}
