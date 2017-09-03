package gttweaker.mods.gregtech.machines;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Precision Laser recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PrecisionLaser")
@ModOnly(MOD_ID)
public class PrecisionLaser {
    /**
     * Adds a Laser Engraver recipe.
     *
     * @param output        recipe output
     * @param input1        Item input
     * @param input2        Lens input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick, boolean cleanroom) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding Precision Laser recipe for " + output, input1, input2, output, durationTicks, euPerTick, cleanroom) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                RA.addLaserEngraverRecipe(i.nextItem(), i.nextItem(), i.nextItem(), i.nextInt(), i.nextInt(), i.nextBool());
            }
        });
    }
    
    @ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
			addRecipe(output, input1, input2, durationTicks, euPerTick, false);
	}
}
