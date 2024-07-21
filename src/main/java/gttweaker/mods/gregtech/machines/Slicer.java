package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.slicerRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Slicer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Slicer")
@ModOnly("gregtech")
public class Slicer {

    /**
     * Adds an Slicer recipe.
     *
     * @param output        primary output
     * @param input         primary input
     * @param blade         blade Slot
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, IItemStack blade, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Slicer recipe for " + output,
                input,
                blade,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItem(), i.nextItem())
                        .itemOutputs(i.nextItem())
                        .duration(i.nextInt())
                        .duration(i.nextInt())
                        .addTo(slicerRecipes);
                }
            });
    }
}
