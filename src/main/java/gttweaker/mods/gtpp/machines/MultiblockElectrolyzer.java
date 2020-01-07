package gttweaker.mods.gtpp.machines;

import gtPlusPlus.core.lib.CORE;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import minetweaker.api.item.IItemStack;

@ZenClass("mods.gtpp.MultiblockElectrolyzer")
@ModOnly(CORE.MODID)
/*
ItemStack[] aOutputs, 
FluidStack[] aFluidOutputs, 
FluidStack[] aFluidInputs,
ItemStack[] aInputs, 
int[] aChances, 
int aDuration, 
int aEUtick, 
int aSpecial
*/	
	public class MultiblockElectrolyzer {
    @ZenMethod
    public static void addRecipe(IItemStack[] inputs, ILiquidStack fluidInput, ILiquidStack fluidOutput, IItemStack[] outputs, int[] chances, int durationTicks, int euPerTick, int Special) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Multiblock Electrolyzer recipe for " + inputs, outputs, fluidOutput, fluidInput, inputs, chances, durationTicks, euPerTick, Special) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                CORE.RA.addMultiblockElectrolyzerRecipe(i.nextItemArr(), i.nextFluidArr(), i.nextFluidArr(), i.nextItemArr(), 
				i.nextIntArr(), i.nextInt(), i.nextInt(), i.nextInt());
            }
        });
    }
}
