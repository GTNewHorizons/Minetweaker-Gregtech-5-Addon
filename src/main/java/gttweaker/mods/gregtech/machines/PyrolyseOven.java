package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.pyrolyseRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.util.GTUtility;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the PyrolyseOven recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PyrolyseOven")
@ModOnly("gregtech")
public class PyrolyseOven {

    /**
     * Adds a Pyrolyse Oven recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   recipe Fluid output
     * @param circuit       circuit preset nr.
     * @param input         recipe input
     * @param fluidInput    recipe Fluid input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, int circuit, IIngredient input,
        ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Pyrolyse Oven recipe for " + output,
                input,
                fluidInput,
                circuit,
                output,
                fluidOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input = i.nextItem();
                    FluidStack fluidInput = i.nextFluid();
                    ItemStack circuit = GTUtility.getIntegratedCircuit(i.nextInt());
                    RA.stdBuilder()
                        .itemInputs(input, circuit)
                        .fluidInputs(fluidInput)
                        .itemOutputs(i.nextItem())
                        .fluidOutputs(i.nextFluid())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(pyrolyseRecipes);
                }
            });
    }
}
