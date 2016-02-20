package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Amplifabricator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Amplifabricator")
@ModOnly(MOD_ID)
public class Amplifabricator {
    /**
     * Adds a Amplifabricator recipe.
     *
     * @param input        primary Input
     * @param duration     reaction time, in ticks
     * @param amount       amount in mb of Liquid UU Output
     *
     */
    @ZenMethod
    public static void addRecipe(IIngredient input,  int duration, int amount) {
        MineTweakerAPI.apply(new AddRecipeAction(input, duration, amount));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(IIngredient input, int duration, int amount) {
            super("Adding Amplifabricator recipe for " + input, input, duration, amount);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            RA.addAmplifier(
                    (ItemStack) args[0],
                    (Integer) args[1],
                    (Integer) args[2]);
        }
    }
}