package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.cutterRecipes;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Saw recipes.
 *
 * @author DreamMasterXXL
 * @author bculkin2442
 */
@ZenClass("mods.gregtech.CuttingSaw")
@ModOnly("gregtech")
public class CuttingSaw {

    /**
     * Adds a Cutting Saw recipe.
     *
     * @param output1       recipe output1
     * @param output2       recipe output2
     * @param input         primary input
     * @param lubricant     primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, ILiquidStack lubricant,
        int durationTicks, int euPerTick) {
        if (lubricant == null) {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Cutting Saw recipe for " + input,
                    input,
                    output1,
                    output2,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        RA.stdBuilder()
                            .itemInputs(i.nextItem())
                            .itemOutputs(i.nextItem(), i.nextItem())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(cutterRecipes);
                    }
                });
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Cutting Saw recipe for " + input,
                    input,
                    lubricant,
                    output1,
                    output2,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        RA.stdBuilder()
                            .itemInputs(i.nextItem())
                            .fluidInputs(i.nextFluid())
                            .itemOutputs(i.nextItem(), i.nextItem())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(cutterRecipes);
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack lubricant, int durationTicks,
        int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("canner requires at least 1 output");
        } else {
            addRecipe(output[0], itemOrNull(output, 1), input, lubricant, durationTicks, euPerTick);
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, IItemStack circuit,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Cutting Saw recipe for " + input,
                input,
                circuit,
                output1,
                output2,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItem(), i.nextItem())
                        .itemOutputs(i.nextItem(), i.nextItem())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(cutterRecipes);
                }
            });
    }
}
