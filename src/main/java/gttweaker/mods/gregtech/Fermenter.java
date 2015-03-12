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
     * @param fluidOutput  primary fluidOutput
     * @param fluidInput  primary fluidInput
     * @param duration reaction time, in ticks
     * @param Hidden hidden
     *
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int duration, boolean Hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(fluidOutput, fluidInput, duration, Hidden));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final ILiquidStack fluidOutput;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final boolean Hidden;

        public AddRecipeAction(ILiquidStack fluidOutput, ILiquidStack fluidInput, int duration, boolean Hidden) {

            this.fluidOutput = fluidOutput;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.Hidden = Hidden;
        }

        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addFermentingRecipe(
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    duration,
                    Hidden);


        }

        @Override
        public String describe() {
            return "Adding Fermenter recipe for " + fluidOutput ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 2;
            hash = 12 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 12 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
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
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.fluidInput != other.fluidInput && (this.fluidInput == null || !this.fluidInput.equals(other.fluidInput))) {
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