package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.circuitAssemblerRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
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
                    ItemStack[] inputs = i.nextItemArr();
                    FluidStack fluidInput = i.nextFluid();
                    ItemStack output = i.nextItem();
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    RA.stdBuilder()
                            .itemInputs(inputs)
                            .itemOutputs(output)
                            .fluidInputs(fluidInput)
                            .duration(duration)
                            .eut(eut)
                            .addTo(circuitAssemblerRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] inputs, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                        "Adding circuit assembler recipe for " + output,
                        inputs,
                        output,
                        durationTicks,
                        euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack[] inputs = i.nextItemArr();
                        ItemStack output = i.nextItem();
                        int duration = i.nextInt();
                        int eut = i.nextInt();
                        RA.stdBuilder()
                                .itemInputs(inputs)
                                .itemOutputs(output)
                                .duration(duration)
                                .eut(eut)
                                .addTo(circuitAssemblerRecipes);
                    }
                });
    }
}
