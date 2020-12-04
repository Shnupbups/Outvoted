package com.hbn.outvoted.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Extends and overrides {@link ShapedRecipeBuilder} to be compatible with config
 */
class ShapedBuilder extends ShapedRecipeBuilder {
    private final Item result;
    private final int count;
    private final List<String> pattern = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
    private String group;

    public ShapedBuilder(IItemProvider resultIn, int countIn) {
        super(resultIn, countIn);
        this.result = resultIn.asItem();
        this.count = countIn;
    }

    public static ShapedRecipeBuilder shapedRecipe(IItemProvider resultIn) {
        return shapedRecipe(resultIn, 1);
    }

    public static ShapedRecipeBuilder shapedRecipe(IItemProvider resultIn, int countIn) {
        return new ShapedBuilder(resultIn, countIn);
    }

    @Override
    public ShapedRecipeBuilder key(Character symbol, Tag<Item> tagIn) {
        return this.key(symbol, Ingredient.fromTag(tagIn));
    }

    @Override
    public ShapedRecipeBuilder key(Character symbol, IItemProvider itemIn) {
        return this.key(symbol, Ingredient.fromItems(itemIn));
    }

    @Override
    public ShapedRecipeBuilder key(Character symbol, Ingredient ingredientIn) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(symbol, ingredientIn);
            return this;
        }
    }

    @Override
    public ShapedRecipeBuilder patternLine(String patternIn) {
        if (!this.pattern.isEmpty() && patternIn.length() != this.pattern.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.pattern.add(patternIn);
            return this;
        }
    }

    @Override
    public ShapedRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.withCriterion(name, criterionIn);
        return this;
    }

    @Override
    public ShapedRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn) {
        this.build(consumerIn, Registry.ITEM.getKey(this.result));
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
        consumerIn.accept(new ShapedRecipeBuilder.Result(id, this.result, this.count, this.group == null ? "" : this.group, this.pattern, this.key, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/misc/" + id.getPath())));
    }
}

class ShapelessBuilder extends ShapelessRecipeBuilder {
    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
    private String group;

    public ShapelessBuilder(IItemProvider resultIn, int countIn) {
        super(resultIn, countIn);
        this.result = resultIn.asItem();
        this.count = countIn;
    }

    public static ShapelessRecipeBuilder shapelessRecipe(IItemProvider resultIn) {
        return new ShapelessBuilder(resultIn, 1);
    }

    public static ShapelessRecipeBuilder shapelessRecipe(IItemProvider resultIn, int countIn) {
        return new ShapelessBuilder(resultIn, countIn);
    }

    @Override
    public ShapelessRecipeBuilder addIngredient(Tag<Item> tagIn) {
        return this.addIngredient(Ingredient.fromTag(tagIn));
    }

    @Override
    public ShapelessRecipeBuilder addIngredient(IItemProvider itemIn) {
        return this.addIngredient(itemIn, 1);
    }

    @Override
    public ShapelessRecipeBuilder addIngredient(IItemProvider itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.addIngredient(Ingredient.fromItems(itemIn));
        }

        return this;
    }

    @Override
    public ShapelessRecipeBuilder addIngredient(Ingredient ingredientIn) {
        return this.addIngredient(ingredientIn, 1);
    }

    @Override
    public ShapelessRecipeBuilder addIngredient(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }

        return this;
    }

    @Override
    public ShapelessRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.withCriterion(name, criterionIn);
        return this;
    }

    @Override
    public ShapelessRecipeBuilder setGroup(String groupIn) {
        this.group = groupIn;
        return this;
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn) {
        this.build(consumerIn, Registry.ITEM.getKey(this.result));
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Shapeless Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, new ResourceLocation(save));
        }
    }

    @Override
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
        consumerIn.accept(new ShapelessRecipeBuilder.Result(id, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/combat/" + id.getPath())));
    }
}

