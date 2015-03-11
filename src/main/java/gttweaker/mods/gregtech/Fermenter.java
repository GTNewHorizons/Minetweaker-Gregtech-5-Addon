package gttweaker.mods.gregtech;

import gregtech.api.GregTech_API;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
/**
 * Provides access to the Fermenter recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Fermenter")
@ModOnly(MOD_ID)
public class Fermenter {
    /**
     * Adds a Fermenter recipe.
     *
     * @param fluidInput1   primary fluidInput
     * @param fluidOutput1  primary fluidOutput
     * @param duration reaction time, in ticks
     * @param Hidden hidden
     *
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidInput1, ILiquidStack fluidOutput1, int duration, boolean Hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(fluidInput1, fluidOutput1, duration, Hidden));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final ILiquidStack fluidInput1;
        private final ILiquidStack fluidOutput1;
        private final int duration;
        private final boolean Hidden;

        public AddRecipeAction(ILiquidStack fluidInput1, ILiquidStack fluidOutput1, int duration, boolean Hidden) {

            this.fluidInput1 = fluidInput1;
            this.fluidOutput1 = fluidOutput1;
            this.duration = duration;
            this.Hidden = Hidden;
        }

        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addFermentingRecipe(
                    MineTweakerMC.getLiquidStack(fluidInput1),
                    MineTweakerMC.getLiquidStack(fluidOutput1),
                    duration,
                    Hidden);


        }

        @Override
        public String describe() {
            return "Adding Fermenter recipe for " + fluidOutput1 ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 2;
            hash = 12 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
            hash = 12 * hash + (this.fluidOutput1 != null ? this.fluidOutput1.hashCode() : 0);
            hash = 12 * hash + this.duration;
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
            if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
                return false;
            }
            if (this.fluidOutput1 != other.fluidOutput1 && (this.fluidOutput1 == null || !this.fluidOutput1.equals(other.fluidOutput1))) {
                return false;
            }
            if (this.duration != other.duration) {
                return false;
            }
            if (this.Hidden != other.Hidden) {
                return false;
            }
            return true;
        }
    }
}