package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gregtech.api.enums.ItemList;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Provides access to the Centrifuge recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Centrifuge")
@ModOnly("gregtech")
public class Centrifuge {

    /**
     * Adds a Centrifuge recipe.
     *
     * @param outputs       array with 1-6 outputs
     * @param fluidOutput   primary fluid output
     * @param input1        primary input
     * @param input2        Cell input
     * @param fluidInput    primary fluid input
     * @param chances       chance 1-6
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2,
        ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Centrifuge must have at least 1 output");
        } else if (outputs.length != chances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Centrifuge recipe with Fluids for " + input1,
                    input1,
                    input2,
                    fluidOutput,
                    fluidInput,
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
                        ItemStack input1 = i.nextItem();
                        ItemStack input2 = i.nextItem();
                        FluidStack fluidInput = i.nextFluid();
                        FluidStack fluidOutput = i.nextFluid();
                        List<ItemStack> outputs = Arrays.asList(
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem());
                        outputs.removeIf(Objects::isNull);

                        int[] chances = i.nextIntArr();
                        int duration = i.nextInt();
                        int eut = i.nextInt();
                        RA.stdBuilder()
                                .itemInputs(input1, input2)
                                .itemOutputs(outputs.toArray(new ItemStack[0]))
                                .fluidInputs(fluidInput)
                                .fluidOutputs(fluidOutput)
                                .outputChances(chances)
                                .duration(duration)
                                .eut(eut)
                                .addTo(centrifugeRecipes);
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipeFuelCan(IItemStack[] outputs, IIngredient input, int numCans, int duration) {
        addRecipe(outputs, input, -numCans, duration);
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int cells, int durationTicks) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Centrifuge must have at least 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding centrifuge recipe with input " + input,
                    input,
                    cells,
                    outputs[0],
                    itemOrNull(outputs, 1),
                    itemOrNull(outputs, 2),
                    itemOrNull(outputs, 3),
                    itemOrNull(outputs, 4),
                    itemOrNull(outputs, 5),
                    durationTicks) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input1 = i.nextItem();
                        int cellNumber = i.nextInt();
                        ItemStack input2 = cellNumber> 0 ? ItemList.Cell_Empty.get(cellNumber) : null;
                        List<ItemStack> inputs = Arrays.asList(input1, input2);
                        inputs.removeIf(Objects::isNull);

                        FluidStack fluidInput = i.nextFluid();
                        FluidStack fluidOutput = i.nextFluid();
                        List<ItemStack> outputs = Arrays.asList(
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem(),
                                i.nextItem());
                        outputs.removeIf(Objects::isNull);

                        int[] chances = i.nextIntArr();
                        int duration = i.nextInt();
                        RA.stdBuilder()
                                .itemInputs(inputs.toArray(new ItemStack[0]))
                                .itemOutputs(outputs.toArray(new ItemStack[0]))
                                .fluidInputs(fluidInput)
                                .fluidOutputs(fluidOutput)
                                .outputChances(chances)
                                .duration(duration)
                                .eut(5)
                                .addTo(centrifugeRecipes);
                    }
                });
        }
    }
}
