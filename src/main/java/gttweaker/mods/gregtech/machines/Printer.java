package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Printer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Printer")
@ModOnly("gregtech")
public class Printer {

    /**
     * Adds a Printer recipe.
     *
     * @param output        recipe output
     * @param input         primary input
     * @param dataStick     Data Stick
     * @param ink           ink fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, IItemStack dataStick, ILiquidStack ink,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Printer recipe for " + output,
                input,
                ink,
                dataStick,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addPrinterRecipe(
                        i.nextItem(),
                        i.nextFluid(),
                        i.nextItem(),
                        i.nextItem(),
                        i.nextInt(),
                        i.nextInt());
                }
            });
    }
}
