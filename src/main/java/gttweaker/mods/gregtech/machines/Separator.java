package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.electroMagneticSeparatorRecipes;
import static gttweaker.util.ArrayHelper.itemOrNull;

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
 * Provides access to the Separator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Separator")
@ModOnly("gregtech")
public class Separator {

    /**
     * Adds a Separator recipe.
     *
     * @param input         recipe input
     * @param output        Item output Slot 1-3
     * @param outChances    Item output chances
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int[] outChances, int durationTicks,
        int euPerTick) {
        if (output.length < 1) {
            MineTweakerAPI.logError("Seperator must have at least 1 output");
        } else if (output.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Separator recipe for " + input,
                    input,
                    output[0],
                    itemOrNull(output, 1),
                    itemOrNull(output, 2),
                    outChances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input = i.nextItem();
                        List<ItemStack> outputs = Arrays.asList(i.nextItem(), i.nextItem(), i.nextItem());
                        outputs.removeIf(Objects::isNull);

                        RA.stdBuilder()
                            .itemInputs(input)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .outputChances(i.nextIntArr())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(electroMagneticSeparatorRecipes);
                    }
                });
        }
    }
}
