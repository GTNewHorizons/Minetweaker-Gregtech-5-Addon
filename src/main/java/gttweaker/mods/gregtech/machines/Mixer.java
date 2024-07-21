package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Provides access to the Mixer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Mixer")
@ModOnly("gregtech")
public class Mixer {

    /**
     * Adds a Mixer recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   primary fluidInput
     * @param input         input 1-4
     * @param fluidInput    primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient[] input,
        ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (input.length == 0) {
            MineTweakerAPI.logError("Mixer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Mixer recipe for " + output,
                    input[0],
                    itemOrNull(input, 1),
                    itemOrNull(input, 2),
                    itemOrNull(input, 3),
                    fluidInput,
                    fluidOutput,
                    output,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        List<ItemStack> inputs = Arrays.asList(
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem()
                        );
                        inputs.removeIf(Objects::isNull);

                        RA.stdBuilder()
                                .itemInputs(inputs.toArray(new ItemStack[0]))
                                .fluidInputs(i.nextFluid())
                                .fluidOutputs(i.nextFluid())
                                .itemOutputs(i.nextItem())
                                .duration(i.nextInt())
                                .eut(i.nextInt())
                                .addTo(mixerRecipes);
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] input, int durationTicks, int euPerTick) {
        if (input.length == 0) {
            MineTweakerAPI.logError("Mixer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(
                    new AddMultipleRecipeAction(
                            "Adding Mixer recipe for " + output,
                            input[0],
                            itemOrNull(input, 1),
                            itemOrNull(input, 2),
                            itemOrNull(input, 3),
                            output,
                            durationTicks,
                            euPerTick) {

                        @Override
                        protected void applySingleRecipe(ArgIterator i) {
                            List<ItemStack> inputs = Arrays.asList(
                                    i.nextItem(),
                                    i.nextItem(),
                                    i.nextItem(),
                                    i.nextItem()
                            );
                            inputs.removeIf(Objects::isNull);

                            RA.stdBuilder()
                                    .itemInputs(inputs.toArray(new ItemStack[0]))
                                    .itemOutputs(i.nextItem())
                                    .duration(i.nextInt())
                                    .eut(i.nextInt())
                                    .addTo(mixerRecipes);
                        }
                    });
        }
    }
}
