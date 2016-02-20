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
 * Provides access to the Autoclave recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Autoclave")
@ModOnly(MOD_ID)
public class Autoclave {
    /**
     * Adds an Autoclave recipe.
     *
     * @param output        primary output
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chances
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chances, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input, fluidInput, chances, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chances, int duration, int euPerTick) {
            super("Adding Autoclave recipe for " + output, input, fluidInput, output, chances, duration, euPerTick);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            RA.addAutoclaveRecipe(
                    (ItemStack) args[0],
                    (FluidStack) args[1],
                    (ItemStack) args[2],
                    (Integer) args[3],
                    (Integer) args[4],
                    (Integer) args[5]);
        }
    }
}
