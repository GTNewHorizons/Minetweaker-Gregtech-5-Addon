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
 * Provides access to the Brewing Machine recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Brewery")
@ModOnly(MOD_ID)
public class Brewery {
    /**
     * Adds a Brewing Machine recipe.
     *
	 * @param fluidOutput  primary fluid output
     * @param Ingredient   primary input
     * @param fluidInput   primary fluid input
     * @param Hidden       hidden true or false
     *
     */
    @ZenMethod
    public static void addRecipe(ILiquidStack fluidOutput, IItemStack Ingredient, ILiquidStack fluidInput, boolean Hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(fluidOutput, Ingredient, fluidInput, Hidden));
    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack Ingredient;
        private final ILiquidStack fluidOutput;
        private final ILiquidStack fluidInput;
        private final boolean Hidden;

        public AddRecipeAction(ILiquidStack fluidOutput, IItemStack Ingredient, ILiquidStack fluidInput, boolean Hidden) {

            this.Ingredient = Ingredient;
            this.fluidOutput = fluidOutput;		
            this.fluidInput = fluidInput;
            this.Hidden = Hidden;
        }

        @Override
        public void apply() {
            RA.addBrewingRecipe(
                    MineTweakerMC.getItemStack(Ingredient),
			        MineTweakerMC.getLiquidStack(fluidOutput).getFluid(),
                    MineTweakerMC.getLiquidStack(fluidInput).getFluid(),
                    Hidden);

        }

        @Override
        public String describe() {
            return "Adding Brewery recipe for " + fluidOutput ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 9;
			hash = 8 * hash + (this.fluidOutput != null ? this.fluidOutput.hashCode() : 0);
            hash = 8 * hash + (this.Ingredient != null ? this.Ingredient.hashCode() : 0);
            hash = 8 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            
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
            if (this.Ingredient != other.Ingredient && (this.Ingredient == null || !this.Ingredient.equals(other.Ingredient))) {

            }
            if (this.fluidInput != other.fluidInput && (this.fluidInput == null || !this.fluidInput.equals(other.fluidInput))) {
                return false;
            }
            if (this.Hidden != other.Hidden) {
                return false;
            }
            return true;
        }
    }
}
