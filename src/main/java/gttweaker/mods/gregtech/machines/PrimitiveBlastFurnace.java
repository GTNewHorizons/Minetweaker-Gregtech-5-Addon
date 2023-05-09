package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gregtech.PrimitiveBlastFurnace")
@ModOnly(MOD_ID)
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
                    RA.addPrimitiveBlastRecipe(
                        i.nextItem(),
                        i.nextItem(),
                        i.nextInt(),
                        i.nextItem(),
                        i.nextItem(),
                        i.nextInt());
                }
            });
    }
}
