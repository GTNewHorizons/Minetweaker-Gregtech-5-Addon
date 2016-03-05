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
 * Provides access to the Centrifuge recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Centrifuge")
@ModOnly(MOD_ID)
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
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2, ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Centrifuge must have at least 1 output");
        } else if(outputs.length!=chances.length){
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        }else {
            MineTweakerAPI.apply(new AddFluidRecipeAction(outputs, fluidOutput, input1, input2, fluidInput, chances, durationTicks, euPerTick));
        }
    }

    @ZenMethod
    public static void addRecipeFuelCan(IItemStack[] outputs, IIngredient input, int numCans, int duration) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("centrifuge must have at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, input, -numCans, duration));
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int cells, int durationTicks) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("centrifuge must have at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, input, cells, durationTicks));
        }
    }


// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(IItemStack[] output, IIngredient input, int cells, int duration) {
            super("Adding centrifuge recipe with input " + input, input, cells, output[0],
                    output.length > 1 ? output[1] : null,
                    output.length > 2 ? output[2] : null,
                    output.length > 3 ? output[3] : null,
                    output.length > 4 ? output[4] : null,
                    output.length > 5 ? output[5] : null,
                    duration
            );
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            int i = 0;
            RA.addCentrifugeRecipe(
                    (ItemStack) args[i++],
                    (Integer) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (Integer) args[i++]
            );
        }
    }

    private static class AddFluidRecipeAction extends AddMultipleRecipeAction {
        public AddFluidRecipeAction(IItemStack[] output, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2, ILiquidStack fluidInput, int[] chances, int duration, int euPerTick) {
            super("Adding Centrifuge recipe with Fluids for " + input1, input1, input2, fluidOutput, fluidInput, output[0],
                    output.length > 1 ? output[1] : null,
                    output.length > 2 ? output[2] : null,
                    output.length > 3 ? output[3] : null,
                    output.length > 4 ? output[4] : null,
                    output.length > 5 ? output[5] : null,
                    chances, duration, euPerTick
            );
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            int i = 0;
            RA.addCentrifugeRecipe(
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (FluidStack) args[i++],
                    (FluidStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (int[]) args[i++],
                    (Integer) args[i++],
                    (Integer) args[i++]
            );
        }
    }
}