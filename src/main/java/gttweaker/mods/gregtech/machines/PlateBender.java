package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

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
@ModOnly(MOD_ID)
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
                    RA.addBenderRecipe(i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());
                }
            });
    }
}
