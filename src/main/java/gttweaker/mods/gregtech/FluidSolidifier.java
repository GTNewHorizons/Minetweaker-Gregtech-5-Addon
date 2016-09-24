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
 * Provides access to the Fluid Solidifier recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidSolidifier")
@ModOnly(MOD_ID)
public class FluidSolidifier {
    /**
     * Adds a Fluid Solidifier recipe.
     *
     * @param output        output Slot
     * @param Mold          mold Slot
     * @param fluidInput    fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack Mold, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, Mold, fluidInput, durationTicks, euPerTick));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final IItemStack Mold;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output, IItemStack Mold, ILiquidStack fluidInput, int duration, int euPerTick) {

            this.output = output;
            this.Mold = Mold;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addFluidSolidifierRecipe(
                    MineTweakerMC.getItemStack(Mold),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);

        }

        @Override
        public String describe() {
            return "Adding Fluid Solidifier recipe for " + output ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 94 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 94 * hash + (this.Mold != null ? this.Mold.hashCode() : 0);
            hash = 94 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 94 * hash + this.duration;
            hash = 94 * hash + this.euPerTick;

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
            if (this.Mold != other.Mold && (this.Mold == null || !this.Mold.equals(other.Mold))) {

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