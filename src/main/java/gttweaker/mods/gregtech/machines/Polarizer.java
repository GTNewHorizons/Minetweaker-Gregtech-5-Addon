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
 * Provides access to the Polarizer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Polarizer")
@ModOnly(MOD_ID)
public class Polarizer {

    /**
     * Adds a Polarizer recipe.
     *
     * @param output        recipe output
     * @param input         Item input Slot 1
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Polarizer recipe for " + output,
                input,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    int a3 = i.nextInt();
                    int a4 = i.nextInt();
                    RA.addPolarizerRecipe(a1, a2, a3, a4);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a2 },
                        null,
                        null,
                        a3,
                        a4,
                        "sPolarizerRecipes");
                }
            });
    }
}
