package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.chemicalBathRecipes;
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
 * Provides access to the Chemical Bath recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ChemicalBath")
@ModOnly("gregtech")
public class ChemicalBath {

    /**
     * Adds a Chemical Bath recipe.
     *
     * @param output        outputs 1-3
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chance of 3 outputs
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack fluidInput, int[] chances,
        int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("chemical bath requires at least 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Chemical Bath recipe for " + input,
                    input,
                    fluidInput,
                    output[0],
                    itemOrNull(output, 1),
                    itemOrNull(output, 2),
                    chances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input = i.nextItem();
                        FluidStack fluidInput = i.nextFluid();
                        List<ItemStack> outputs = new ArrayList<>(
                            Arrays.asList(i.nextItem(), i.nextItem(), i.nextItem()));
                        outputs.removeIf(Objects::isNull);
                        int[] chances = i.nextIntArr();
                        int duration = i.nextInt();
                        int eut = i.nextInt();
                        RA.stdBuilder()
                            .itemInputs(input)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .outputChances(chances)
                            .fluidInputs(fluidInput)
                            .duration(duration)
                            .eut(eut)
                            .addTo(chemicalBathRecipes);
                    }
                });
        }
    }
}
