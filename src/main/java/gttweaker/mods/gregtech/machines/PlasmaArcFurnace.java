package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.plasmaArcFurnaceRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Plasma Arc Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PlasmaArcFurnace")
@ModOnly("gregtech")
public class PlasmaArcFurnace {

    /**
     * Adds an Arc Furnace recipe.
     *
     * @param outputs       1-4 recipe output
     * @param fluidOutput   primary fluidOutput
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param outChances    chances of 1-4 output
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input,
        ILiquidStack fluidInput, int[] outChances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Plasma Arc Furnace must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Plasma Arc Furnace recipe for " + input,
                    input,
                    fluidInput,
                    outputs,
                    fluidOutput,
                    outChances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input = i.nextItem();
                        FluidStack fluidInput = i.nextFluid();
                        ItemStack[] outputs = i.nextItemArr();
                        FluidStack fluidOutput = i.nextFluid();
                        int[] chances  =i.nextIntArr();
                        int duration = i.nextInt();
                        int eut = i.nextInt();

                        RA.stdBuilder()
                                .itemInputs(input)
                                .itemOutputs(outputs)
                                .outputChances(chances)
                                .fluidInputs(fluidInput)
                                .fluidOutputs(fluidOutput)
                                .duration(duration)
                                .eut(eut)
                                .addTo(plasmaArcFurnaceRecipes);
                    }
                });
        }
    }

    /**
     * Adds an Arc Furnace recipe.
     *
     * @param outputs       1-4 recipe output
     * @param fluidOutput   primary fluidOutput
     * @param input         primary input
     * @param outChances    chances of 1-4 output
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input, int[] outChances,
        int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Plasma Arc Furnace must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                    new AddMultipleRecipeAction(
                            "Adding Plasma Arc Furnace recipe for " + input,
                            input,
                            outputs,
                            fluidOutput,
                            outChances,
                            durationTicks,
                            euPerTick) {

                        @Override
                        protected void applySingleRecipe(ArgIterator i) {
                            ItemStack input = i.nextItem();
                            ItemStack[] outputs = i.nextItemArr();
                            FluidStack fluidOutput = i.nextFluid();
                            int[] chances  =i.nextIntArr();
                            int duration = i.nextInt();
                            int eut = i.nextInt();

                            RA.stdBuilder()
                                    .itemInputs(input)
                                    .itemOutputs(outputs)
                                    .outputChances(chances)
                                    .fluidOutputs(fluidOutput)
                                    .duration(duration)
                                    .eut(eut)
                                    .addTo(plasmaArcFurnaceRecipes);
                        }
                    });
        }
    }
}
