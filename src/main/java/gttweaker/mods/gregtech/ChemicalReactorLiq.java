package gttweaker.mods.gregtech;

import gregtech.api.GregTech_API;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
/**
* Provides access to the Chemical Reactor recipes.
*
* @author Stan Hebben
* @author DreamMasterXXL
*/
@ZenClass("mods.gregtech.ChemicalReactorLiq")
@ModOnly(MOD_ID)
public class ChemicalReactorLiq {
    /**
     * Adds a Chemical Reactor recipe.
     *
     * @param output        recipe output
     * @param fluidOutput1  primary fluidInput
     * @param input1        primary input
     * @param input2        secondary input
     * @param fluidInput1   primary fluidInput
     * @param durationTicks reaction time, in ticks
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, ILiquidStack fluidOutput1, IItemStack input1, IItemStack input2, ILiquidStack fluidInput1, int durationTicks) {
        MineTweakerAPI.apply(new AddRecipeAction(output, fluidOutput1, input1, input2, fluidInput1, durationTicks));
    }

    // ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {
        private final IItemStack output;
        private final ILiquidStack fluidOutput1;
        private final IItemStack input1;
        private final IItemStack input2;
        private final ILiquidStack fluidInput1;
        private final int duration;

        public AddRecipeAction(IItemStack output, ILiquidStack fluidOutput1, IItemStack input1, IItemStack input2, ILiquidStack fluidInput1, int duration) {
            this.output = output;
            this.fluidOutput1 = fluidOutput1;
            this.input1 = input1;
            this.input2 = input2;
            this.fluidInput1 = fluidInput1;
            this.duration = duration;
        }

        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addChemicalRecipe(
                    MineTweakerMC.getItemStack(input1),
                    MineTweakerMC.getItemStack(input2),
                    MineTweakerMC.getLiquidStack(fluidInput1),
                    MineTweakerMC.getLiquidStack(fluidOutput1),
                    MineTweakerMC.getItemStack(output),
                    duration);
        }

        @Override
        public String describe() {
            return "Adding chemical reactor recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 12 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 12 * hash + (this.fluidOutput1 != null ? this.fluidOutput1.hashCode() : 0);
            hash = 12 * hash + (this.input1 != null ? this.input1.hashCode() : 0);
            hash = 12 * hash + (this.input2 != null ? this.input2.hashCode() : 0);
            hash = 12 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
            hash = 12 * hash + this.duration;
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
            if (this.fluidOutput1 != other.fluidOutput1 && (this.fluidOutput1 == null || !this.fluidOutput1.equals(other.fluidOutput1))) {
                return false;
            }
            if (this.input1 != other.input1 && (this.input1 == null || !this.input1.equals(other.input1))) {
                return false;
            }
            if (this.input2 != other.input2 && (this.input2 == null || !this.input2.equals(other.input2))) {
                return false;
            }
            if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
                return false;
            }
            if (this.duration != other.duration) {
                return false;
            }
            return true;
        }
    }
}
