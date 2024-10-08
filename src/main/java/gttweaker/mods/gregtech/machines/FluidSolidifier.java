package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.fluidSolidifierRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Fluid Solidifier recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidSolidifier")
@ModOnly("gregtech")
public class FluidSolidifier {

    /**
     * Adds a Fluid Solidifier recipe.
     *
     * @param output        output Slot
     * @param mold          mold Slot
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack mold, ILiquidStack fluidInput, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fluid Solidifier recipe for " + output,
                mold,
                fluidInput,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {

                    RA.stdBuilder()
                        .itemInputs(i.nextItem())
                        .fluidInputs(i.nextFluid())
                        .itemOutputs(i.nextItem())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(fluidSolidifierRecipes);
                }
            });
    }
}
