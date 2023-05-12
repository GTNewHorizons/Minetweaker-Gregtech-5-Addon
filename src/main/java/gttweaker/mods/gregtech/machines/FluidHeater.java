package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Fluid Heater recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidHeater")
@ModOnly(MOD_ID)
public class FluidHeater {

    /**
     * Adds a Fluid Heater recipe.
     *
     * @param fluidOutput   Fluid output
     * @param circuit       Circuit
     * @param fluidInput    fluid input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IItemStack circuit, ILiquidStack fluidInput,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fluid Heater recipe for " + fluidOutput,
                circuit,
                fluidInput,
                fluidOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addFluidHeaterRecipe(i.nextItem(), i.nextFluid(), i.nextFluid(), i.nextInt(), i.nextInt());
                }
            });
    }
}
