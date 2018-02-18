package gttweaker.mods.gregtech.machines;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

/**
 * Provides access to the Blast Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.BlastFurnace")
@ModOnly(MOD_ID)
public class BlastFurnace {
    /**
     * Adds a Blast Furnace recipe.
     *
     * @param output        recipe output 1+2
     * @param fluidInput    primary fluidInput
     * @param input         recipes input  1+2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidOutput, IIngredient[] input, ILiquidStack fluidInput, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0 || input.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input and 1 output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Blast furnace recipe for " + output[0], input[0], itemOrNull(input, 1), fluidInput, fluidOutput, output[0], itemOrNull(output, 1), durationTicks, euPerTick, heat) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addBlastRecipe(i.nextItem(), i.nextItem(), i.nextFluid(), i.nextFluid(), i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt(), i.nextInt());
                }
            });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidInput, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        addRecipe(output, null, input, fluidInput, durationTicks, euPerTick, heat);
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        addRecipe(output, null, input, durationTicks, euPerTick, heat);
    }
}