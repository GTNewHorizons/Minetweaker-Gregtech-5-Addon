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
     * @param input1 primary input
     * @param fluidInput1 primary fluidInput
     * @param output1 primery output
     * @param chances chances (not working now)
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack input1, ILiquidStack fluidInput1, IItemStack output1, int chances, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(input1, fluidInput1, output1, chances, durationTicks, euPerTick));
    }
    // ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {
        private final IItemStack input1;
        private final ILiquidStack fluidInput1;
        private final IItemStack output1;
        private final int chances;
        private final int duration;
        private final int euPerTick;
        public AddRecipeAction(IItemStack input1, ILiquidStack fluidInput1, IItemStack output1, int chances, int duration, int euPerTick) {

            this.input1 = input1;
            this.fluidInput1 = fluidInput1;
            this.output1 = output1;
            this.chances = chances;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }
        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addAutoclaveRecipe(
                    MineTweakerMC.getItemStack(input1),
                    MineTweakerMC.getLiquidStack(fluidInput1),
                    MineTweakerMC.getItemStack(output1),
                    chances,
                    duration,
                    euPerTick);
        }
        @Override
        public String describe() {
            return "Adding assembler recipe for " + output1;
        }
        @Override
        public Object getOverrideKey() {
            return null;
        }
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 22 * hash + (this.input1 != null ? this.input1.hashCode() : 0);
            hash = 22 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
            hash = 22 * hash + (this.output1 != null ? this.output1.hashCode() : 0);
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
            if (this.input1 != other.input1 && (this.input1 == null || !this.input1.equals(other.input1))) {
                return false;
            }
            if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
                return false;
            }
            if (this.output1 != other.output1 && (this.output1 == null || !this.output1.equals(other.output1))) {
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