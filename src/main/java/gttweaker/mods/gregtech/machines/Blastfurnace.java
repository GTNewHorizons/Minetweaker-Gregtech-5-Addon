package gttweaker.mods.gregtech.machines;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Blast Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.BlastFurnace")
@ModOnly(MOD_ID)
public class Blastfurnace {
    /**
     * Adds a Blast Furnace recipe.
     *
     * @param output        recipe output 1+2
     * @param fluidInput    primary fluidInput
     * @param input         recipes input  1+2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     *
     */

    @ZenMethod
    public static void addRecipe(IItemStack[]output, ILiquidStack fluidInput, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddFluidRecipeAction(output, fluidInput, input, durationTicks, euPerTick, heat));
        }
    }
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient[] input, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(output, input, durationTicks, euPerTick, heat));
        }

    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack[] output;
        private final IIngredient[] input;
        private final int duration;
        private final int euPerTick;
        private final int heat;

        public AddRecipeAction(IItemStack[] output, IIngredient[] input, int duration, int euPerTick, int heat) {

            this.output = output;
            this.input = input;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.heat = heat;
        }

        @Override
        public void apply() {
            RA.addBlastRecipe(
                    MineTweakerMC.getItemStack(input[0]),
                    input.length > 1 ? MineTweakerMC.getItemStack(input[1]) : null,
                    MineTweakerMC.getLiquidStack(null),
                    MineTweakerMC.getLiquidStack(null),
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    duration,
                    euPerTick,
                    heat);
        }

        @Override
        public String describe() {
            return "Adding Blast furnace recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }

    private static class AddFluidRecipeAction extends OneWayAction {

        private final IItemStack[] output;
        private final ILiquidStack fluidInput;
        private final IIngredient[] input;
        private final int duration;
        private final int euPerTick;
        private final int heat;

        public AddFluidRecipeAction(IItemStack[] output, ILiquidStack fluidInput, IIngredient[] input, int duration, int euPerTick, int heat) {

            this.output = output;
            this.fluidInput = fluidInput;
            this.input = input;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.heat = heat;
        }

        @Override
        public void apply() {
            RA.addBlastRecipe(
                    MineTweakerMC.getItemStack(input[0]),
                    input.length > 1 ? MineTweakerMC.getItemStack(input[1]) : null,
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(null),
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    duration,
                    euPerTick,
                    heat);
        }

        @Override
        public String describe() {
            return "Adding Blast furnace recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 4;
            hash = 67 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 67 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 67 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 67 * hash + this.duration;
            hash = 67 * hash + this.euPerTick;
            hash = 67 * hash + this.heat;
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
            if (this.heat != other.heat) {
                return false;
            }
            return true;
        }
    }
}