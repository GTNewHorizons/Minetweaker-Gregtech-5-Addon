package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Distillery recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Distillery")
@ModOnly(MOD_ID)
public class Distillery {

    /**
     * Adds a Distillery recipe.
     *
     * @param fluidOutput   Fluid output
     * @param circuit       Circuit
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param hidden        hidden
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IItemStack circuit, ILiquidStack fluidInput,
        int durationTicks, int euPerTick, boolean hidden) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Distillery recipe for " + fluidOutput,
                circuit,
                fluidInput,
                fluidOutput,
                durationTicks,
                euPerTick,
                hidden) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    FluidStack a2 = i.nextFluid();
                    FluidStack a3 = i.nextFluid();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();
                    boolean a6 = i.nextBool();
                    RA.addDistilleryRecipe(a1, a2, a3, a4, a5, a6);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        null,
                        new FluidStack[] { a2 },
                        new FluidStack[] { a3 },
                        a4,
                        a5,
                        "sDistilleryRecipes");
                }
            });
    }
}
