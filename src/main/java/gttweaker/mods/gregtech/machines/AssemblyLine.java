package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_ITEM;
import static gregtech.api.util.GTRecipeConstants.RESEARCH_TIME;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.util.GTRecipeConstants;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Assembly Line recipes.
 *
 * @author Draknyte1 / Alkalus
 */
@ZenClass("mods.gregtech.AssemblyLine")
@ModOnly("gregtech")
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
                    ItemStack researchItem = i.nextItem();
                    int researchTime = i.nextInt();
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack[] fluidInputs = i.nextFluidArr();
                    ItemStack output = i.nextItem();
                    int duration = i.nextInt();
                    int eut = i.nextInt();

                    RA.stdBuilder()
                        .metadata(RESEARCH_ITEM, researchItem)
                        .metadata(RESEARCH_TIME, researchTime)
                        .itemInputs(inputs)
                        .itemOutputs(output)
                        .fluidInputs(fluidInputs)
                        .duration(duration)
                        .eut(eut)
                        .addTo(GTRecipeConstants.AssemblyLine);
                }
            });
    }
}
