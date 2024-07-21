package gttweaker.mods.gtpp.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.cokeOvenRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.CokeOven")
@ModOnly("miscutils")
public class CokeOven {

    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2,
        ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding coke oven recipe for " + output,
                input1,
                input2,
                fluidInput,
                fluidOutput,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItem(), i.nextItem())
                        .fluidInputs(i.nextFluid())
                        .fluidOutputs(i.nextFluid())
                        .itemOutputs(i.nextItem())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(cokeOvenRecipes);
                }
            });
    }
}
