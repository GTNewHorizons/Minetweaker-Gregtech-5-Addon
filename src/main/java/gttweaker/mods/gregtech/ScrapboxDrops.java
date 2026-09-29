package gttweaker.mods.gregtech;

import gregtech.api.util.GTModHandler;
import gregtech.common.items.ItemScrapbox.ScrapDrop;
import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the item drops from opening Scrapboxes.
 *
 */
@ZenClass("mods.gregtech.ScrapboxDrops")
@ModOnly("gregtech")
public class ScrapboxDrops {

    @ZenMethod
    public static void addDrop(float weight, IItemStack output) {
        MineTweakerAPI.apply(new AddMultipleRecipeAction("Adding scrapbox drop for " + output, weight, output) {

            @Override
            protected void applySingleRecipe(ArgIterator i) {
                GTModHandler.addScrapboxDrop(i.nextFloat(), i.nextItem());
            }
        });
    }

    @ZenMethod
    public static void removeDrop(IItemStack output) {
        MineTweakerAPI.apply(new ScrapboxRecipeRemoveAction(output));
    }

    public static class ScrapboxRecipeRemoveAction implements IUndoableAction {

        IItemStack output;
        float weight;

        public ScrapboxRecipeRemoveAction(IItemStack output) {
            this.output = output;
        }

        @Override
        public void apply() {
            weight = ScrapDrop.removeDrop(MineTweakerMC.getItemStack(output));
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public void undo() {
            GTModHandler.addScrapboxDrop(weight, MineTweakerMC.getItemStack(output));
        }

        @Override
        public String describe() {
            return "Remove recipe from Scrapbox loot pool";
        }

        @Override
        public String describeUndo() {
            return "Re-add recipe to Scrapbox loot pool";
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }

}
