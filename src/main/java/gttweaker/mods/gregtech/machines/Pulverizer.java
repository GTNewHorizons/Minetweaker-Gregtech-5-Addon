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
 * Provides access to the Pulverizer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Pulverizer")
@ModOnly(MOD_ID)
public class Pulverizer {

    /**
     * Adds a Pulverizer recipe.
     *
     * @param outputs       recipe outputs
     * @param input         primary input
     * @param outChances    Chances for Outputs
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int[] outChances, int durationTicks,
        int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Pulverizer must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Pulverizer recipe for " + input,
                    input,
                    outputs,
                    outChances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        ItemStack[] a2 = i.nextItemArr();
                        int[] a3 = i.nextIntArr();
                        int a4 = i.nextInt();
                        int a5 = i.nextInt();

                        RA.addPulveriserRecipe(a1, a2, a3, a4, a5);
                        // for(ItemStack out : a2)
                        GTTweaker.logGTRecipe(new ItemStack[] { a1 }, a2, a3, null, null, a4, a5, "sMaceratorRecipes");
                    }
                });
        }
    }
}
