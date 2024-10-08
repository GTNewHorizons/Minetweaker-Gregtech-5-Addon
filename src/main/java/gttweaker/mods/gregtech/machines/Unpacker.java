package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.unpackagerRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Unpacker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Unpacker")
@ModOnly("gregtech")
public class Unpacker {

    /**
     * Adds a Unpacker recipe.
     *
     * @param output1       recipe output Slot 1
     * @param output2       recipe output Slot 2
     * @param input         recipe Input Slot
     * @param durationTicks duration time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Unpacker recipe for " + input,
                input,
                output1,
                output2,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItem())
                        .itemOutputs(i.nextItem(), i.nextItem())
                        .duration(i.nextInt())
                        .duration(i.nextInt())
                        .addTo(unpackagerRecipes);
                }
            });
    }
}
