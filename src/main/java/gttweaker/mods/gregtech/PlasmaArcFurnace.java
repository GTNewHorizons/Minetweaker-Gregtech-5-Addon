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

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Plasma Arc Furnace recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.PlasmaArcFurnace")
@ModOnly(MOD_ID)
public class PlasmaArcFurnace {
    /**
     * Adds an Arc Furnace recipe.
     *
     * @param outputs       1-4 recipe output
     * @param fluidOutput   primary fluidOutput
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param outChances    chances of 1-4 output
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, ILiquidStack fluidOutput, IItemStack input, ILiquidStack fluidInput, int[] outChances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Plasma Arc Furnace must have at least 1 output");
        } else if(outputs.length!=outChances.length){
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        }else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, fluidOutput, input, fluidInput, outChances, durationTicks, euPerTick));
        }
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack[] output;
        private final ILiquidStack fluidOutput;
        private final IItemStack input;
        private final ILiquidStack fluidInput;
        private final int[] chances;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack[] output, ILiquidStack fluidOutput, IItemStack input, ILiquidStack fluidInput, int[] outChances, int duration, int euPerTick) {

            this.output = output;
            this.input = input;
            this.fluidInput = fluidInput;
            this.fluidOutput = fluidOutput;
            this.chances = outChances;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addPlasmaArcFurnaceRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getItemStacks(output),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    chances,
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Plasma Arc Furnace recipe for " + input;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 9 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 9 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 9 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 9 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 9 * hash + this.duration;
            hash = 9 * hash + this.euPerTick;
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
            if (this.chances != other.chances){
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