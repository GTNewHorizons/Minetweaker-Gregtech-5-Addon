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
 * Provides access to the Printer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Printer")
@ModOnly(MOD_ID)
public class Printer {
    /**
     * Adds a Printer recipe.
     *
     * @param output        recipe output
     * @param input         primary input
     * @param DataStick     Data Stick
     * @param ink           ink fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input, IItemStack DataStick, ILiquidStack ink, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input, DataStick, ink, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final IItemStack input;
        private final IItemStack DataStick;
        private final ILiquidStack ink;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output, IItemStack input, IItemStack DataStick, ILiquidStack ink, int duration, int euPerTick) {

            this.output = output;
            this.input = input;
            this.DataStick = DataStick;
            this.ink = ink;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addPrinterRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getLiquidStack(ink),
                    MineTweakerMC.getItemStack(DataStick),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Printer recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 66 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 66 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 66 * hash + (this.DataStick != null ? this.DataStick.hashCode() : 0);
            hash = 66 * hash + (this.ink != null ? this.ink.hashCode() : 0);
            hash = 66 * hash + this.duration;
            hash = 66 * hash + this.euPerTick;
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
                return false;
            }
            if (this.DataStick != other.DataStick && (this.DataStick == null || !this.DataStick.equals(other.DataStick))) {
                return false;
            }
            if (this.ink != other.ink && (this.ink == null || !this.ink.equals(other.ink))) {
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
