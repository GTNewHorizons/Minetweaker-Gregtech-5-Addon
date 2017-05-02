package gttweaker.mods.gregtech.machines;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Autoclave recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Autoclave")
@ModOnly(MOD_ID)
public class Autoclave {
    /**
     * Adds an Autoclave recipe.
     *
     * @param output        primary output
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chances
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chances, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Autoclave recipe for " + output, input, fluidInput, output, chances, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                RA.addAutoclaveRecipe(i.nextItem(), i.nextFluid(), i.nextItem(), i.nextInt(), i.nextInt(), i.nextInt());
            }
        });
    }
}
