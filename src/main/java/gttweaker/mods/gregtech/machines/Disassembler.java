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
 * Provides access to the Disassembler recipes.
 *
 * @author Pilad
 */
@ZenClass("mods.gregtech.Disassembler")
@ModOnly(MOD_ID)
public class Disassembler {

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding thermal disassembler recipe for " + input, input, outputs, durationTicks, euPerTick) {
            @Override
            protected void applySingleRecipe(ArgIterator i) {
                RA.addDisassemblerRecipe(i.nextItem(), i.nextItemArr(), i.nextInt(), i.nextInt());
            }
        });
    }

}
