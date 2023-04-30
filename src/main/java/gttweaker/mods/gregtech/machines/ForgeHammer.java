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
 * Provides access to the Forge Hammer recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.ForgeHammer")
@ModOnly(MOD_ID)
public class ForgeHammer {

    /**
     * Add a Forge Hammer recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks forging duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding forge hammer recipe for " + output,
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
                    RA.addForgeHammerRecipe(a1, a2, a3, a4);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a2 },
                        null,
                        null,
                        a3,
                        a4,
                        "sHammerRecipes");
                }
            });
    }
}
