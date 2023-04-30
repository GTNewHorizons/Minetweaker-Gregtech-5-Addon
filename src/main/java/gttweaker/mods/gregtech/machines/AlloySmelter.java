package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

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
 * Provider access to the Alloy Smelter recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.AlloySmelter")
@ModOnly(MOD_ID)
public class AlloySmelter {

    /**
     * Adds an alloy smelter recipe.
     *
     * @param output        alloy smelter output
     * @param input1        primary input
     * @param input2        secondary input
     * @param durationTicks smelting time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding alloy smelter recipe for " + output,
                input1,
                input2,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    ItemStack a3 = i.nextItem();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();
                    RA.addAlloySmelterRecipe(a1, a2, a3, a4, a5);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1, a2 },
                        new ItemStack[] { a3 },
                        null,
                        null,
                        a4,
                        a5,
                        "sAlloySmelterRecipes");
                }
            });
    }
}
