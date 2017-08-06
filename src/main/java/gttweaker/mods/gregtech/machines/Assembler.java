package gttweaker.mods.gregtech.machines;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
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
     * @param fluidInput    primary fluidInput
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        addRecipe(output, new IIngredient[]{input1, input2}, fluidInput, durationTicks, euPerTick);
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        addRecipe(output, new IIngredient[]{input1, input2}, null, durationTicks, euPerTick);
    }


    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] inputs, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding assembler recipe for " + output, inputs, fluidInput, output, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                RA.addAssemblerRecipe(i.nextItemArr(), i.nextFluid(), i.nextItem(), i.nextInt(), i.nextInt());
            }
        });
    }
}