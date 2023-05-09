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
 * Provides access to the Fluid Canner recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidCanner")
@ModOnly(MOD_ID)
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
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    FluidStack a3 = i.nextFluid();
                    FluidStack a4 = i.nextFluid();
                    RA.addFluidCannerRecipe(a1, a2, a3, a4);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a2 },
                        new FluidStack[] { a3 },
                        new FluidStack[] { a4 },
                        a4 == null ? a3.amount / 62 : a4.amount / 62,
                        1,
                        "sFluidCannerRecipes");

                }
            });
    }
}
