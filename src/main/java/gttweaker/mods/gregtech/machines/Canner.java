package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;

import net.minecraft.item.ItemStack;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;

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
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        addRecipe(new IItemStack[] { output }, input1, input2, durationTicks, euPerTick);
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
    public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("canner requires at least 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding canner recipe for " + output[0],
                    input1,
                    input2,
                    output[0],
                    itemOrNull(output, 1),
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        ItemStack a2 = i.nextItem();
                        ItemStack a3 = i.nextItem();
                        ItemStack a4 = i.nextItem();
                        int a5 = i.nextInt();
                        int a6 = i.nextInt();
                        RA.addCannerRecipe(a1, a2, a3, a4, a5, a6);
                        GTTweaker.logGTRecipe(
                            new ItemStack[] { a1, a2 },
                            new ItemStack[] { a3, a4 },
                            null,
                            null,
                            a5,
                            a6,
                            "sCannerRecipes");
                    }
                });
        }
    }
}
