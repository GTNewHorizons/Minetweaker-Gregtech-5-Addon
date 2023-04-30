package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;

import net.minecraft.item.ItemStack;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;

@ZenClass("mods.gregtech.Extractor")
@ModOnly(MOD_ID)
public class Extractor {

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding extractor recipe for " + output,
                input,
                output,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    int a3 = i.nextInt();
                    int a4 = i.nextInt();
                    RA.addExtractorRecipe(a1, a2, a3, a4);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a2 },
                        null,
                        null,
                        a3,
                        a4,
                        "sExtractorRecipes");
                }
            });
    }
}
