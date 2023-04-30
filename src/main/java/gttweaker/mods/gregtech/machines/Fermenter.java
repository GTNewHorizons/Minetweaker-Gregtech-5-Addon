package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;

import net.minecraftforge.fluids.FluidStack;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;

/**
 * Provides access to the Fermenter recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Fermenter")
@ModOnly(MOD_ID)
public class Fermenter {

    /**
     * Adds a Fermenter recipe.
     *
     * @param fluidOutput primary fluidOutput
     * @param fluidInput  primary fluidInput
     * @param duration    reaction time, in ticks
     * @param hidden      hidden
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int duration, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fermenter recipe for " + fluidOutput,
                fluidInput,
                fluidOutput,
                duration,
                hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    FluidStack a1 = i.nextFluid();
                    FluidStack a2 = i.nextFluid();
                    int a3 = i.nextInt();
                    boolean a4 = i.nextBool();
                    RA.addFermentingRecipe(a1, a2, a3, a4);
                    GTTweaker.logGTRecipe(
                        null,
                        null,
                        new FluidStack[] { a1 },
                        new FluidStack[] { a2 },
                        a3,
                        2,
                        "sFermentingRecipes");
                }
            });
    }
}
