package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.fermentingRecipes;

import net.minecraftforge.fluids.FluidStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Fermenter recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Fermenter")
@ModOnly("gregtech")
public class Fermenter {

    /**
     * Adds a Fermenter recipe.
     *
     * @param fluidOutput primary fluidOutput
     * @param fluidInput  primary fluidInput
     * @param duration    reaction time, in ticks
     * @param hidden      hidden
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int duration, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fermenter recipe for " + fluidOutput,
                fluidInput,
                fluidOutput,
                duration,
                hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    FluidStack input = i.nextFluid();
                    FluidStack output = i.nextFluid();
                    int duration = i.nextInt();
                    int eut = 2;
                    boolean hidden = i.nextBool();
                    if (hidden) {
                        RA.stdBuilder()
                            .fluidInputs(input)
                            .fluidOutputs(output)
                            .duration(duration)
                            .eut(eut)
                            .hidden()
                            .addTo(fermentingRecipes);
                        return;
                    }
                    RA.stdBuilder()
                        .fluidInputs(input)
                        .fluidOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(fermentingRecipes);
                }
            });
    }
}
