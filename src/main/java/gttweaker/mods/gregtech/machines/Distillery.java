package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Distillery recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Distillery")
@ModOnly("gregtech")
public class Distillery {

    /**
     * Adds a Distillery recipe.
     *
     * @param fluidOutput   Fluid output
     * @param circuit       Circuit
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param hidden        hidden
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IItemStack circuit, ILiquidStack fluidInput,
        int durationTicks, int euPerTick, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Distillery recipe for " + fluidOutput,
                circuit,
                fluidInput,
                fluidOutput,
                durationTicks,
                euPerTick,
                hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addDistilleryRecipe(
                        i.nextItem(),
                        i.nextFluid(),
                        i.nextFluid(),
                        i.nextInt(),
                        i.nextInt(),
                        i.nextBool());
                }
            });
    }
}
