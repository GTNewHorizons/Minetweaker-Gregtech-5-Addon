package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

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
@ModOnly("gregtech")
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
                    ItemStack input = i.nextItem();
                    ItemStack output = i.nextItem();
                    FluidStack fluidOutput = i.nextFluid();
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    int chance = i.nextInt();

                    RA.stdBuilder()
                        .itemInputs(input)
                        .itemOutputs(output)
                        .outputChances(chance)
                        .fluidOutputs(fluidOutput)
                        .duration(duration)
                        .eut(eut)
                        .addTo(fluidExtractionRecipes);
                }
            });
    }
}
