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

@ZenClass("mods.gregtech.CircuitAssembler")
@ModOnly(MOD_ID)
public class CircuitAssembler {

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] inputs, ILiquidStack fluidInput, int durationTicks,
        int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding circuit assembler recipe for " + output,
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
                    RA.addCircuitAssemblerRecipe(a1, a2, a3, a4, a5);
                    GTTweaker.logGTRecipe(
                        a1,
                        new ItemStack[] { a3 },
                        new FluidStack[] { a2 },
                        null,
                        a4,
                        a5,
                        "sCircuitAssemblerRecipes");
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] inputs, int durationTicks, int euPerTick) {
        addRecipe(output, inputs, null, durationTicks, euPerTick);
    }
}
