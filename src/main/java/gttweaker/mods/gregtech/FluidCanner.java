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
 * Provides access to the Fluid Canner recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.FluidCanner")
@ModOnly(MOD_ID)
public class FluidCanner {
    /**
     * Adds a Fluid Canner recipe.
     *
     * @param output        output Slot
     * @param input         input Slot
     * @param fluidOutput   fluid Output Slot
     * @param fluidInput    fluid Input Slot
     *
     */
    @ZenMethod
    public static void addRecipe(IIngredient output, IIngredient input, ILiquidStack fluidOutput, ILiquidStack fluidInput) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input, fluidOutput, fluidInput));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IIngredient output;
        private final IIngredient input;
        private final ILiquidStack fluidOutput;
        private final ILiquidStack fluidInput;


        public AddRecipeAction(IIngredient output, IIngredient input, ILiquidStack fluidOutput, ILiquidStack fluidInput) {

            this.output = output;
            this.input = input;
            this.fluidOutput = fluidOutput;
            this.fluidInput = fluidInput;

        }

        @Override
        public void apply() {
            RA.addFluidCannerRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(output),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getLiquidStack(fluidOutput));

        }

        @Override
        public String describe() {
            return "Adding Fluid Canner recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 91 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 91 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 91 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 91 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);

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

            if (this.fluidInput != other.fluidInput && (this.fluidInput == null || !this.fluidInput.equals(other.fluidInput))) {
            return false;
            }
            return true;
        }
    }
}