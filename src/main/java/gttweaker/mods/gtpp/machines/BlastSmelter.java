package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.BlastSmelter")
@ModOnly(CORE.MODID)
public class BlastSmelter {
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IIngredient[] inputs, int chance, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding alloy blast smelter recipe for " + fluidOutput, inputs, fluidOutput, chance, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                CORE.RA.addBlastSmelterRecipe(i.nextItemArr(), i.nextFluid(), i.nextInt(), i.nextInt(), i.nextInt());
            }
        });
    }
}
