package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.packagerRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Packer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Packer")
@ModOnly("gregtech")
public class Packer {

    /**
     * Adds a Packer recipe.
     *
     * @param output        recipe output
     * @param input1        Item input Slot 1
     * @param input2        Item input Slot 2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Packer recipe for " + output,
                input1,
                input2,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                            .itemInputs(i.nextItem(), i.nextItem())
                            .itemOutputs(i.nextItem())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(packagerRecipes);
                }
            });
    }
}
