package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Amplifabricator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.AmplifabricatorUU")
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
    public static void addRecipe(IItemStack input,  int duration, int amount) {
        MineTweakerAPI.apply(new AddRecipeAction(input, duration, amount));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack input;
        private final int duration;
        private final int amount;

        public AddRecipeAction(IItemStack input, int duration, int amount) {

            this.input = input;
            this.duration = duration;
            this.amount = amount;
        }

        @Override
        public void apply() {
            RA.addAmplifier(
                    MineTweakerMC.getItemStack(input),
                    duration,
                    amount);
        }

        @Override
        public String describe() {
            return "Adding Amplifabricator recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            hash = 12 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 12 * hash + this.duration;
            hash = 12 * hash + this.amount;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final AddRecipeAction other = (AddRecipeAction) obj;
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
                return false;
            }
            if (this.duration != other.duration) {
                return false;
            }
            if (this.amount != other.amount) {
                return false;
            }
            return true;
        }
    }
}
