package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.LFTR")
@ModOnly(CORE.MODID)
public class LFTR {
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput1, ILiquidStack fluidInput2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding LFTR recipe for " + fluidOutput, fluidInput1, fluidInput2, fluidOutput, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                CORE.RA.addLFTRRecipe(i.nextFluid(), i.nextFluid(), i.nextFluid(), i.nextInt(), i.nextInt());
            }
        });
    }
}
