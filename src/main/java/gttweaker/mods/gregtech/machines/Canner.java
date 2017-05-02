package gttweaker.mods.gregtech.machines;

import gttweaker.mods.AddMultipleRecipeAction;
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
 * Provider access to the Canner recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Canner")
@ModOnly(MOD_ID)
public class Canner {
    /**
     * Adds a canner recipe with a single output.
     *
     * @param output        crafting output
     * @param input1        primary input
     * @param input2        secondary input (optional
     * @param durationTicks crafting duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        addRecipe(new IItemStack[]{output}, input1, input2, durationTicks, euPerTick);
    }

    /**
     * Adds a canner recipe with multiple outputs.
     *
     * @param output        array with 1 or 2 outputs
     * @param input1        primary inputs
     * @param input2        secondary inputs
     * @param durationTicks crafting duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("canner requires at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding canner recipe for " + output[0], input1, input2, output[0], itemOrNull(output, 1), durationTicks, euPerTick) {
                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.addCannerRecipe(i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt());
                }
            });
        }
    }
}
