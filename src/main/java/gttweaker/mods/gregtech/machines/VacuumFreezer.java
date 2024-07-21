package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.vacuumFreezerRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.TierEU;
import gregtech.api.util.GT_Utility;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Vacuum Freezer recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.VacuumFreezer")
@ModOnly("gregtech")
public class VacuumFreezer {

    /**
     * Adds a vacuum freezer recipe.
     *
     * @param output        recipe output
     * @param input         recipe input
     * @param durationTicks freezing duration, in ticks
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction("Adding vacuum freezer recipe for " + output, input, output, durationTicks) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input = i.nextItem();
                    ItemStack output = i.nextItem();
                    int duration = i.nextInt();
                    RA.stdBuilder()
                        .itemInputs(input)
                        .itemOutputs(output)
                        .duration(duration)
                        .eut(TierEU.RECIPE_MV)
                        .addTo(vacuumFreezerRecipes);

                    FluidStack fluidInput = GT_Utility.getFluidForFilledItem(input, true);
                    FluidStack fluidOutput = GT_Utility.getFluidForFilledItem(output, true);
                    if (fluidInput != null && fluidOutput != null) {
                        RA.stdBuilder()
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .duration(duration)
                            .eut(TierEU.RECIPE_MV)
                            .addTo(vacuumFreezerRecipes);
                    }

                }
            });
    }
}
