package gttweaker.mods.gtpp.machines;

import static gregtech.api.enums.GTValues.RA;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.chemicalDehydratorRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.MultiblockCentrifuge")
@ModOnly("miscutils")

public class MultiblockCentrifuge {

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack[] fluidOutput, ILiquidStack[] fluidInput,
        IItemStack[] inputs, int[] chances, int durationTicks, int euPerTick, int Special) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Multiblock Centrifuge recipe for " + outputs,
                inputs,
                fluidInput,
                fluidOutput,
                outputs,
                chances,
                durationTicks,
                euPerTick,
                Special) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .noOptimize()
                        .itemInputs(i.nextItemArr())
                        .fluidInputs(i.nextFluidArr())
                        .fluidOutputs(i.nextFluidArr())
                        .itemOutputs(i.nextItemArr())
                        .outputChances(i.nextIntArr())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .specialValue(i.nextInt()) // unused, added for full backward compat
                        .addTo(chemicalDehydratorRecipes);

                }
            });
    }
}
