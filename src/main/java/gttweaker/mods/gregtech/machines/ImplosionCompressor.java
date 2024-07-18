package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.implosionRecipes;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static gregtech.api.util.GT_RecipeConstants.ADDITIVE_AMOUNT;
import static gttweaker.util.ArrayHelper.itemOrNull;

import gregtech.api.enums.TierEU;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Provides access to the Implosion Compressor recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.ImplosionCompressor")
@ModOnly("gregtech")
public class ImplosionCompressor {

    /**
     * Adds an implosion compressor recipe with a single output.
     *
     * @param output recipe output
     * @param input  primary input
     * @param tnt    amount of TNT needed
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int tnt) {
        addRecipe(new IItemStack[] { output, null }, input, tnt);
    }

    /**
     * Adds an implosion compressor recipe with one or two outputs.
     *
     * @param output array with 1-2 outputs
     * @param input  primary input
     * @param tnt    amount of TNT needed
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int tnt) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Implosion compressor recipe requires at least 1 output");
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Implosion compressor recipe for " + output[0],
                    input,
                    tnt,
                    output[0],
                    itemOrNull(output, 1)) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack input = i.nextItem();
                        int additiveAmount = i.nextInt();
                        ItemStack output1 = i.nextItem();
                        ItemStack output2 = i.nextItem();
                        List<ItemStack> outputs = Arrays.asList(output1, output2);
                        outputs.removeIf(Objects::isNull);
                        RA.stdBuilder()
                            .itemInputs(input)
                            .itemOutputs(outputs.toArray(new ItemStack[0]))
                            .duration(1*SECONDS)
                            .eut(TierEU.RECIPE_LV)
                            .metadata(ADDITIVE_AMOUNT, additiveAmount)
                            .addTo(implosionRecipes);
                    }
                });
        }
    }
}
