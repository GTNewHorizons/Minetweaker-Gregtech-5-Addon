package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.cannerRecipes;
import static gttweaker.util.ArrayHelper.itemOrNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.minecraft.item.ItemStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provider access to the Canner recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Canner")
@ModOnly("gregtech")
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
                        ItemStack input1 = i.nextItem();
                        ItemStack input2 = i.nextItem();
                        ItemStack output1 = i.nextItem();
                        ItemStack output2 = i.nextItem();
                        List<ItemStack> outputs = new ArrayList<>(Arrays.asList(output1, output2));
                        outputs.removeIf(Objects::isNull);

                        int duration = i.nextInt();
                        int eut = i.nextInt();
                        RA.stdBuilder()
                            .itemInputs(input1, input2)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .duration(duration)
                            .eut(eut)
                            .addTo(cannerRecipes);
                    }
                });
        }
    }
}
