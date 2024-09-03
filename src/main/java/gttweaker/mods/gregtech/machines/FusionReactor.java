package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.fusionRecipes;
import static gregtech.api.util.GTRecipeConstants.FUSION_THRESHOLD;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Fusion Reactor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FusionReactor")
@ModOnly("gregtech")
public class FusionReactor {

    /**
     * Adds a Mixer recipe.
     *
     * @param fluidOutput   primary fluid Output
     * @param fluidInput1   primary fluid Input
     * @param fluidInput2   secondary fluid Input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param startEU       Starting at ... EU
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput1, ILiquidStack fluidInput2,
        int durationTicks, int euPerTick, int startEU) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fusion Reactor recipe for " + fluidOutput,
                fluidInput1,
                fluidInput2,
                fluidOutput,
                durationTicks,
                euPerTick,
                startEU) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .fluidInputs(i.nextFluid(), i.nextFluid())
                        .fluidOutputs(i.nextFluid())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .metadata(FUSION_THRESHOLD, i.nextInt())
                        .addTo(fusionRecipes);
                }
            });
    }
}
