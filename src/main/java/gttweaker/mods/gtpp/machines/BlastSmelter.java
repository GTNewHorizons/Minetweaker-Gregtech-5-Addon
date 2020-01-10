package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

	/**
	 * Adds a Recipe for the Alloy Blast Smelter. (up to 9 Inputs)
	 *
	 * @param aInput   = ItemStack[] (not null, and respects StackSize)
	 * @param aFluidInput   = Input of a fluid (can be null, and respects StackSize)
	 * @param aFluidOutput   = Output of the Molten Metal (not null, and respects StackSize)
	 * @param aChances 	= Output Chance (can be == 0)
	 * @param aDuration 	= Duration (must be >= 0)
	 * @param aEUt			= EU per tick needed for heating up (must be >= 0)
	 * @return true if the Recipe got added, otherwise false.
	 */

//inputs, fluidOutput, chance, durationTicks, euPerTick
//inputs, fluidInput, fluidOutput, chance, durationTicks, euPerTick
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
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, IIngredient[] inputs, int chance, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding alloy blast smelter recipe for " + fluidOutput, inputs, fluidInput, fluidOutput, chance, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                CORE.RA.addBlastSmelterRecipe(i.nextItemArr(), i.nextFluid(), i.nextFluid(), i.nextInt(), i.nextInt(), i.nextInt());
            }
        });
    } 
}
