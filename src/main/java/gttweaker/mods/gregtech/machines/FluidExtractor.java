package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Fluid Extractor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidExtractor")
@ModOnly(MOD_ID)
public class FluidExtractor {

    /**
     * Adds a Fluid Extractor recipe.
     *
     * @param output        output Slot
     * @param input         input Slot
     * @param fluidOutput   fluidOutput Slot
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param chance        chance output slot
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidOutput, int durationTicks,
        int euPerTick, int chance) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fluid Extractor recipe for " + input,
                input,
                output,
                fluidOutput,
                durationTicks,
                euPerTick,
                chance) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    FluidStack a3 = i.nextFluid();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();
                    int a6 = i.nextInt();
                    RA.addFluidExtractionRecipe(a1, a2, a3, a4, a5, a6);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a2 },
                        new int[] { a6 },
                        null,
                        new FluidStack[] { a3 },
                        a4,
                        a5,
                        "sFluidExtractionRecipes");
                }
            });
    }
}
