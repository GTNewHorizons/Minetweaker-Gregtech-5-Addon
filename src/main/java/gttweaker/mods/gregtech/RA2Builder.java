package gttweaker.mods.gregtech;

import java.util.Arrays;
import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.GT_Values;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_RecipeBuilder;
import gttweaker.GTTweaker;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenClass("mods.gregtech.RA2")
@ModOnly("gregtech")
public class RA2Builder {

    private GT_RecipeBuilder recipeBuilder;

    public RA2Builder() {}

    public RA2Builder(GT_RecipeBuilder recipeBuilder) {
        this.recipeBuilder = recipeBuilder;
    }

    @ZenMethod
    public static RA2Builder builder() {
        return new RA2Builder(GT_Values.RA.stdBuilder());
    }

    @ZenMethod
    public RA2Builder noItemInputs() {
        return this;
    }

    @ZenMethod
    public RA2Builder itemInputs(IIngredient[] inputs) {
        recipeBuilder.itemInputs(
            Arrays.stream(inputs)
                .map(GTTweaker::getItemStackOrNull)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder noItemOutputs() {
        return this;
    }

    @ZenMethod
    public RA2Builder itemOutputs(IIngredient[] outputs) {
        recipeBuilder.itemOutputs(
            Arrays.stream(outputs)
                .map(GTTweaker::getItemStackOrNull)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder noFluidInputs() {
        return this;
    }

    @ZenMethod
    public RA2Builder fluidInputs(IIngredient[] inputs) {
        recipeBuilder.fluidInputs(
            Arrays.stream(inputs)
                .map(GTTweaker::getFluidStackOrNull)
                .filter(Objects::nonNull)
                .toArray(FluidStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder noFluidOutputs() {
        return this;
    }

    @ZenMethod
    public RA2Builder fluidOutputs(IIngredient[] outputs) {
        recipeBuilder.fluidOutputs(
            Arrays.stream(outputs)
                .map(GTTweaker::getFluidStackOrNull)
                .filter(Objects::nonNull)
                .toArray(FluidStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder duration(int duration) {
        recipeBuilder.duration(duration);
        return this;
    }

    @ZenMethod
    public RA2Builder eut(int eut) {
        recipeBuilder.eut(eut);
        return this;
    }

    @ZenMethod
    public RA2Builder outputChances(int[] chances) {
        recipeBuilder.outputChances(chances);
        return this;
    }

    @ZenMethod
    public RA2Builder specialValue(int specialValue) {
        recipeBuilder.specialValue(specialValue);
        return this;
    }

    @ZenMethod
    public RA2Builder specialItem(IIngredient specialItem) {
        recipeBuilder.special(GTTweaker.getItemStackOrNull(specialItem));
        return this;
    }

    @ZenMethod
    public RA2Builder noOptimize() {
        recipeBuilder.noOptimize();
        return this;
    }

    @ZenMethod
    public void addTo(String recipeMap) {
        GT_Recipe recipe = recipeBuilder.build()
            .orElse(null);
        if (recipe == null) {
            MineTweakerAPI.logError("Could not build recipe!");
            return;
        }
        RecipeMap<?> map = GTRecipeMap.getRecipeMap(recipeMap);
        if (map == null) {
            MineTweakerAPI.logError("Could not find recipe map named \"" + recipeMap + "\"");
            return;
        }

        MineTweakerAPI.apply(new RecipeAddAction(recipe, map));
    }

    public static class RecipeAddAction implements IUndoableAction {

        GT_Recipe recipe;
        RecipeMap<?> map;

        public RecipeAddAction(GT_Recipe recipe, RecipeMap<?> map) {
            this.recipe = recipe;
            this.map = map;
        }

        @Override
        public void apply() {
            map.add(recipe);
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            map.getBackend()
                .removeRecipe(recipe);
        }

        @Override
        public String describe() {
            return "RA2 - Adding recipe";
        }

        @Override
        public String describeUndo() {
            return "RA2 - Removing recipe";
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }
}
