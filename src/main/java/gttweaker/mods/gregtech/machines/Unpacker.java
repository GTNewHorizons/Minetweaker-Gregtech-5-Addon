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
 * Provides access to the Unpacker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Unpacker")
@ModOnly(MOD_ID)
public class Unpacker {

    /**
     * Adds a Unpacker recipe.
     *
     * @param output1       recipe output Slot 1
     * @param output2       recipe output Slot 2
     * @param input         recipe Input Slot
     * @param durationTicks duration time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Unpacker recipe for " + input,
                input,
                output1,
                output2,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    ItemStack a3 = i.nextItem();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();
                    RA.addUnboxingRecipe(a1, a2, a3, a4, a5);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a2, a3 },
                        null,
                        null,
                        a4,
                        a5,
                        "sUnboxinatorRecipes");
                }
            });
    }
}
