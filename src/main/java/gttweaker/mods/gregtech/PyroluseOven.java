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
 * Provides access to the PyroluseOven recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PyroluseOven")
@ModOnly(MOD_ID)
public class PyroluseOven {
    /**
     * Adds a Pyroluse Oven recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   recipe Fluid output
     * @param circuit       circuit preset nr.
     * @param input         recipe input
     * @param fluidInput    recipe Fluid input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, int circuit, IItemStack input, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, fluidOutput, circuit, input, fluidInput, durationTicks, euPerTick));
    }

    // ######################
    // ### Action classes ###
    // ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final ILiquidStack fluidOutput;
        private final int circuit;
        private final IItemStack input;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output, ILiquidStack fluidOutput, int circuit, IItemStack input, ILiquidStack fluidInput, int duration, int euPerTick) {

            this.output = output;
            this.fluidOutput = fluidOutput;
            this.circuit = circuit;
            this.input = input;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addPyrolyseRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    circuit,
                    MineTweakerMC.getItemStack(output),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding PyroluseOven recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 98 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 98 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 98 * hash + this.circuit;
            hash = 98 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 98 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 98 * hash + this.duration;
            hash = 98 * hash + this.euPerTick;
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
            if (this.output != other.output && (this.output == null || !this.output.equals(other.output))) {
                return false;
            }
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.circuit != other.circuit) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
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

