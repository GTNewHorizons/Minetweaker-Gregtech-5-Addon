package gttweaker.mods.gtpp.machines;

import static gregtech.api.enums.GTValues.RA;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.alloyBlastSmelterRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Adds a Recipe for the Alloy Blast Smelter. (up to 9 Inputs)
 *
 * @param aInput       = ItemStack[] (not null, and respects StackSize)
 * @param aFluidInput  = Input of a fluid (can be null, and respects StackSize)
 * @param aFluidOutput = Output of the Molten Metal (not null, and respects StackSize)
 * @param aChances     = Output Chance (can be == 0)
 * @param aDuration    = Duration (must be >= 0)
 * @param aEUt         = EU per tick needed for heating up (must be >= 0)
 * @return true if the Recipe got added, otherwise false.
 * @param aSpecialValue = Stores the Required Temp for the Recipe
 */

// inputs, fluidOutput, chance, durationTicks, euPerTick
// inputs, fluidInput, fluidOutput, chance, durationTicks, euPerTick
// inputs, fluidInput, fluidOutput, outputs, chance, durationTicks, euPerTick
// inputs, fluidInput, fluidOutput, chance, durationTicks, euPerTick, Special
// inputs, fluidInput, fluidOutput, outputs, chance, durationTicks, euPerTick, Special

@ZenClass("mods.gtpp.BlastSmelter")
@ModOnly("miscutils")
public class BlastSmelter {

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IIngredient[] inputs, int chance, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding alloy blast smelter recipe for " + fluidOutput,
                inputs,
                fluidOutput,
                chance,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack output = i.nextFluid();
                    i.nextInt(); // stupid code that asked for an output chance with no output added
                    int duration = i.nextInt();
                    int eut = i.nextInt();

                    RA.stdBuilder()
                        .itemInputs(inputs)
                        .fluidOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(alloyBlastSmelterRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, IIngredient[] inputs, int chance,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding alloy blast smelter recipe for " + fluidOutput,
                inputs,
                fluidInput,
                fluidOutput,
                chance,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack input = i.nextFluid();
                    FluidStack output = i.nextFluid();
                    i.nextInt(); // stupid code that asked for an output chance with no output added
                    int duration = i.nextInt();
                    int eut = i.nextInt();

                    RA.stdBuilder()
                        .itemInputs(inputs)
                        .fluidInputs(input)
                        .fluidOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(alloyBlastSmelterRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IIngredient[] outputs, ILiquidStack fluidOutput, ILiquidStack fluidInput,
        IIngredient[] inputs, int[] chance, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding alloy blast smelter recipe for " + outputs,
                inputs,
                fluidInput,
                fluidOutput,
                outputs,
                chance,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack input = i.nextFluid();
                    FluidStack output = i.nextFluid();
                    ItemStack[] outputs = i.nextItemArr();
                    int[] chances = i.nextIntArr();
                    int duration = i.nextInt();
                    int eut = i.nextInt();

                    RA.stdBuilder()
                        .itemInputs(inputs)
                        .itemOutputs(outputs)
                        .outputChances(chances)
                        .fluidInputs(input)
                        .fluidOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(alloyBlastSmelterRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, IIngredient[] inputs, int chance,
        int durationTicks, int euPerTick, int Special) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding alloy blast smelter recipe for " + fluidOutput,
                inputs,
                fluidInput,
                fluidOutput,
                chance,
                durationTicks,
                euPerTick,
                Special) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack input = i.nextFluid();
                    FluidStack output = i.nextFluid();
                    i.nextInt(); // unused
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    // special value is useless in ABS recipes

                    RA.stdBuilder()
                        .itemInputs(inputs)
                        .fluidInputs(input)
                        .fluidOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(alloyBlastSmelterRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IIngredient[] outputs, ILiquidStack fluidOutput, ILiquidStack fluidInput,
        IIngredient[] inputs, int[] chance, int durationTicks, int euPerTick, int Special) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding alloy blast smelter recipe for " + outputs,
                inputs,
                fluidInput,
                fluidOutput,
                outputs,
                chance,
                durationTicks,
                euPerTick,
                Special) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack input = i.nextFluid();
                    FluidStack output = i.nextFluid();
                    ItemStack[] outputs = i.nextItemArr();
                    int[] chances = i.nextIntArr();
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    // special value is useless in ABS recipes
                    RA.stdBuilder()
                        .itemInputs(inputs)
                        .itemOutputs(outputs)
                        .outputChances(chances)
                        .fluidInputs(input)
                        .fluidOutputs(output)
                        .duration(duration)
                        .eut(eut)
                        .addTo(alloyBlastSmelterRecipes);
                }
            });
    }
}
