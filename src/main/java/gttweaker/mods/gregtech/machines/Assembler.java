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
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, ILiquidStack fluidInput,
        int durationTicks, int euPerTick) {
        addRecipe(output, new IIngredient[] { input1, input2 }, fluidInput, durationTicks, euPerTick);
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        addRecipe(output, new IIngredient[] { input1, input2 }, null, durationTicks, euPerTick);
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] inputs, ILiquidStack fluidInput, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding assembler recipe for " + output,
                inputs,
                fluidInput,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack[] a1 = i.nextItemArr();
                    FluidStack a2 = i.nextFluid();
                    ItemStack a3 = i.nextItem();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();

                    RA.addAssemblerRecipe(a1, a2, a3, a4, a5);
                    GTTweaker.logGTRecipe(
                        a1,
                        new ItemStack[] { a3 },
                        new FluidStack[] { a2 },
                        null,
                        a4,
                        a5,
                        "sAssemblerRecipes");
                }
            });
    }
}
