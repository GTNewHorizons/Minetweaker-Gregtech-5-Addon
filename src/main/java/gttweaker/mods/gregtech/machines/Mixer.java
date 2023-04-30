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
 * Provides access to the Mixer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Mixer")
@ModOnly(MOD_ID)
public class Mixer {

    /**
     * Adds a Mixer recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   primary fluidInput
     * @param input         input 1-4
     * @param fluidInput    primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient[] input,
        ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (input.length == 0) {
            MineTweakerAPI.logError("Mixer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Mixer recipe for " + output,
                    input[0],
                    itemOrNull(input, 1),
                    itemOrNull(input, 2),
                    itemOrNull(input, 3),
                    fluidInput,
                    fluidOutput,
                    output,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        ItemStack a2 = i.nextItem();
                        ItemStack a3 = i.nextItem();
                        ItemStack a4 = i.nextItem();
                        FluidStack a5 = i.nextFluid();
                        FluidStack a6 = i.nextFluid();
                        ItemStack a7 = i.nextItem();
                        int a8 = i.nextInt();
                        int a9 = i.nextInt();
                        RA.addMixerRecipe(a1, a2, a3, a4, a5, a6, a7, a8, a9);
                        GTTweaker.logGTRecipe(
                            new ItemStack[] { a1, a2, a3, a4 },
                            new ItemStack[] { a7 },
                            new FluidStack[] { a5 },
                            new FluidStack[] { a6 },
                            a8,
                            a9,
                            "sMixerRecipes");
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] input, int durationTicks, int euPerTick) {
        addRecipe(output, null, input, null, durationTicks, euPerTick);
    }
}
