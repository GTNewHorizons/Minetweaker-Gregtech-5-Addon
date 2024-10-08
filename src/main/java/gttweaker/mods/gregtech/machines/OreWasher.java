package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.oreWasherRecipes;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gregtech.OreWasher")
@ModOnly("gregtech")
public class OreWasher {

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IItemStack output3, IIngredient input,
        FluidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding ore washer recipe for " + output1,
                input,
                output1,
                output2,
                output3,
                fluidInput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItem())
                        .itemOutputs(i.nextItem(), i.nextItem(), i.nextItem())
                        .fluidInputs(i.nextFluid())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(oreWasherRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IItemStack output3, IIngredient input,
        int durationTicks, int euPerTick) {
        addRecipe(
            output1,
            output2,
            output3,
            input,
            FluidRegistry.getFluidStack("water", 1000),
            durationTicks,
            euPerTick);
    }
}
