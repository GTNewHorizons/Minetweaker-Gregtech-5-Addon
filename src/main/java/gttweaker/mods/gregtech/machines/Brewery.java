package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;

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
     * @param output     primary fluid output
     * @param ingredient primary ingredient
     * @param input      primary fluid ingredient
     * @param hidden     hidden true or false
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack output, IIngredient ingredient, ILiquidStack input, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction("Adding Brewery recipe for " + output, ingredient, input, output, hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    FluidStack a2 = i.nextFluid();
                    FluidStack a3 = i.nextFluid();
                    boolean a4 = i.nextBool();
                    RA.addBrewingRecipe(a1, a2.getFluid(), a3.getFluid(), a4);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        null,
                        new FluidStack[] { new FluidStack(a2.getFluid(), 750) },
                        new FluidStack[] { new FluidStack(a3.getFluid(), 750) },
                        128,
                        4,
                        "sBrewingRecipes");
                }
            });
    }
}
