package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

/**
 * Provides access to the Implosion Compressor recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.ImplosionCompressor")
@ModOnly(MOD_ID)
public class ImplosionCompressor {
    /**
     * Adds an implosion compressor recipe with a single output.
     *
     * @param output recipe output
     * @param input  primary input
     * @param tnt    amount of TNT needed
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int tnt) {
        addRecipe(new IItemStack[]{output, null}, input, tnt);
    }

    /**
     * Adds an implosion compressor recipe with one or two outputs.
     *
     * @param output array with 1-2 outputs
     * @param input  primary input
     * @param tnt    amount of TNT needed
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int tnt) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Implosion compressor recipe requires at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Implosion compressor recipe for " + output[0], input, tnt, output[0], itemOrNull(output, 1)) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addImplosionRecipe(i.nextItem(), i.nextInt(), i.nextItem(), i.nextItem());
                }
            });
        }
    }
}
