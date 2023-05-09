package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
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
                    CORE.RA.addCokeOvenRecipe(
                        i.nextItem(),
                        i.nextItem(),
                        i.nextFluid(),
                        i.nextFluid(),
                        i.nextItem(),
                        i.nextInt(),
                        i.nextInt());
                }
            });
    }
}
