package gttweaker.mods.gregtech;

import java.util.Arrays;
import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_Recipe;
import gttweaker.GTTweaker;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenClass("mods.gregtech.RecipeRemover")
@ModOnly("gregtech")
public class RecipeRemover {

    @ZenMethod
    public static void remove(String map, IIngredient[] inputs, IIngredient[] fluidInputs) {
        RecipeMap<?> recipeMap = GTRecipeMap.getRecipeMap(map);
        if (recipeMap == null) {
            MineTweakerAPI.logError("Could not find recipe map named \"" + map + "\"");
            return;
        }
        GT_Recipe recipe = recipeMap.findRecipeQuery()
            .items(
                Arrays.stream(inputs)
                    .map(GTTweaker::getItemStackOrNull)
                    .filter(Objects::nonNull)
                    .toArray(ItemStack[]::new))
            .fluids(
                Arrays.stream(fluidInputs)
                    .map(GTTweaker::getFluidStackOrNull)
                    .filter(Objects::nonNull)
                    .toArray(FluidStack[]::new))
            .dontCheckStackSizes(true)
            .find();
        if (recipe == null) {
            MineTweakerAPI.logWarning("Could not find recipe to remove!");
            return;
        }
        MineTweakerAPI.apply(new RecipeRemoveAction(recipe, recipeMap));
    }

    public static class RecipeRemoveAction implements IUndoableAction {

        GT_Recipe recipe;
        RecipeMap<?> map;

        public RecipeRemoveAction(GT_Recipe recipe, RecipeMap<?> map) {
            this.recipe = recipe;
            this.map = map;
        }

        @Override
        public void apply() {
            map.getBackend()
                .removeRecipe(recipe);
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            map.add(recipe);
        }

        @Override
        public String describe() {
            return "Remove recipe from GT Machine";
        }

        @Override
        public String describeUndo() {
            return "Re-add recipe to GT Machine";
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }
}
