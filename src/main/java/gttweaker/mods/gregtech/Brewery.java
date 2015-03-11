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
 * Provides access to the Brewing Machine recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Brewery")
@ModOnly(MOD_ID)
public class Brewery {
    /**
     * Adds a Brewing Machine recipe.
     *
     * @param Ingredient primary input
     * @param fluidInput1   primary fluidInput
     * @param fluidOutput1  primary fluidOutput
     * @param Hidden hidden
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack Ingredient, ILiquidStack fluidInput1, ILiquidStack fluidOutput1, boolean Hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(Ingredient, fluidInput1, fluidOutput1, Hidden));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack Ingredient;
        private final ILiquidStack fluidInput1;
        private final ILiquidStack fluidOutput1;
        private final boolean Hidden;

        public AddRecipeAction(IItemStack Ingredient, ILiquidStack fluidInput1, ILiquidStack fluidOutput1, boolean Hidden) {

            this.Ingredient = Ingredient;
            this.fluidInput1 = fluidInput1;
            this.fluidOutput1 = fluidOutput1;
            this.Hidden = Hidden;
        }

        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addBrewingRecipe(
                    MineTweakerMC.getItemStack(Ingredient),
                    MineTweakerMC.getLiquidStack(fluidInput1).getFluid(),
                    MineTweakerMC.getLiquidStack(fluidOutput1).getFluid(),
                    Hidden);


        }

        @Override
        public String describe() {
            return "Adding brewery recipe for " + fluidOutput1 ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 8 * hash + (this.Ingredient != null ? this.Ingredient.hashCode() : 0);
            hash = 8 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
            hash = 8 * hash + (this.fluidOutput1 != null ? this.fluidOutput1.hashCode() : 0);

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
            if (this.Ingredient != other.Ingredient && (this.Ingredient == null || !this.Ingredient.equals(other.Ingredient))) {

            }
            if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
                return false;
            }
            if (this.fluidOutput1 != other.fluidOutput1 && (this.fluidOutput1 == null || !this.fluidOutput1.equals(other.fluidOutput1))) {
                return false;
            }
            if (this.Hidden != other.Hidden) {
                return false;
            }
            return true;
        }
    }
}