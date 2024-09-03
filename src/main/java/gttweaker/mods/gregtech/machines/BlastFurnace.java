package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.blastFurnaceRecipes;
import static gregtech.api.util.GTRecipeConstants.COIL_HEAT;
import static gttweaker.util.ArrayHelper.itemOrNull;

import java.util.ArrayList;
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
 * Provides access to the Blast Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.BlastFurnace")
@ModOnly("gregtech")
public class BlastFurnace {

    /**
     * Adds a Blast Furnace recipe.
     *
     * @param output        recipe output 1+2
     * @param fluidInput    primary fluidInput
     * @param input         recipes input 1+2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidOutput, IIngredient[] input,
        ILiquidStack fluidInput, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0 || input.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input and 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Blast furnace recipe for " + output[0],
                    input[0],
                    itemOrNull(input, 1),
                    fluidInput,
                    fluidOutput,
                    output[0],
                    itemOrNull(output, 1),
                    durationTicks,
                    euPerTick,
                    heat) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input1 = i.nextItem();
                        ItemStack input2 = i.nextItem();
                        List<ItemStack> inputs = new ArrayList<>(Arrays.asList(input1, input2));
                        inputs.removeIf(Objects::isNull);

                        FluidStack fluidInput = i.nextFluid();
                        FluidStack fluidOutput = i.nextFluid();
                        ItemStack output1 = i.nextItem();
                        ItemStack output2 = i.nextItem();
                        List<ItemStack> outputs = new ArrayList<>(Arrays.asList(output1, output2));
                        outputs.removeIf(Objects::isNull);
                        int duration = i.nextInt();
                        int eut = i.nextInt();
                        int heatLevel = i.nextInt();
                        RA.stdBuilder()
                            .itemInputs(inputs.toArray(new ItemStack[0]))
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .metadata(COIL_HEAT, heatLevel)
                            .duration(duration)
                            .eut(eut)
                            .addTo(blastFurnaceRecipes);
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidInput, IIngredient[] input, int durationTicks,
        int euPerTick, int heat) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Blast furnace recipe for " + output[0],
                input[0],
                itemOrNull(input, 1),
                fluidInput,
                output[0],
                itemOrNull(output, 1),
                durationTicks,
                euPerTick,
                heat) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input1 = i.nextItem();
                    ItemStack input2 = i.nextItem();
                    List<ItemStack> inputs = new ArrayList<>(Arrays.asList(input1, input2));
                    inputs.removeIf(Objects::isNull);

                    FluidStack fluidInput = i.nextFluid();
                    ItemStack output1 = i.nextItem();
                    ItemStack output2 = i.nextItem();
                    List<ItemStack> outputs = new ArrayList<>(Arrays.asList(output1, output2));
                    outputs.removeIf(Objects::isNull);
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    int heatLevel = i.nextInt();
                    RA.stdBuilder()
                        .itemInputs(inputs.toArray(new ItemStack[0]))
                        .itemOutputs(outputs.toArray(new ItemStack[0]))
                        .fluidInputs(fluidInput)
                        .metadata(COIL_HEAT, heatLevel)
                        .duration(duration)
                        .eut(eut)
                        .addTo(blastFurnaceRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Blast furnace recipe for " + output[0],
                input[0],
                itemOrNull(input, 1),
                output[0],
                itemOrNull(output, 1),
                durationTicks,
                euPerTick,
                heat) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input1 = i.nextItem();
                    ItemStack input2 = i.nextItem();
                    List<ItemStack> inputs = new ArrayList<>(Arrays.asList(input1, input2));
                    inputs.removeIf(Objects::isNull);

                    ItemStack output1 = i.nextItem();
                    ItemStack output2 = i.nextItem();
                    List<ItemStack> outputs = new ArrayList<>(Arrays.asList(output1, output2));
                    outputs.removeIf(Objects::isNull);
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    int heatLevel = i.nextInt();
                    RA.stdBuilder()
                        .itemInputs(inputs.toArray(new ItemStack[0]))
                        .itemOutputs(outputs.toArray(new ItemStack[0]))
                        .metadata(COIL_HEAT, heatLevel)
                        .duration(duration)
                        .eut(eut)
                        .addTo(blastFurnaceRecipes);
                }
            });
    }
}
