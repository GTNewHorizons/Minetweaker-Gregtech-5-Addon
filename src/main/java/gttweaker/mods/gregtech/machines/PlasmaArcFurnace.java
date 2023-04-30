package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;

/**
 * Provides access to the Plasma Arc Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PlasmaArcFurnace")
@ModOnly(MOD_ID)
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
                        ItemStack a1 = i.nextItem();
                        FluidStack a2 = i.nextFluid();
                        ItemStack[] a3 = i.nextItemArr();
                        FluidStack a4 = i.nextFluid();
                        int[] a5 = i.nextIntArr();
                        int a6 = i.nextInt();
                        int a7 = i.nextInt();
                        RA.addPlasmaArcFurnaceRecipe(a1, a2, a3, a4, a5, a6, a7);
                        GTTweaker.logGTRecipe(
                            new ItemStack[] { a1 },
                            a3,
                            a5,
                            new FluidStack[] { a2 },
                            new FluidStack[] { a4 },
                            a6,
                            a7,
                            "sPlasmaArcFurnaceRecipes");
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
        addRecipe(outputs, fluidOutput, input, null, outChances, durationTicks, euPerTick);
    }
}
