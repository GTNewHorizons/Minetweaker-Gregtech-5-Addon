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
 * Provides access to the Printer recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Printer")
@ModOnly(MOD_ID)
public class Printer {
    /**
     * Adds a Printer recipe.
     *
     * @param output      recipe output
     * @param input1        primary input
     * @param DataStick        Data Stick
     * @param fluidInput   primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input1, IItemStack DataStick, ILiquidStack fluidInput, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input1, DataStick, fluidInput, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final IItemStack input1;
        private final IItemStack DataStick;
        private final ILiquidStack fluidInput;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output, IItemStack input1, IItemStack DataStick, ILiquidStack fluidInput, int duration, int euPerTick1) {

            this.output = output;
            this.input1 = input1;
            this.DataStick = DataStick;
            this.fluidInput = fluidInput;
            this.duration = duration;
            this.euPerTick = euPerTick1;
        }

        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addPrinterRecipe(
                    MineTweakerMC.getItemStack(input1),
                    MineTweakerMC.getLiquidStack(fluidInput),
                    MineTweakerMC.getItemStack(DataStick),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Printer recipe for " + output;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 66 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 66 * hash + (this.input1 != null ? this.input1.hashCode() : 0);
            hash = 66 * hash + (this.DataStick != null ? this.DataStick.hashCode() : 0);
            hash = 66 * hash + (this.fluidInput != null ? this.fluidInput.hashCode() : 0);
            hash = 66 * hash + this.duration;
            hash = 66 * hash + this.euPerTick;
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
            if (this.input1 != other.input1 && (this.input1 == null || !this.input1.equals(other.input1))) {
                return false;
            }
            if (this.DataStick != other.DataStick && (this.DataStick == null || !this.DataStick.equals(other.DataStick))) {
                return false;
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
