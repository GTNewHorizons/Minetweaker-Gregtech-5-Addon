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
 * Provides access to the Chemical Reactor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ChemicalReactor")
@ModOnly(MOD_ID)
public class ChemicalReactor {

    /**
     * Adds a Chemical Reactor recipe.
     *
     * @param output1       primary output
     * @param output2       secondary output
     * @param fluidOutput   primary fluidInput
     * @param input1        primary input
     * @param input2        secondary input
     * @param fluidInput    primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, ILiquidStack fluidOutput, IIngredient input1,
        IIngredient input2, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Chemical Reactor recipe for " + output1,
                input1,
                input2,
                fluidInput,
                fluidOutput,
                output1,
                output2,
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
                    int a7 = i.nextInt();
                    int a8 = i.nextInt();

                    RA.addChemicalRecipe(a1, a2, a3, a4, a5, a6, a7, a8);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1, a2 },
                        new ItemStack[] { a5, a6 },
                        new FluidStack[] { a3 },
                        new FluidStack[] { a4 },
                        a7,
                        a8,
                        "sChemicalRecipes");
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2,
        ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        addRecipe(output, null, fluidOutput, input1, input2, fluidInput, durationTicks, euPerTick);
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IIngredient input1, IIngredient input2,
        ILiquidStack fluidInput, int durationTicks) {
        addRecipe(output, fluidOutput, input1, input2, fluidInput, durationTicks, 30);
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks) {
        addRecipe(output, null, input1, input2, null, durationTicks);
    }
}
