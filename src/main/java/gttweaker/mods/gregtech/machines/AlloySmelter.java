package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provider access to the Alloy Smelter recipes.
 *
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.AlloySmelter")
@ModOnly(MOD_ID)
public class AlloySmelter {
    /**
     * Adds an alloy smelter recipe.
     *
     * @param output alloy smelter output
     * @param input1 primary input
     * @param input2 secondary input
     * @param durationTicks smelting time, in ticks
     * @param euPerTick eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input1, input2, durationTicks, euPerTick));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends AddMultipleRecipeAction {
        public AddRecipeAction(IItemStack output, IIngredient input1, IIngredient input2, int duration, int euPerTick) {
            super("Adding alloy smelter recipe for " + output, input1, input2, output, duration, euPerTick);
        }

        @Override
        protected void applySingleRecipe(Object[] args) {
            RA.addAlloySmelterRecipe(
                    (ItemStack) args[0],
                    (ItemStack) args[1],
                    (ItemStack) args[2],
                    (Integer) args[3],
                    (Integer) args[4]);
        }
    }
}
