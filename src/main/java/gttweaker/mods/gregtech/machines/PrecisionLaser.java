package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.RA;
import static gregtech.api.recipe.RecipeMaps.laserEngraverRecipes;
import static gregtech.api.util.GT_RecipeConstants.CLEANROOM;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Precision Laser recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PrecisionLaser")
@ModOnly("gregtech")
public class PrecisionLaser {

    /**
     * Adds a Laser Engraver recipe.
     *
     * @param output        recipe output
     * @param input1        Item input
     * @param input2        Lens input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param cleanroom     the cleanroom requirement
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick, boolean cleanroom) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Precision Laser recipe for " + output,
                input1,
                input2,
                output,
                durationTicks,
                euPerTick,
                cleanroom) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    RA.stdBuilder()
                            .itemInputs(i.nextItem(), i.nextItem())
                            .itemOutputs(i.nextItem())
                            .duration(i.nextInt())
                            .eut(i.nextInt())
                            .metadata(CLEANROOM, i.nextBool())
                            .addTo(laserEngraverRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        addRecipe(output, input1, input2, durationTicks, euPerTick, false);
    }
}
