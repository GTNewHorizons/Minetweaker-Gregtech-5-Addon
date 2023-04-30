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
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2,
        ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Centrifuge must have at least 1 output");
        } else if (outputs.length != chances.length) {
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Centrifuge recipe with Fluids for " + input1,
                    input1,
                    input2,
                    fluidOutput,
                    fluidInput,
                    outputs[0],
                    itemOrNull(outputs, 1),
                    itemOrNull(outputs, 2),
                    itemOrNull(outputs, 3),
                    itemOrNull(outputs, 4),
                    itemOrNull(outputs, 5),
                    chances,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        ItemStack a2 = i.nextItem();
                        FluidStack a3 = i.nextFluid();
                        FluidStack a4 = i.nextFluid();
                        ItemStack a5 = i.nextItem();
                        ItemStack a6 = i.nextItem();
                        ItemStack a7 = i.nextItem();
                        ItemStack a8 = i.nextItem();
                        ItemStack a9 = i.nextItem();
                        ItemStack a10 = i.nextItem();
                        int[] a11 = i.nextIntArr();
                        int a12 = i.nextInt();
                        int a13 = i.nextInt();
                        RA.addCentrifugeRecipe(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
                        GTTweaker.logGTRecipe(
                            new ItemStack[] { a1, a2 },
                            new ItemStack[] { a5, a6, a7, a8, a9, a10 },
                            a11,
                            new FluidStack[] { a4 },
                            new FluidStack[] { a3 },
                            a12,
                            a13,
                            "sCentrifugeRecipes");
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipeFuelCan(IItemStack[] outputs, IIngredient input, int numCans, int duration) {
        addRecipe(outputs, input, -numCans, duration);
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int cells, int durationTicks) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Centrifuge must have at least 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding centrifuge recipe with input " + input,
                    input,
                    cells,
                    outputs[0],
                    itemOrNull(outputs, 1),
                    itemOrNull(outputs, 2),
                    itemOrNull(outputs, 3),
                    itemOrNull(outputs, 4),
                    itemOrNull(outputs, 5),
                    durationTicks) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        RA.addCentrifugeRecipe(
                            i.nextItem(),
                            i.nextInt(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextItem(),
                            i.nextInt());
                    }
                });
        }
    }
}
