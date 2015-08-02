package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Distillation Tower recipes.
 *
 * @author DreamMasterXXL
 * @author Blood Asp
 */
@ZenClass("mods.gregtech.DistillationTowerLiq")
@ModOnly(MOD_ID)
public class DistillationTower {
    /**
     * Adds an Distillation Tower recipe.
     *
     * @param fluidInput     Fluid Input
     * @param fluidOutput    Up to 6 Fluid Outputs
     * @param itemOutput     Item output Slot
     * @param durationTicks  duration, in ticks
     * @param euPerTick      eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack[] fluidOutput, IItemStack itemOutput, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        if (fluidOutput.length < 1) {
            MineTweakerAPI.logError("Distillation Twower must have at least 1 Fluid output");
        }else {
            MineTweakerAPI.apply(new AddRecipeAction(fluidOutput, itemOutput, fluidInput, durationTicks, euPerTick));
        }
    }

    // ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final FluidStack[] fluidOutput;
        private final IItemStack itemOutput;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(ILiquidStack[] fluidOutput, IItemStack itemOutput, ILiquidStack fluidInput, int duration, int euPerTick) {

            this.fluidOutput = new FluidStack[fluidOutput.length];
            this.itemOutput = itemOutput;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;

            for(int i =0;i< fluidOutput.length;i++){
                this.fluidOutput[i]=MineTweakerMC.getLiquidStack(fluidOutput[i]);
            }

        }


        @Override
        public void apply() {
            RA.addDistillationTowerRecipe(
                    MineTweakerMC.getLiquidStack(fluidInput),
                    this.fluidOutput,
                    MineTweakerMC.getItemStack(itemOutput),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Distillation Tower recipe for " + fluidInput;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            hash = 9 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 9 * hash + Arrays.deepHashCode(this.fluidOutput);
            hash = 9 * hash + (this.itemOutput != null ? this.itemOutput.hashCode() : 0);
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
            if (this.fluidInput != other.fluidInput && (this.fluidInput == null || !this.fluidInput.equals(other.fluidInput))) {
                return false;
            }
            if (!Arrays.deepEquals(this.fluidOutput, other.fluidOutput)) {
                return false;
            }
            if (this.itemOutput != other.itemOutput && (this.itemOutput == null || !this.itemOutput.equals(other.itemOutput))) {
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