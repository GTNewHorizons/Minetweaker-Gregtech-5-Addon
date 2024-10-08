package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.brewingRecipes;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Brewing Machine recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Brewery")
@ModOnly("gregtech")
public class Brewery {

    /**
     * Adds a Brewing Machine recipe.
     *
     * @param output     primary fluid output
     * @param ingredient primary ingredient
     * @param input      primary fluid ingredient
     * @param hidden     hidden true or false
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack output, IIngredient ingredient, ILiquidStack input, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction("Adding Brewery recipe for " + output, ingredient, input, output, hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input = i.nextItem();
                    FluidStack fluidInput = new FluidStack(
                        i.nextFluid()
                            .getFluid(),
                        750);
                    FluidStack fluidOutput = new FluidStack(
                        i.nextFluid()
                            .getFluid(),
                        750);
                    boolean recipe_hidden = i.nextBool();
                    if (recipe_hidden) {
                        RA.stdBuilder()
                            .itemInputs(input)
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .duration(6 * SECONDS + 8 * TICKS)
                            .eut(4)
                            .hidden()
                            .addTo(brewingRecipes);
                    } else {
                        RA.stdBuilder()
                            .itemInputs(input)
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .duration(6 * SECONDS + 8 * TICKS)
                            .eut(4)
                            .addTo(brewingRecipes);
                    }
                }
            });
    }
}
