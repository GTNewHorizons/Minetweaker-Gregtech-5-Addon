package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Brewing Machine recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Brewery")
@ModOnly("gregtech")
public class Brewery {

    /**
     * Adds a Brewing Machine recipe.
     *
     * @param output     primary fluid output
     * @param ingredient primary ingredient
     * @param input      primary fluid ingredient
     * @param hidden     hidden true or false
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack output, IIngredient ingredient, ILiquidStack input, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction("Adding Brewery recipe for " + output, ingredient, input, output, hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addBrewingRecipe(
                        i.nextItem(),
                        i.nextFluid()
                            .getFluid(),
                        i.nextFluid()
                            .getFluid(),
                        i.nextBool());
                }
            });
    }
}
