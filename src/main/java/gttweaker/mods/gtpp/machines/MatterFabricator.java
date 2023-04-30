package gttweaker.mods.gtpp.machines;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gtPlusPlus.core.lib.CORE;
import gttweaker.mods.AddMultipleRecipeAction;

@ZenClass("mods.gtpp.MatterFabricator")
@ModOnly("miscutils")
public class MatterFabricator {

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding matter fabricator recipe for " + fluidOutput,
                fluidInput,
                fluidOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    CORE.RA.addMatterFabricatorRecipe(i.nextFluid(), i.nextFluid(), i.nextInt(), i.nextInt());
                }
            });
    }

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IIngredient input, ILiquidStack fluidInput,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding matter fabricator recipe for " + fluidOutput,
                input,
                fluidInput,
                fluidOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    // CORE.RA.addMatterFabricatorRecipe(i.nextItem(), i.nextFluid(), i.nextFluid(), i.nextInt(),
                    // i.nextInt());
                }
            });
    }
}
