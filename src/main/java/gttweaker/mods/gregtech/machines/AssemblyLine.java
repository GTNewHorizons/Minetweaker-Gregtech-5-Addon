package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.mods.AddMultipleRecipeAction;

/**
 * Provides access to the Assembly Line recipes.
 *
 * @author Draknyte1 / Alkalus
 */
@ZenClass("mods.gregtech.AssemblyLine")
@ModOnly(MOD_ID)
public class AssemblyLine {

    /**
     * Adds an Assembly Line recipe.
     *
     * @param aInputs      must be != null, 4-16 inputs
     * @param aFluidInputs 0-4 fluids
     * @param aOutput      must be != null
     * @param aDuration    must be > 0
     * @param aEUt         should be > 0
     */
    @ZenMethod
    public static void addRecipe(IItemStack aResearchItem, int aResearchTime, IItemStack[] aInputs,
        ILiquidStack[] aFluidInputs, IItemStack aOutput, int aDuration, int aEUt) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Assembly Line recipe for " + aOutput,
                aResearchItem,
                aResearchTime,
                aInputs,
                aFluidInputs,
                aOutput,
                aDuration,
                aEUt) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addAssemblylineRecipe(
                        i.nextItem(),
                        i.nextInt(),
                        i.nextItemArr(),
                        i.nextFluidArr(),
                        i.nextItem(),
                        i.nextInt(),
                        i.nextInt());
                }
            });
    }
}
