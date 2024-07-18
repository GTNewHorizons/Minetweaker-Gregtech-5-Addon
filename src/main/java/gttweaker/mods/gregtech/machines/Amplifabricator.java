package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.amplifierRecipes;

import gregtech.api.enums.Materials;
import gregtech.api.enums.TierEU;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Amplifabricator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Amplifabricator")
@ModOnly("gregtech")
public class Amplifabricator {

    /**
     * Adds a Amplifabricator recipe.
     *
     * @param input    primary Input
     * @param duration reaction time, in ticks
     */
    @ZenMethod
    public static void addRecipe(IIngredient input, int duration, int amount) {
        MineTweakerAPI
            .apply(new AddMultipleRecipeAction("Adding Amplifabricator recipe for " + input, input, duration, amount) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input = i.nextItem();
                    int duration = i.nextInt();
                    int UUAQuantity = i.nextInt();
                    RA.stdBuilder()
                            .itemInputs(input)
                            .duration(duration)
                            .fluidOutputs(Materials.UUAmplifier.getFluid(UUAQuantity))
                            .eut(TierEU.RECIPE_LV)
                            .addTo(amplifierRecipes);
                }
            });
    }
}
