package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the OilCracker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.OilCracker")
@ModOnly(MOD_ID)
public class OilCracker {
    /**
     * Adds a Pyrolyse Oven recipe.
     *
     * @param fluidOutput    recipe Fluid output
     * @param fluidInput     recipe Fluid input
     * @param durationTicks  reaction time, in ticks
     * @param euPerTick      eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(fluidOutput, fluidInput, durationTicks, euPerTick));
    }

    // ######################
    // ### Action classes ###
    // ######################
    private static class AddRecipeAction extends OneWayAction {

        private final ILiquidStack fluidOutput;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(ILiquidStack fluidOutput, ILiquidStack fluidInput, int duration, int euPerTick) {

            this.fluidOutput = fluidOutput;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addCrackingRecipe(
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding OilCracker recipe for " + fluidOutput;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 4;
            hash = 43 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 43 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 43 * hash + this.duration;
            hash = 43 * hash + this.euPerTick;
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
            if (this.euPerTick != other.euPerTick) {
                return false;
            }
            return true;
        }
    }
}

