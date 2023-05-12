package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Amplifabricator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Amplifabricator")
@ModOnly(MOD_ID)
public class Amplifabricator {

    /**
     * Adds a Amplifabricator recipe.
     *
     * @param input    primary Input
     * @param duration reaction time, in ticks
     */
    @ZenMethod
    public static void addRecipe(IIngredient input, int duration, int amount) {
        MineTweakerAPI
            .apply(new AddMultipleRecipeAction("Adding Amplifabricator recipe for " + input, input, duration, amount) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addAmplifier(i.nextItem(), i.nextInt(), i.nextInt());
                }
            });
    }
}
