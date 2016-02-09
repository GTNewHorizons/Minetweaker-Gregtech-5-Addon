package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Electrolyzer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Electrolyzer")
@ModOnly(MOD_ID)
public class Electrolyzer {
    /**
     * Adds a Electrolyzer recipe.
     *
     * @param output        output 1-6
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
    public static void addRecipe(IItemStack[] output, ILiquidStack fluidOutput, IIngredient input, IIngredient cells,  ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (output.length < 1) {
            MineTweakerAPI.logError("Electrolyzer must have at least 1 output");
        } else if(output.length!=chances.length){
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        }else {
            MineTweakerAPI.apply(new AddFluidRecipeAction(output, fluidOutput, input, cells, fluidInput, chances, durationTicks, euPerTick));
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int cells, int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Electrolyzer recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(output, input, cells, durationTicks, euPerTick));
        }

    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {
        private final IItemStack[] output;
        private final IIngredient input;
        private final int cells;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack[] output, IIngredient input, int cells, int duration, int euPerTick) {
            this.output = output;
            this.input = input;
            this.cells = cells;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addElectrolyzerRecipe(
                    MineTweakerMC.getItemStack(input),
                    cells,
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    output.length > 2 ? MineTweakerMC.getItemStack(output[2]) : null,
                    output.length > 3 ? MineTweakerMC.getItemStack(output[3]) : null,
                    output.length > 4 ? MineTweakerMC.getItemStack(output[4]) : null,
                    output.length > 5 ? MineTweakerMC.getItemStack(output[5]) : null,
                    duration,
                    cells);
        }

        @Override
        public String describe() {
            return "Adding electrolyzer recipe with input " + input;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Arrays.deepHashCode(this.output);
            hash = 97 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 97 * hash + this.cells;
            hash = 97 * hash + this.duration;
            hash = 97 * hash + this.euPerTick;
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
            if (this.euPerTick != other.euPerTick) {
                return false;
            }
            return true;
        }
    }

    private static class AddFluidRecipeAction extends OneWayAction {

        private final IItemStack [] output;
        private final ILiquidStack fluidOutput;
        private final IIngredient input;
        private final IIngredient cells;
        private final ILiquidStack fluidInput;
        private final int [] chances;
        private final int duration;
        private final int euPerTick;

        public AddFluidRecipeAction(IItemStack [] output, ILiquidStack fluidOutput, IIngredient input, IIngredient cells, ILiquidStack fluidInput, int [] chances, int duration, int euPerTick) {

            this.output = output;
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
            RA.addElectrolyzerRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(cells),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    output.length > 2 ? MineTweakerMC.getItemStack(output[2]) : null,
                    output.length > 3 ? MineTweakerMC.getItemStack(output[3]) : null,
                    output.length > 4 ? MineTweakerMC.getItemStack(output[4]) : null,
                    output.length > 5 ? MineTweakerMC.getItemStack(output[5]) : null,
                    chances,
                    duration,
                    euPerTick);

        }

        @Override
        public String describe() {
            return "Adding Electrolyzer recipe with Liquid support for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 8;
            hash = 44 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 44 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 44 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 44 * hash + (this.cells != null ? this.cells.hashCode() : 0);
            hash = 44 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 44 * hash + (this.chances != null ? this.chances.hashCode() : 0);
            hash = 44 * hash + this.duration;
            hash = 44 * hash + this.euPerTick;

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

            }
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {

            }
            if (this.cells != other.cells && (this.cells == null || !this.cells.equals(other.cells))) {

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
