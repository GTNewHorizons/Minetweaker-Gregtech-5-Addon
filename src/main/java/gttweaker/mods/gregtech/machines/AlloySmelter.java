package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.alloySmelterRecipes;

import net.minecraft.item.ItemStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provider access to the Alloy Smelter recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.AlloySmelter")
@ModOnly("gregtech")
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
                    ItemStack input1 = i.nextItem();
                    ItemStack input2 = i.nextItem();
                    ItemStack output = i.nextItem();
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    RA.stdBuilder()
                        .itemInputs(input1, input2)
                        .itemOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(alloySmelterRecipes);
                }
            });
    }
}
