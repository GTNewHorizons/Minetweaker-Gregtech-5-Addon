package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.benderRecipes;
import static gregtech.api.recipe.RecipeMaps.wiremillRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Wiremill recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Wiremill")
@ModOnly("gregtech")
public class Wiremill {

    /**
     * Adds a Wiremill recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks processing time
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding wiremill recipe for " + output,
                input,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                            .itemInputs(i.nextItem())
                            .itemOutputs(i.nextItem())
                            .duration(i.nextInt())
                            .duration(i.nextInt())
                            .addTo(wiremillRecipes);
                }
            });
    }
}
