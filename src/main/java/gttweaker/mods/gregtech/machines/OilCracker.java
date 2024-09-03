package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.crackingRecipes;

import gregtech.api.util.GTUtility;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the OilCracker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.OilCracker")
@ModOnly("gregtech")
public class OilCracker {

    /**
     * Adds a Oil Cracker recipe.
     *
     * @param fluidOutput   recipe Fluid output
     * @param fluidInput1   primary Fluid input
     * @param fluidInput2   secondary Fluid input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(int circuitConfig, ILiquidStack fluidOutput, ILiquidStack fluidInput1,
        ILiquidStack fluidInput2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Oil Cracker recipe for " + fluidOutput,
                circuitConfig,
                fluidInput1,
                fluidInput2,
                fluidOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(GTUtility.getIntegratedCircuit(i.nextInt()))
                        .fluidInputs(i.nextFluid(), i.nextFluid())
                        .fluidOutputs(i.nextFluid())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(crackingRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.logWarning("This method is no longer supported for OilCracker. Use OilCracker.addRecipe()");
    }
}
