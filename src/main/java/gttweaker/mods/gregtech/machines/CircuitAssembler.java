package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gregtech.CircuitAssembler")
@ModOnly("gregtech")
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
                    RA.addCircuitAssemblerRecipe(
                        i.nextItemArr(),
                        i.nextFluid(),
                        i.nextItem(),
                        i.nextInt(),
                        i.nextInt());
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] inputs, int durationTicks, int euPerTick) {
        addRecipe(output, inputs, null, durationTicks, euPerTick);
    }
}
