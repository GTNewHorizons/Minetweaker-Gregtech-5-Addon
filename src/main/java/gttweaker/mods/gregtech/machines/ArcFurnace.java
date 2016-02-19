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
 * Provides access to the Arc Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ArcFurnace")
@ModOnly(MOD_ID)
public class ArcFurnace {
    /**
     * Adds an Arc Furnace recipe.
     *
     * @param outputs       1-4 recipe output
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param outChances    chances of 1-4 output
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, ILiquidStack fluidInput, int[] outChances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Arc Furnace must have at least 1 output");
        } else if (outputs.length != outChances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, input, fluidInput, outChances, durationTicks, euPerTick));
        }
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends AddMultipleRecipeAction {

        public AddRecipeAction(IItemStack[] output, IIngredient input, ILiquidStack fluidInput, int[] outChances, int duration, int euPerTick) {
            super("Adding Arc Furnace recipe for " + input, input, fluidInput, output, outChances, duration, euPerTick);
        }

        @Override
        public void applySingleRecipe(Object... args) {
            RA.addSimpleArcFurnaceRecipe(
                    (ItemStack) args[0],
                    (FluidStack) args[1],
                    (ItemStack[]) args[2],
                    (int[]) args[3],
                    (Integer) args[4],
                    (Integer) args[5]);
        }
    }
}