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
     * @param output1       recipe output 1
     * @param output2       recipe output 2
     * @param fluidInput    primary fluidInput
     * @param input1        primary input
     * @param input2        secondary input
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param heat          heat in Kelvin
     *
     */

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, ILiquidStack fluidInput, IItemStack input1, IItemStack input2, int durationTicks, int euPerTick, int heat) {
        MineTweakerAPI.apply(new AddFluidRecipeAction(output1, output2, fluidInput, input1, input2, durationTicks, euPerTick, heat));
    }
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IItemStack input1, IItemStack input2, int durationTicks, int euPerTick, int heat) {
        if (output.length == 0) {
            MineTweakerAPI.logError("Blast furnace recipe requires at least 1 input");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(output[0], output.length > 1 ? output[1] : null, input1, input2, durationTicks, euPerTick, heat));
        }

    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output1;
        private final IItemStack output2;
        private final IItemStack input1;
        private final IItemStack input2;
        private final int duration;
        private final int euPerTick;
        private final int heat;

        public AddRecipeAction(IItemStack input1, IItemStack input2, IItemStack output1, IItemStack output2, int duration, int euPerTick, int heat) {

            this.output1 = output1;
            this.output2 = output2;
            this.input1 = input1;
            this.input2 = input2;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.heat = heat;
        }

        @Override
        public void apply() {
            RA.addBlastRecipe(
                    MineTweakerMC.getItemStack(input1),
                    MineTweakerMC.getItemStack(input2),
                    MineTweakerMC.getLiquidStack(null),
                    MineTweakerMC.getLiquidStack(null),
                    MineTweakerMC.getItemStack(output1),
                    MineTweakerMC.getItemStack(output2),
                    duration,
                    euPerTick,
                    heat);
        }

        @Override
        public String describe() {
            return "Adding Blast furnace recipe for " + output1;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }
    }

    private static class AddFluidRecipeAction extends OneWayAction {

        private final IItemStack output1;
        private final IItemStack output2;
        private final ILiquidStack fluidInput;
        private final IItemStack input1;
        private final IItemStack input2;
        private final int duration;
        private final int euPerTick;
        private final int heat;

        public AddFluidRecipeAction(IItemStack output1, IItemStack output2, ILiquidStack fluidInput, IItemStack input1, IItemStack input2, int duration, int euPerTick, int heat) {

            this.output1 = output1;
            this.output2 = output2;
            this.fluidInput = fluidInput;
            this.input1 = input1;
            this.input2 = input2;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.heat = heat;
        }

        @Override
        public void apply() {
            RA.addBlastRecipe(
                    MineTweakerMC.getItemStack(input1),
                    MineTweakerMC.getItemStack(input2),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(null),
                    MineTweakerMC.getItemStack(output1),
                    MineTweakerMC.getItemStack(output2),
                    duration,
                    euPerTick,
                    heat);
        }

        @Override
        public String describe() {
            return "Adding Blast furnace recipe for " + output1;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 4;
            hash = 67 * hash + (this.output1 != null ? this.output1.hashCode() : 0);
            hash = 67 * hash + (this.output2 != null ? this.output2.hashCode() : 0);
            hash = 67 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 67 * hash + (this.input1 != null ? this.input1.hashCode() : 0);
            hash = 67 * hash + (this.input2 != null ? this.input2.hashCode() : 0);
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
            if (this.output1 != other.output1 && (this.output1 == null || !this.output1.equals(other.output1))) {
                return false;
            }
            if (this.output2 != other.output2 && (this.output2 == null || !this.output2.equals(other.output2))) {
                return false;
            }
            if (this.input1 != other.input1 && (this.input1 == null || !this.input1.equals(other.input1))) {
                return false;
            }
            if (this.input2 != other.input2 && (this.input2 == null || !this.input2.equals(other.input2))) {
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