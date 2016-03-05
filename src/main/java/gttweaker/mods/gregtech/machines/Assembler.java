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
 * Provides access to the assembler recipes.
 *
 * @author Blood Asp
 * @author DreamMasterXXL
 * @author bculkin2442
 */
@ZenClass("mods.gregtech.Assembler")
@ModOnly(MOD_ID)
public class Assembler {
    /**
     * Adds an assemble recipe.
     *
     * @param output        recipe output
     * @param input1        primary input
     * @param input2        secondary input (optional, can be null)
     * @param fluidInput1   primary fluidInput
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, ILiquidStack fluidInput1, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddFluidRecipeAction(output, input1, input2, fluidInput1, durationTicks, euPerTick));
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input1, input2, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(IItemStack output, IIngredient input1, IIngredient input2, int duration, int euPerTick) {
            super("Adding assembler recipe for " + output, input1, input2, output, duration, euPerTick);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            int i = 0;
            RA.addAssemblerRecipe(
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (Integer) args[i++],
                    (Integer) args[i++]
            );
        }
    }

    private static class AddFluidRecipeAction extends AddMultipleRecipeAction {
        public AddFluidRecipeAction(IItemStack output, IIngredient input1, IIngredient input2, ILiquidStack fluidInput1, int duration, int euPerTick) {
            super("Adding assembler recipe with fluid Support for " + output, input1, input2, fluidInput1, output, duration, euPerTick);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            int i = 0;
            RA.addAssemblerRecipe(
                    (ItemStack) args[i++],
                    (ItemStack) args[i++],
                    (FluidStack) args[i++],
                    (ItemStack) args[i++],
                    (Integer) args[i++],
                    (Integer) args[i++]
            );
        }
    }
}