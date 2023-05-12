package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Chemical Bath recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ChemicalBath")
@ModOnly(MOD_ID)
public class ChemicalBath {

    /**
     * Adds a Chemical Bath recipe.
     *
     * @param output        outputs 1-3
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chance of 3 outputs
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack fluidInput, int[] chances,
        int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("chemical bath requires at least 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Chemical Bath recipe for " + input,
                    input,
                    fluidInput,
                    output[0],
                    itemOrNull(output, 1),
                    itemOrNull(output, 2),
                    chances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        RA.addChemicalBathRecipe(
                            i.nextItem(),
                            i.nextFluid(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextIntArr(),
                            i.nextInt(),
                            i.nextInt());
                    }
                });
        }
    }
}
