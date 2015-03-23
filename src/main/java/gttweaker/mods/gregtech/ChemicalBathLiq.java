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
 * Provides access to the Chemical Bath recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 * @author Blood Asp
 */
@ZenClass("mods.gregtech.ChemicalBathLiq")
@ModOnly(MOD_ID)
public class ChemicalBathLiq {
    /**
     * Adds a Chemical Bath recipe.
     *
     * @param output1  first output
     * @param output2  second output
     * @param output3 third output
     * @param input primary input
     * @param fluidInput primary fluidInput
     * @param chance1 chance of 1st output
     * @param chance2 chance of 2nd output
     * @param chance3 chance of 3rd output
     * @param durationTicks reaction time, in ticks
     * @param euPerTick eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IItemStack output3, IItemStack input, ILiquidStack fluidInput, int chance1, int chance2, int chance3, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output1, output2, output3, input, fluidInput, chance1, chance2,  chance3, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output1;
        private final IItemStack output2;
        private final IItemStack output3;
        private final IItemStack input;
        private final ILiquidStack fluidInput;
        private final int [] chances;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output1, IItemStack output2, IItemStack output3, IItemStack input, ILiquidStack fluidInput, int chance1, int chance2, int chance3 , int duration, int euPerTick) {

            this.output1 = output1;
            this.output2 = output2;
            this.output3 = output3;
            this.input = input;
            this.fluidInput = fluidInput;
            this.chances = new int[3];
            this.chances[0] =chance1;
            this.chances[1] =chance2;
            this.chances[2] =chance3;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addChemicalBathRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getItemStack(output1),
                    MineTweakerMC.getItemStack(output2),
                    MineTweakerMC.getItemStack(output3),
                    chances,
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding chemical bath recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 8 * hash + (this.output1 != null ? this.output1.hashCode() : 0);
            hash = 8 * hash + (this.output2 != null ? this.output2.hashCode() : 0);
            hash = 8 * hash + (this.output3 != null ? this.output3.hashCode() : 0);
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
            if (this.output1 != other.output1 && (this.output1 == null || !this.output1.equals(other.output1))) {
                return false;
            }
            if (this.output2 != other.output2 && (this.output2 == null || !this.output2.equals(other.output2))) {
                return false;
            }
            if (this.output3 != other.output3 && (this.output3 == null || !this.output3.equals(other.output3))) {
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