package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.primitiveBlastRecipes;
import static gregtech.api.util.GT_RecipeConstants.ADDITIVE_AMOUNT;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gregtech.PrimitiveBlastFurnace")
@ModOnly("gregtech")
public class PrimitiveBlastFurnace {

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input1, IIngredient input2,
        int durationTicks, int coalAmount) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Primitive Blast Furnace recipe for " + output1,
                input1,
                input2,
                coalAmount,
                output1,
                output2,
                durationTicks) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                            .itemInputs(i.nextItem(), i.nextItem())
                            .metadata(ADDITIVE_AMOUNT, i.nextInt())
                            .itemOutputs(i.nextItem(), i.nextItem())
                            .duration(i.nextInt())
                            .addTo(primitiveBlastRecipes);
                }
            });
    }
}
