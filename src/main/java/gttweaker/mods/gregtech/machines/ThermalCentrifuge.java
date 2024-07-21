package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.thermalCentrifugeRecipes;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gregtech.ThermalCentrifuge")
@ModOnly("gregtech")
public class ThermalCentrifuge {

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IItemStack output3, IIngredient input,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding thermal centrifuge recipe for " + output1,
                input,
                output1,
                output2,
                output3,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                        .itemInputs(i.nextItem())
                        .itemOutputs(i.nextItem(), i.nextItem(), i.nextItem())
                        .duration(i.nextInt())
                        .eut(i.nextInt())
                        .addTo(thermalCentrifugeRecipes);
                }
            });
    }
}
