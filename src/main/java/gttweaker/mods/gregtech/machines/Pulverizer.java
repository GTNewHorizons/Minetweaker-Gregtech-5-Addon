package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.maceratorRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Pulverizer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Pulverizer")
@ModOnly("gregtech")
public class Pulverizer {

    /**
     * Adds a Pulverizer recipe.
     *
     * @param outputs       recipe outputs
     * @param input         primary input
     * @param outChances    Chances for Outputs
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int[] outChances, int durationTicks,
        int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Pulverizer must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Pulverizer recipe for " + input,
                    input,
                    outputs,
                    outChances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        RA.stdBuilder()
                            .itemInputs(i.nextItem())
                            .itemOutputs(i.nextItemArr())
                            .outputChances(i.nextIntArr())
                            .duration(i.nextInt())
                            .duration(i.nextInt())
                            .addTo(maceratorRecipes);
                    }
                });
        }
    }
}
