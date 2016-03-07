package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Fermenter recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Fermenter")
@ModOnly(MOD_ID)
public class Fermenter {
    /**
     * Adds a Fermenter recipe.
     *
     * @param fluidOutput primary fluidOutput
     * @param fluidInput  primary fluidInput
     * @param duration    reaction time, in ticks
     * @param hidden      hidden
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int duration, boolean hidden) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Fermenter recipe for " + fluidOutput, fluidInput, fluidOutput, duration, hidden) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                RA.addFermentingRecipe(i.nextFluid(), i.nextFluid(), i.nextInt(), i.nextBool());
            }
        });
    }
}