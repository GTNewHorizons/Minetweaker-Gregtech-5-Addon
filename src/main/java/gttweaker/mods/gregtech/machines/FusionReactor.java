package gttweaker.mods.gregtech.machines;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Fusion Reactor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FusionReactor")
@ModOnly(MOD_ID)
public class FusionReactor {
    /**
     * Adds a Mixer recipe.
     *
     * @param fluidOutput    primary fluid Output
     * @param fluidInput1    primary fluid Input
     * @param fluidInput2    secondary fluid Input
     * @param durationTicks  reaction time, in ticks
     * @param euPerTick      eu consumption per tick
     * @param startEU        Starting at ... EU
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, ILiquidStack fluidInput1, ILiquidStack fluidInput2, int durationTicks, int euPerTick, int startEU) {
        MineTweakerAPI.apply(new AddRecipeAction(fluidOutput, fluidInput1, fluidInput2, durationTicks, euPerTick, startEU));
    }

    // ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final ILiquidStack fluidOutput;
        private final ILiquidStack fluidInput1;
        private final ILiquidStack fluidInput2;
        private final int duration;
        private final int euPerTick;
        private final int startEU;

        public AddRecipeAction(ILiquidStack fluidOutput, ILiquidStack fluidInput1, ILiquidStack fluidInput2, int duration, int euPerTick, int StartEU) {

            this.fluidOutput = fluidOutput;
            this.fluidInput1 = fluidInput1;
            this.fluidInput2 = fluidInput2;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.startEU = StartEU;
        }

        @Override
        public void apply() {
            RA.addFusionReactorRecipe(
                    MineTweakerMC.getLiquidStack(fluidInput2),
                    MineTweakerMC.getLiquidStack(fluidInput1),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    duration,
                    euPerTick,
                    startEU);
        }

        @Override
        public String describe() {
            return "Adding Fusion Reactor recipe for " + fluidOutput;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            hash = 99 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 99 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
            hash = 99 * hash + (this.fluidInput2 != null ? this.fluidInput2.hashCode() : 0);
            hash = 99 * hash + this.duration;
            hash = 99 * hash + this.euPerTick;
            hash = 99 * hash + this.startEU;
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
            if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
                return false;
            }
            if (this.fluidInput2 != other.fluidInput2 && (this.fluidInput2 == null || !this.fluidInput2.equals(other.fluidInput2))) {
                return false;
            }
            if (this.duration != other.duration) {
                return false;
            }
            if (this.euPerTick != other.euPerTick) {
                return false;
            }
            if (this.startEU != other.startEU) {
                return false;
            }
            return true;
        }
    }
}
