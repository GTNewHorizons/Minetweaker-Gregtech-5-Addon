package gttweaker.mods.gtpp.machines;

import static gregtech.api.enums.GTValues.RA;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.electrolyzerNonCellRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.MultiblockElectrolyzer")
@ModOnly("miscutils")
/*
 * ItemStack[] aOutputs,
 * FluidStack[] aFluidOutputs,
 * FluidStack[] aFluidInputs,
 * ItemStack[] aInputs,
 * int[] aChances,
 * int aDuration,
 * int aEUtick,
 * int aSpecial
 */
public class MultiblockElectrolyzer {

    @ZenMethod
    public static void addRecipe(IItemStack[] inputs, ILiquidStack[] fluidInput, ILiquidStack[] fluidOutput,
        IItemStack[] outputs, int[] chances, int durationTicks, int euPerTick, int Special) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Multiblock Electrolyzer recipe for " + inputs,
                outputs,
                fluidOutput,
                fluidInput,
                inputs,
                chances,
                durationTicks,
                euPerTick,
                Special) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItemArr())
                        .fluidInputs(i.nextFluidArr())
                        .fluidOutputs(i.nextFluidArr())
                        .itemOutputs(i.nextItemArr())
                        .outputChances(i.nextIntArr())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .specialValue(i.nextInt()) // unused, added for full backward compat
                        .addTo(electrolyzerNonCellRecipes);
                }
            });
    }
}
