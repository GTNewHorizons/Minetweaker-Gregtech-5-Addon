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
 * Provides access to the Packer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Packer")
@ModOnly(MOD_ID)
public class Packer {

    /**
     * Adds a Packer recipe.
     *
     * @param output        recipe output
     * @param input1        Item input Slot 1
     * @param input2        Item input Slot 2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Packer recipe for " + output,
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

                    RA.addBoxingRecipe(a1, a2, a3, a4, a5);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1, a2 },
                        new ItemStack[] { a3 },
                        null,
                        null,
                        a4,
                        a5,
                        "sBoxinatorRecipes");
                }
            });
    }
}
