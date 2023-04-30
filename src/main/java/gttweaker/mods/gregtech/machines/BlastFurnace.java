package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

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
 * Provides access to the Blast Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.BlastFurnace")
@ModOnly(MOD_ID)
public class BlastFurnace {

    /**
     * Adds a Blast Furnace recipe.
     *
     * @param output        recipe output 1+2
     * @param fluidInput    primary fluidInput
     * @param input         recipes input 1+2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidOutput, IIngredient[] input,
        ILiquidStack fluidInput, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0 || input.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input and 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Blast furnace recipe for " + output[0],
                    input[0],
                    itemOrNull(input, 1),
                    fluidInput,
                    fluidOutput,
                    output[0],
                    itemOrNull(output, 1),
                    durationTicks,
                    euPerTick,
                    heat) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        ItemStack a2 = i.nextItem();
                        FluidStack a3 = i.nextFluid();
                        FluidStack a4 = i.nextFluid();
                        ItemStack a5 = i.nextItem();
                        ItemStack a6 = i.nextItem();
                        int a7 = i.nextInt();
                        int a8 = i.nextInt();
                        int a9 = i.nextInt();
                        RA.addBlastRecipe(a1, a2, a3, a4, a5, a6, a7, a8, a9);
                        GTTweaker.logGTRecipe(
                            new ItemStack[] { a1, a2 },
                            new ItemStack[] { a5, a6 },
                            new FluidStack[] { a3 },
                            new FluidStack[] { a4 },
                            a7,
                            a8,
                            a9,
                            "sBlastRecipes");
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidInput, IIngredient[] input, int durationTicks,
        int euPerTick, int heat) {
        addRecipe(output, null, input, fluidInput, durationTicks, euPerTick, heat);
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        addRecipe(output, null, input, durationTicks, euPerTick, heat);
    }
}
