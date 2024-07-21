package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.electrolyzerRecipes;
import static gttweaker.util.ArrayHelper.itemOrNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Electrolyzer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Electrolyzer")
@ModOnly("gregtech")
public class Electrolyzer {

    /**
     * Adds a Electrolyzer recipe.
     *
     * @param outputs       output 1-6
     * @param fluidOutput   primary fluid output
     * @param input         primary input
     * @param cells         Cell input
     * @param fluidInput    primary fluid input
     * @param chances       chance 1-6
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input, IIngredient cells,
        ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Electrolyzer must have at least 1 output");
        } else if (outputs.length != chances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Electrolyzer recipe with Liquid support for " + input,
                    input,
                    cells,
                    fluidInput,
                    fluidOutput,
                    outputs[0],
                    itemOrNull(outputs, 1),
                    itemOrNull(outputs, 2),
                    itemOrNull(outputs, 3),
                    itemOrNull(outputs, 4),
                    itemOrNull(outputs, 5),
                    chances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack[] inputs = new ItemStack[] { i.nextItem(), i.nextItem() };
                        FluidStack fluidInput = i.nextFluid();
                        FluidStack fluidOutput = i.nextFluid();
                        List<ItemStack> outputs = Arrays
                            .asList(i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem());
                        outputs.removeIf(Objects::isNull);

                        RA.stdBuilder()
                            .itemInputs(inputs)
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .outputChances(i.nextIntArr())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(electrolyzerRecipes);
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int cells, int durationTicks, int euPerTick) {
        if (outputs.length == 0) {
            MineTweakerAPI.logError("Electrolyzer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding electrolyzer recipe with input " + input,
                    input,
                    cells,
                    outputs[0],
                    itemOrNull(outputs, 1),
                    itemOrNull(outputs, 2),
                    itemOrNull(outputs, 3),
                    itemOrNull(outputs, 4),
                    itemOrNull(outputs, 5),
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack[] inputs = new ItemStack[] { i.nextItem(), i.nextItem() };
                        FluidStack fluidInput = i.nextFluid();
                        FluidStack fluidOutput = i.nextFluid();
                        List<ItemStack> outputs = Arrays
                            .asList(i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem(), i.nextItem());
                        outputs.removeIf(Objects::isNull);

                        RA.stdBuilder()
                            .itemInputs(inputs)
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .outputChances(i.nextIntArr())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .addTo(electrolyzerRecipes);
                    }
                });
        }
    }
}
