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
 * Provides access to the Fluid Extractor recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidExtractor")
@ModOnly(MOD_ID)
public class FluidExtractor {
    /**
     * Adds a Fluid Extractor recipe.
     *
     * @param output        output Slot
     * @param input         input Slot
     * @param fluidOutput   fluidOutput Slot
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param chance        chance output slot
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input, ILiquidStack fluidOutput, int durationTicks, int euPerTick, int chance) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input, fluidOutput, durationTicks, euPerTick, chance));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final IItemStack input;
        private final ILiquidStack fluidOutput;
        private final int duration;
        private final int euPerTick;
        private final int chance;

        public AddRecipeAction(IItemStack output, IItemStack input, ILiquidStack fluidOutput, int duration, int euPerTick, int chance) {

            this.output = output;
            this.input = input;
            this.fluidOutput = fluidOutput;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.chance = chance;
        }

        @Override
        public void apply() {
            RA.addFluidExtractionRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(output),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    duration,
                    euPerTick,
                    chance);

        }

        @Override
        public String describe() {
            return "Adding Fluid Extractor recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 94 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 94 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 94 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 94 * hash + this.duration;
            hash = 94 * hash + this.euPerTick;
            hash = 94 * hash + this.chance;

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
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.duration != other.duration) {
                return false;
            }
            if (this.euPerTick != other.euPerTick) {
                    return false;
            }
            if (this.chance != other.chance) {
                return false;
            }
            return true;
        }
    }
}