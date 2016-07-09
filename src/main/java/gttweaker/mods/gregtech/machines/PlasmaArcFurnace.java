package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Plasma Arc Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PlasmaArcFurnace")
@ModOnly(MOD_ID)
public class PlasmaArcFurnace {
    /**
     * Adds an Arc Furnace recipe.
     *
     * @param outputs       1-4 recipe output
     * @param fluidOutput   primary fluidOutput
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param outChances    chances of 1-4 output
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input, ILiquidStack fluidInput, int[] outChances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Plasma Arc Furnace must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Plasma Arc Furnace recipe for " + input, input, fluidInput, outputs, fluidOutput, outChances, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addPlasmaArcFurnaceRecipe(i.nextItem(), i.nextFluid(), i.nextItemArr(), i.nextFluid(), i.nextIntArr(), i.nextInt(), i.nextInt());
                }
            });
        }
    }
}