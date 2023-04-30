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
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    ItemStack a3 = i.nextItem();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();
                    boolean a6 = i.nextBool();
                    RA.addLaserEngraverRecipe(a1, a2, a3, a4, a5, a6);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1, a2 },
                        new ItemStack[] { a3 },
                        null,
                        null,
                        a4,
                        a5,
                        "sLaserEngraverRecipes");
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks,
        int euPerTick) {
        addRecipe(output, input1, input2, durationTicks, euPerTick, false);
    }
}
