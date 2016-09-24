package gttweaker.mods.gregtech.machines;

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
* Provides access to the Mixer recipes.
*
* @author DreamMasterXXL
*/
@ZenClass("mods.gregtech.Mixer")
@ModOnly(MOD_ID)
public class Mixer {
    /**
     * Adds a Mixer recipe.
     *
     * @param output        recipe output
     * @param fluidOutput   primary fluidInput
     * @param input         input 1-4
     * @param fluidInput    primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput, IItemStack[] input, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (input.length == 0) {
            MineTweakerAPI.logError("Lathe recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddFluidRecipeAction(output, fluidOutput, input, fluidInput, durationTicks, euPerTick));
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack[] input, int durationTicks, int euPerTick) {
        if (input.length == 0) {
            MineTweakerAPI.logError("Lathe recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(output, null, input, null, durationTicks, euPerTick));
        }
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddFluidRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final ILiquidStack fluidOutput;
        private final IItemStack[] input;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddFluidRecipeAction(IItemStack output, ILiquidStack fluidOutput, IItemStack[] input, ILiquidStack fluidInput, int duration, int euPerTick) {

            this.output = output;
            this.fluidOutput = fluidOutput;
            this.input = input;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addMixerRecipe(
                    MineTweakerMC.getItemStack(input[0]),
                    input.length > 1 ? MineTweakerMC.getItemStack(input[1]) : null,
                    input.length > 2 ? MineTweakerMC.getItemStack(input[2]) : null,
                    input.length > 3 ? MineTweakerMC.getItemStack(input[3]) : null,
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Mixer recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 6;
            hash = 67 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 67 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 67 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 67 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 67 * hash + this.duration;
            hash = 67 * hash + this.euPerTick;
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
            if (this.output != other.output && (this.output == null || !this.output.equals(other.output))) {
                return false;
            }
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
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
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final ILiquidStack fluidOutput;
        private final IItemStack[] input;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output, ILiquidStack fluidOutput, IItemStack[] input, ILiquidStack fluidInput, int duration, int euPerTick) {

            this.output = output;
            this.fluidOutput = fluidOutput;
            this.input = input;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addMixerRecipe(
                    MineTweakerMC.getItemStack(input[0]),
                    input.length > 1 ? MineTweakerMC.getItemStack(input[1]) : null,
                    input.length > 2 ? MineTweakerMC.getItemStack(input[2]) : null,
                    input.length > 3 ? MineTweakerMC.getItemStack(input[3]) : null,
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Mixer recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            hash = 17 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 17 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 17 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 17 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 17 * hash + this.duration;
            hash = 17 * hash + this.euPerTick;
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
