package gttweaker.mods.gtpp.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.centrifugeNonCellRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import gttweaker.util.ArrayHelper;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.Dehydrator")
@ModOnly("miscutils")
public class Dehydrator {

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient[] inputs,
        ILiquidStack fluidInput, int[] chanses, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding dehydrator recipe for " + ArrayHelper.itemOrNull(outputs, 0),
                inputs,
                fluidInput,
                fluidOutput,
                outputs,
                chanses,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItemArr())
                        .fluidInputs(i.nextFluid())
                        .fluidOutputs(i.nextFluid())
                        .itemOutputs(i.nextItemArr())
                        .outputChances(i.nextIntArr())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(centrifugeNonCellRecipes);
                }
            });
    }
}
