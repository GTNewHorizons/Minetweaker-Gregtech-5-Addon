package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import net.minecraft.item.ItemStack;

import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the extruder recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Extruder")
@ModOnly(MOD_ID)
public class Extruder {

    /**
     * Adds an extruder recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param shape         shape (set stack size to 0 to prevent the shape from being consumed)
     * @param durationTicks extruding time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, IItemStack shape, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding extruder recipe for " + output,
                input,
                shape,
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
                    RA.addExtruderRecipe(a1, a2, a3, a4, a5);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1, a2 },
                        new ItemStack[] { a3 },
                        null,
                        null,
                        a4,
                        a5,
                        "sExtruderRecipes");
                }
            });
    }
}
