package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.fluidCannerRecipes;

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
 * Provides access to the Fluid Canner recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidCanner")
@ModOnly("gregtech")
public class FluidCanner {

    /**
     * Adds a Fluid Canner recipe.
     *
     * @param output      output Slot
     * @param input       input Slot
     * @param fluidOutput fluid Output Slot
     * @param fluidInput  fluid Input Slot
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidOutput,
        ILiquidStack fluidInput) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Fluid Canner recipe for " + input,
                input,
                output,
                fluidInput,
                fluidOutput) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input = i.nextItem();
                    ItemStack output = i.nextItem();
                    FluidStack fluidInput = i.nextFluid();
                    FluidStack fluidOutput = i.nextFluid();
                    int duration = fluidOutput == null ? fluidInput.amount / 62 : fluidOutput.amount / 62;
                    int eut = 1;
                    RA.stdBuilder()
                        .itemInputs(input)
                        .itemOutputs(output)
                        .fluidInputs(fluidInput)
                        .fluidOutputs()
                        .duration(duration)
                        .eut(eut)
                        .addTo(fluidCannerRecipes);
                }
            });
    }
}
