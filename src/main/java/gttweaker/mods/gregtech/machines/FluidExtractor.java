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
 * Provides access to the Fluid Extractor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidExtractor")
@ModOnly("gregtech")
public class FluidExtractor {

    /**
     * Adds a Fluid Extractor recipe.
     *
     * @param output        output Slot
     * @param input         input Slot
     * @param fluidOutput   fluidOutput Slot
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param chance        chance output slot
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidOutput, int durationTicks,
        int euPerTick, int chance) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fluid Extractor recipe for " + input,
                input,
                output,
                fluidOutput,
                durationTicks,
                euPerTick,
                chance) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addFluidExtractionRecipe(
                        i.nextItem(),
                        i.nextItem(),
                        i.nextFluid(),
                        i.nextInt(),
                        i.nextInt(),
                        i.nextInt());
                }
            });
    }
}
