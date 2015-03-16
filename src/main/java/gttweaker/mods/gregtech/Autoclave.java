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
 * Provides access to the Autoclave recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Autoclave")
@ModOnly(MOD_ID)
public class Autoclave {
    /**
     * Adds an Autoclave recipe.
     *
     * @param output primery output
     * @param input primary input
     * @param fluidInput primary fluidInput
     * @param chances chances
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input, ILiquidStack fluidInput, int chances, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input, fluidInput, chances, durationTicks, euPerTick));
    }
// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {
	    private final IItemStack output;
        private final IItemStack input;
        private final ILiquidStack fluidInput;
        private final int chances;
        private final int duration;
        private final int euPerTick;
        public AddRecipeAction(IItemStack output, IItemStack input, ILiquidStack fluidInput, int chances, int duration, int euPerTick) {

            this.output = output;		
            this.input = input;
            this.fluidInput = fluidInput;
            this.chances = chances;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }
        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addAutoclaveRecipe(
                   MineTweakerMC.getItemStack(output),
                   MineTweakerMC.getLiquidStack(fluidInput),
				   MineTweakerMC.getItemStack(input),
                   chances,
                   duration,
                   euPerTick);
        }
        @Override
        public String describe() {
            return "Adding assembler recipe for " + output;
        }
        @Override
        public Object getOverrideKey() {
            return null;
        }
        @Override
        public int hashCode() {
            int hash = 7;
			hash = 22 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 22 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 22 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 77 * hash + this.duration;
            hash = 77 * hash + this.euPerTick;
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