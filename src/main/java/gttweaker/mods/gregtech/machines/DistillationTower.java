package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.distilleryRecipes;

import gregtech.api.util.GT_Utility;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Distillation Tower recipes.
 *
 * @author DreamMasterXXL
 * @author Blood Asp
 */
@ZenClass("mods.gregtech.DistillationTower")
@ModOnly("gregtech")
public class DistillationTower {

    /**
     * Adds an Distillation Tower recipe.
     *
     * @param fluidInput    Fluid Input
     * @param fluidOutput   Up to 6 Fluid Outputs
     * @param itemOutput    Item output Slot
     * @param durationTicks duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack[] fluidOutput, IItemStack itemOutput, ILiquidStack fluidInput,
        int durationTicks, int euPerTick) {
        if (fluidOutput.length < 1) {
            MineTweakerAPI.logError("Distillation Tower must have at least 1 Fluid output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Distillation Tower recipe for " + fluidInput.getDisplayName(),
                    fluidInput,
                    fluidOutput,
                    itemOutput,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        FluidStack fluidInput = i.nextFluid();
                        FluidStack[] fluidOutputs = i.nextFluidArr();
                        ItemStack output = i.nextItem();
                        int duration = i.nextInt();
                        int eut = i.nextInt();

                        RA.stdBuilder()
                                .itemOutputs(output)
                                .fluidInputs(fluidInput)
                                .fluidOutputs(fluidOutputs)
                                .duration(duration)
                                .eut(eut)
                                .addTo(distillationTowerRecipes);
                    }
                });
        }
    }

    @ZenMethod
    public static void addUniversalRecipe(ILiquidStack[] fluidOutput, IItemStack itemOutput, ILiquidStack fluidInput,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding universal distillation recipe for " + fluidInput.getDisplayName(),
                fluidInput,
                fluidOutput,
                itemOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    FluidStack fluidInput = i.nextFluid();
                    FluidStack[] fluidOutputs = i.nextFluidArr();
                    ItemStack output = i.nextItem();
                    int duration = i.nextInt();
                    int eut = i.nextInt();


                    for (int idx = 0; idx < Math.min(fluidOutputs.length, 11); idx++) {
                        RA.stdBuilder()
                                .itemInputs(GT_Utility.getIntegratedCircuit(idx+1))
                                .itemOutputs(output)
                                .fluidInputs(fluidInput)
                                .fluidOutputs(fluidOutputs[idx])
                                .duration(duration*2)
                                .eut(eut/4)
                                .addTo(distilleryRecipes);
                    }

                    RA.stdBuilder()
                            .itemOutputs(output)
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutputs)
                            .duration(duration)
                            .eut(eut)
                            .addTo(distillationTowerRecipes);
                }
            });
    }
}
