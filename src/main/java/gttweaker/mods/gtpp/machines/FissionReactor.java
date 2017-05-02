package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gttweaker.util.ArrayHelper.itemOrNull;

@ZenClass("mods.gtpp.FissionReactor")
@ModOnly(CORE.MODID)
public class FissionReactor {
    @ZenMethod
    public static void addFissionFuel(ILiquidStack[] fluidOutputs, ILiquidStack[] fluidInputs, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding fission fuel for " + itemOrNull(fluidOutputs, 0),
                itemOrNull(fluidInputs, 0), itemOrNull(fluidInputs, 1), itemOrNull(fluidInputs, 2),
                itemOrNull(fluidInputs, 3), itemOrNull(fluidInputs, 4), itemOrNull(fluidInputs, 5),
                itemOrNull(fluidInputs, 6), itemOrNull(fluidInputs, 7), itemOrNull(fluidInputs, 8),
                itemOrNull(fluidOutputs, 0), itemOrNull(fluidOutputs, 1), durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                CORE.RA.addFissionFuel(i.nextFluid(), i.nextFluid(), i.nextFluid(),
                        i.nextFluid(), i.nextFluid(), i.nextFluid(), i.nextFluid(),
                        i.nextFluid(), i.nextFluid(), i.nextFluid(), i.nextFluid(),
                        i.nextInt(), i.nextInt());
            }
        });
    }
}
