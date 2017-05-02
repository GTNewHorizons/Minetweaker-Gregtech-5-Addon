package gttweaker.mods.gregtech.machines;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Distillation Tower recipes.
 *
 * @author DreamMasterXXL
 * @author Blood Asp
 */
@ZenClass("mods.gregtech.DistillationTower")
@ModOnly(MOD_ID)
public class DistillationTower {
    /**
     * Adds an Distillation Tower recipe.
     *
     * @param fluidInput    Fluid Input
     * @param fluidOutput   Up to 6 Fluid Outputs
     * @param itemOutput    Item output Slot
     * @param durationTicks duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack[] fluidOutput, IItemStack itemOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (fluidOutput.length < 1) {
            MineTweakerAPI.logError("Distillation Twower must have at least 1 Fluid output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Distillation Tower recipe for " + fluidInput.getDisplayName(), fluidInput, fluidOutput, itemOutput, durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addDistillationTowerRecipe(i.nextFluid(), i.nextFluidArr(), i.nextItem(), i.nextInt(), i.nextInt());
                }
            });
        }
    }
}