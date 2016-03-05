package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Brewing Machine recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Brewery")
@ModOnly(MOD_ID)
public class Brewery {
    /**
     * Adds a Brewing Machine recipe.
     *
	 * @param output       primary fluid output
     * @param ingredient   primary ingredient
     * @param input        primary fluid ingredient
     * @param hidden       hidden true or false
     *
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack output, IIngredient ingredient, ILiquidStack input, boolean hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(output, ingredient, input, hidden));
    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(ILiquidStack output, IIngredient ingredient, ILiquidStack input, boolean hidden) {
            super("Adding Brewery recipe for " + output, ingredient, input, output, hidden);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            int i = 0;
            RA.addBrewingRecipe(
                    (ItemStack) args[i++],
                    ((FluidStack) args[i++]).getFluid(),
                    ((FluidStack) args[i++]).getFluid(),
                    (Boolean) args[i++]);
        }
    }
}
