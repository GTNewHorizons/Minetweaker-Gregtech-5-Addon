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
 * Provides access to the Distillery recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Distillery")
@ModOnly(MOD_ID)
public class Distillery {
    /**
     * Adds a Distillery recipe.
     *
     * @param fluidOutput   Fluid output
     * @param Circuit       Circuit
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     * @param Hidden        hidden
     *
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IItemStack Circuit, ILiquidStack fluidInput, int durationTicks, int euPerTick, boolean Hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(fluidOutput, Circuit, fluidInput, durationTicks, euPerTick, Hidden));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final ILiquidStack fluidOutput;
        private final IItemStack Circuit;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;
        private final boolean Hidden;

        public AddRecipeAction(ILiquidStack fluidOutput, IItemStack Circuit, ILiquidStack fluidInput, int duration, int euPerTick, boolean Hidden) {

            this.fluidOutput = fluidOutput;
            this.Circuit = Circuit;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.Hidden = Hidden;
        }

        @Override
        public void apply() {
            RA.addDistilleryRecipe(
                    MineTweakerMC.getItemStack(Circuit),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    duration,
                    euPerTick,
                    Hidden);
        }

        @Override
        public String describe() {
            return "Adding Distillery recipe for " + fluidOutput ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 8 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 8 * hash + (this.Circuit != null ? this.Circuit.hashCode() : 0);
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
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.Circuit != other.Circuit && (this.Circuit == null || !this.Circuit.equals(other.Circuit))) {

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
            if (this.Hidden != other.Hidden) {
                return false;
            }
            return true;
        }
    }
}