package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.latheRecipes;
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
 * Access point for Lathe recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Lathe")
@ModOnly("gregtech")
public class Lathe {

    /**
     * Adds a lathe recipe with a single output.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks crafting duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        addRecipe(new IItemStack[] { output, null }, input, durationTicks, euPerTick);
    }

    /**
     * Adds a lathe recipe with 1 or 2 outputs.
     *
     * @param outputs       array with 1-2 outputs
     * @param input         recipe input
     * @param durationTicks crafting duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int durationTicks, int euPerTick) {
        if (outputs.length == 0) {
            MineTweakerAPI.logError("Lathe recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding lathe recipe for " + outputs[0],
                    input,
                    outputs[0],
                    itemOrNull(outputs, 1),
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input = i.nextItem();
                        List<ItemStack> outputs = new ArrayList<>(Arrays.asList(i.nextItem(), i.nextItem()));
                        outputs.removeIf(Objects::isNull);

                        RA.stdBuilder()
                            .itemInputs(input)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(latheRecipes);
                    }
                });
        }
    }
}
