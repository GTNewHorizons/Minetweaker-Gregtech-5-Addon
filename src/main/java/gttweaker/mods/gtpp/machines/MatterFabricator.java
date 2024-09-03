package gttweaker.mods.gtpp.machines;

import static gregtech.api.enums.GTValues.RA;
import static gtPlusPlus.api.recipe.GTPPRecipeMaps.multiblockMassFabricatorRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtpp.MatterFabricator")
@ModOnly("miscutils")
public class MatterFabricator {

    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding matter fabricator recipe for " + fluidOutput,
                fluidInput,
                fluidOutput,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .fluidInputs(i.nextFluid())
                        .fluidOutputs(i.nextFluid())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(multiblockMassFabricatorRecipes);

                }
            });
    }
}
