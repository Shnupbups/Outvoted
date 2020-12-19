package com.hbn.outvoted.data;

import com.hbn.outvoted.init.ModItems;
import com.hbn.outvoted.init.ModRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.data.CustomRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessBuilder.shapelessRecipe(ModItems.INFERNO_SHIELD.get())
                .addIngredient(Items.SHIELD)
                .addIngredient(ModItems.INFERNO_SHIELD_PART.get())
                .addCriterion("has_shield_part", hasItem(ModItems.INFERNO_SHIELD_PART.get()))
                .build(consumer);
        ShapedBuilder.shapedRecipe(ModItems.INFERNO_SHIELD_PART.get())
                .patternLine("MMM")
                .patternLine("MCM")
                .patternLine("MMM")
                .key('M', Blocks.NETHER_BRICKS)
                .key('C', ModItems.INFERNO_PIECE.get())
                .addCriterion("has_inferno_piece", hasItem(ModItems.INFERNO_PIECE.get()))
                .build(consumer);
        ShapedBuilder.shapedRecipe(ModItems.PRISMARINE_ROD.get())
                .patternLine("P")
                .patternLine("P")
                .patternLine("P")
                .key('P', Blocks.PRISMARINE_BRICKS)
                .addCriterion("has_prismarine", hasItem(Blocks.PRISMARINE_BRICKS))
                .build(consumer);
        ShapedBuilder.shapedRecipe(Items.TRIDENT)
                .patternLine(" TT")
                .patternLine(" HT")
                .patternLine("R  ")
                .key('T', ModItems.KRAKEN_TOOTH.get())
                .key('H', Items.HEART_OF_THE_SEA)
                .key('R', ModItems.PRISMARINE_ROD.get())
                .addCriterion("has_tooth", hasItem(ModItems.KRAKEN_TOOTH.get()))
                .build(consumer);
        CustomRecipeBuilder.customRecipe(ModRecipes.REPAIR_COST.get()).build(consumer, ModRecipes.REPAIR_COST.getId().getPath());
    }
}
