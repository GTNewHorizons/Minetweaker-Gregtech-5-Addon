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
 * Provides access to the Electrolyzer recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.ElectrolyzerLiq")
@ModOnly(MOD_ID)
public class ElectrolyzerLiq {
    /**
     * Adds a Electrolyzer recipe.
     *
     * @param output1  1st output
     * @param output2  2nd output
     * @param output3  3rd output
     * @param output4  4rd output
     * @param output5  5rd output
     * @param output6  6rd output
     * @param fluidOutput  primary fluid output
     * @param input primary input
     * @param cells Cell input
     * @param fluidInput  primary fluid input
     * @param chance1 chance of 1st output
     * @param chance2 chance of 2nd output
     * @param chance3 chance of 3rd output
     * @param chance4 chance of 4rd output
     * @param chance5 chance of 5rd output
     * @param chance6 chance of 6rd output
     * @param durationTicks reaction time, in ticks
     * @param euPerTick eu consumption per tick
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IItemStack output3, IItemStack output4, IItemStack output5, IItemStack output6, ILiquidStack fluidOutput, IItemStack input, IItemStack cells,  ILiquidStack fluidInput, int chance1, int chance2, int chance3, int chance4, int chance5, int chance6, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output1, output2, output3, output4, output5, output6, fluidOutput, input, cells, fluidInput, chance1, chance2, chance3, chance4, chance5, chance6, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output1;
        private final IItemStack output2;
        private final IItemStack output3;
        private final IItemStack output4;
        private final IItemStack output5;
        private final IItemStack output6;
        private final ILiquidStack fluidOutput;
        private final IItemStack input;
        private final IItemStack cells;
        private final ILiquidStack fluidInput;
        private final int [] chances;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output1, IItemStack output2, IItemStack output3, IItemStack output4, IItemStack output5, IItemStack output6, ILiquidStack fluidOutput, IItemStack input, IItemStack cells, ILiquidStack fluidInput, int chance1, int chance2, int chance3, int chance4, int chance5, int chance6, int duration, int euPerTick) {

            this.output1 = output1;
            this.output2 = output2;
            this.output3 = output3;
            this.output4 = output4;
            this.output5 = output5;
            this.output6 = output6;
            this.fluidOutput = fluidOutput;
            this.input = input;
            this.cells = cells;
            this.fluidInput = fluidInput;
            this.chances = new int[6];
            this.chances[0] =chance1;
            this.chances[1] =chance2;
            this.chances[2] =chance3;
            this.chances[3] =chance4;
            this.chances[4] =chance5;
            this.chances[5] =chance6;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }

        @Override
        public void apply() {
            RA.addElectrolyzerRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(cells),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput),
                    MineTweakerMC.getItemStack(output1),
                    MineTweakerMC.getItemStack(output2),
                    MineTweakerMC.getItemStack(output3),
                    MineTweakerMC.getItemStack(output4),
                    MineTweakerMC.getItemStack(output5),
                    MineTweakerMC.getItemStack(output6),
                    chances,
                    duration,
                    euPerTick);

        }

        @Override
        public String describe() {
            return "Adding Electrolyzer recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 8;
            hash = 44 * hash + (this.output1 != null ? this.output1.hashCode() : 0);
            hash = 44 * hash + (this.output2 != null ? this.output2.hashCode() : 0);
            hash = 44 * hash + (this.output3 != null ? this.output3.hashCode() : 0);
            hash = 44 * hash + (this.output4 != null ? this.output4.hashCode() : 0);
            hash = 44 * hash + (this.output5 != null ? this.output5.hashCode() : 0);
            hash = 44 * hash + (this.output6 != null ? this.output6.hashCode() : 0);
            hash = 44 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 44 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 44 * hash + (this.cells != null ? this.cells.hashCode() : 0);
            hash = 44 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 44 * hash + this.duration;
            hash = 44 * hash + this.euPerTick;

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

            }
            if (this.output2 != other.output2 && (this.output2 == null || !this.output2.equals(other.output2))) {

            }
            if (this.output3 != other.output3 && (this.output3 == null || !this.output3.equals(other.output3))) {

            }
            if (this.output4 != other.output4 && (this.output4 == null || !this.output4.equals(other.output4))) {

            }
            if (this.output5 != other.output5 && (this.output5 == null || !this.output5.equals(other.output5))) {

            }
            if (this.output6 != other.output6 && (this.output6 == null || !this.output6.equals(other.output6))) {

            }
            if (this.fluidOutput != other.fluidOutput && (this.fluidOutput == null || !this.fluidOutput.equals(other.fluidOutput))) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {

            }
            if (this.cells != other.cells && (this.cells == null || !this.cells.equals(other.cells))) {

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
