package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Blast Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.BlastFurnace")
@ModOnly(MOD_ID)
public class Blastfurnace {
    /**
     * Adds a Blast Furnace recipe.
     *
     * @param output        recipe output 1+2
     * @param fluidInput    primary fluidInput
     * @param input         recipes input  1+2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     *
     */

    @ZenMethod
    public static void addRecipe(IItemStack[]output, ILiquidStack fluidInput, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddFluidRecipeAction(output, fluidInput, input, durationTicks, euPerTick, heat));
        }
    }
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(output, input, durationTicks, euPerTick, heat));
        }

    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(IItemStack[] output, IIngredient[] input, int duration, int euPerTick, int heat) {
            super("Adding Blast furnace recipe for " + output,
                    input[0], input.length > 1 ? input[1] : null,
                    output[0], output.length > 1 ? output[1] : null,
                    duration, euPerTick, heat);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            RA.addBlastRecipe(
                    (ItemStack) args[0],
                    (ItemStack) args[1],
                    null,
                    null,
                    (ItemStack) args[2],
                    (ItemStack) args[3],
                    (Integer) args[4],
                    (Integer) args[5],
                    (Integer) args[6]);
        }
    }

    private static class AddFluidRecipeAction extends AddMultipleRecipeAction {
        public AddFluidRecipeAction(IItemStack[] output, ILiquidStack fluidInput, IIngredient[] input, int duration, int euPerTick, int heat) {
            super("Adding Blast furnace recipe for " + output,
                    input[0], input.length > 1 ? input[1] : null,
                    fluidInput,
                    output[0], output.length > 1 ? output[1] : null,
                    duration, euPerTick, heat);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            RA.addBlastRecipe(
                    (ItemStack) args[0],
                    (ItemStack) args[1],
                    (FluidStack) args[2],
                    null,
                    (ItemStack) args[3],
                    (ItemStack) args[4],
                    (Integer) args[5],
                    (Integer) args[6],
                    (Integer) args[7]);
        }
    }
}