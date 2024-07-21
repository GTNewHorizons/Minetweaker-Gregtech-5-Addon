package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.benderRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Access point to Plate Bender recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.PlateBender")
@ModOnly("gregtech")
public class PlateBender {

    /**
     * Adds a plate bender recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks bending time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding plate bender recipe for " + output,
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
                        .addTo(benderRecipes);
                }
            });
    }
}
