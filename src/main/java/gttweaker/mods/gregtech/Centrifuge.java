package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Centrifuge recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Centrifuge")
@ModOnly(MOD_ID)
public class Centrifuge {
    /**
     * Adds a Centrifuge recipe.
     *
     * @param outputs       array with 1-6 outputs
     * @param fluidOutput   primary fluid output
     * @param input         primary input
     * @param cells         Cell input
     * @param fluidInput    primary fluid input
     * @param chances       chance 1-6
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IItemStack input, IItemStack cells, ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Centrifuge must have at least 1 output");
        } else if(outputs.length!=chances.length){
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        }else {
            MineTweakerAPI.apply(new AddFluidRecipeAction(outputs, fluidOutput, input, cells, fluidInput, chances, durationTicks, euPerTick));
        }
    }

    @ZenMethod
    public static void addRecipeFuelCan(IItemStack[] outputs, IItemStack input, int numCans, int duration) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("centrifuge must have at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, input, -numCans, duration));
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IItemStack input, int cells, int durationTicks) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("centrifuge must have at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, input, cells, durationTicks));
        }
    }


// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {
        private final IItemStack[] output;
        private final IItemStack input;
        private final int cells;
        private final int duration;

        public AddRecipeAction(IItemStack[] output, IItemStack input, int cells, int duration) {
            this.output = output;
            this.input = input;
            this.cells = cells;
            this.duration = duration;
        }

        @Override
        public void apply() {
            RA.addCentrifugeRecipe(
                    MineTweakerMC.getItemStack(input),
                    cells,
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    output.length > 2 ? MineTweakerMC.getItemStack(output[2]) : null,
                    output.length > 3 ? MineTweakerMC.getItemStack(output[3]) : null,
                    output.length > 4 ? MineTweakerMC.getItemStack(output[4]) : null,
                    output.length > 5 ? MineTweakerMC.getItemStack(output[5]) : null,
                    duration);
        }

        @Override
        public String describe() {
            return "Adding centrifuge recipe with input " + input;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 59 * hash + Arrays.deepHashCode(this.output);
            hash = 59 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 59 * hash + this.cells;
            hash = 59 * hash + this.duration;
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
            if (!Arrays.deepEquals(this.output, other.output)) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
                return false;
            }
            if (this.cells != other.cells) {
                return false;
            }
            if (this.duration != other.duration) {
                return false;
            }
            return true;
        }
    }

    private static class AddFluidRecipeAction extends OneWayAction {

        private final IItemStack[] outputs;
        private final ILiquidStack fluidOutput;
        private final IItemStack input;
        private final IItemStack cells;
        private final ILiquidStack fluidInput;
        private final int [] chances;
        private final int duration;
        private final int euPerTick;

        public AddFluidRecipeAction(IItemStack[] outputs, ILiquidStack fluidOutput, IItemStack input, IItemStack cells, ILiquidStack fluidInput, int[] chances, int duration, int euPerTick) {

            this.outputs = outputs;
            this.fluidOutput = fluidOutput;
            this.input = input;
            this.cells = cells;
            this.fluidInput = fluidInput;
            this.chances = chances;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addCentrifugeRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(cells),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getItemStack(outputs[0]),
                    outputs.length > 1 ? MineTweakerMC.getItemStack(outputs[1]) : null,
                    outputs.length > 2 ? MineTweakerMC.getItemStack(outputs[2]) : null,
                    outputs.length > 3 ? MineTweakerMC.getItemStack(outputs[3]) : null,
                    outputs.length > 4 ? MineTweakerMC.getItemStack(outputs[4]) : null,
                    outputs.length > 5 ? MineTweakerMC.getItemStack(outputs[5]) : null,
                    chances,
                    duration,
                    euPerTick);

        }

        @Override
        public String describe() {
            return "Adding Centrifuge recipe with Fluids for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 6;
            hash = 55 * hash + (this.outputs != null ? this.outputs.hashCode() : 0);
            hash = 55 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 55 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 55 * hash + (this.cells != null ? this.cells.hashCode() : 0);
            hash = 55 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 55 * hash + this.duration;
            hash = 55 * hash + this.euPerTick;

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
            final AddFluidRecipeAction other = (AddFluidRecipeAction) obj;
            if (this.outputs != other.outputs && (this.outputs == null || !this.outputs.equals(other.outputs))) {
                return false;
            }
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
                return false;
            }
            if (this.cells != other.cells && (this.cells == null || !this.cells.equals(other.cells))) {
                return false;
            }
            if (this.fluidInput != other.fluidInput && (this.fluidInput == null || !this.fluidInput.equals(other.fluidInput))) {
                return false;
            }
            if (this.chances != other.chances) {
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