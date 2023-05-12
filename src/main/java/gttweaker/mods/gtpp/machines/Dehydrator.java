package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
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
@ModOnly(CORE.MODID)
public class Dehydrator {

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, ILiquidStack fluidInput, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding dehydrator recipe for " + ArrayHelper.itemOrNull(outputs, 0),
                input,
                fluidInput,
                outputs,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    CORE.RA.addDehydratorRecipe(i.nextItem(), i.nextFluid(), i.nextItemArr(), i.nextInt(), i.nextInt());
                }
            });
    }

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
                    CORE.RA.addDehydratorRecipe(
                        i.nextItemArr(),
                        i.nextFluid(),
                        i.nextFluid(),
                        i.nextItemArr(),
                        i.nextIntArr(),
                        i.nextInt(),
                        i.nextInt());
                }
            });
    }
}
