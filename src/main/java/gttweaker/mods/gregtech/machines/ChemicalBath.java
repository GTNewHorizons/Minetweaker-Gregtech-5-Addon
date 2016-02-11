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
 * Provides access to the Chemical Bath recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ChemicalBath")
@ModOnly(MOD_ID)
public class ChemicalBath {
    /**
     * Adds a Chemical Bath recipe.
     *
     * @param output        outputs 1-3
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chances       chance of 3 outputs
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack fluidInput, int[] chances, int durationTicks, int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("chemical bath requires at least 1 output");
        } else {
            MineTweakerAPI.apply(new AddRecipeAction(output, input, fluidInput, chances, durationTicks, euPerTick));
        }
    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack[] output;
        private final IIngredient input;
        private final ILiquidStack fluidInput;
        private final int[] chances;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack[] output, IIngredient input, ILiquidStack fluidInput, int[] chances, int duration, int euPerTick) {

            this.output = output;
            this.input = input;
            this.fluidInput = fluidInput;
            this.chances = chances;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addChemicalBathRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    output.length > 2 ? MineTweakerMC.getItemStack(output[2]) : null,
                    chances,
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Chemical Bath recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 8 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 8 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 8 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 8 * hash + this.duration;
            hash = 8 * hash + this.euPerTick;

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
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {

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