package gttweaker.mods.gregtech.machines;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Packer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Packer")
@ModOnly(MOD_ID)
public class Packer{
    /**
     * Adds a Packer recipe.
     *
     * @param output        recipe output
     * @param input1        Item input Slot 1
     * @param input2        Item input Slot 2
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IItemStack input1, IItemStack input2, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input1, input2, durationTicks, euPerTick));
    }
// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output;
        private final IItemStack input1;
        private final IItemStack input2;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output, IItemStack input1, IItemStack input2, int duration, int euPerTick) {

            this.output = output;
            this.input1 = input1;
            this.input2 = input2;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }
        @Override
        public void apply() {
            RA.addBoxingRecipe(
                    MineTweakerMC.getItemStack(input1),
                    MineTweakerMC.getItemStack(input2),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);
        }
        @Override
        public String describe() {return "Adding Packer recipe for " + output;}

        @Override
        public Object getOverrideKey() {return null;}

        @Override
        public int hashCode() {
            int hash = 1;
            hash = 39 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 39 * hash + (this.input1 != null ? this.input1.hashCode() : 0);
            hash = 39 * hash + (this.input2 != null ? this.input2.hashCode() : 0);
            hash = 39 * hash + this.duration;
            hash = 39 * hash + this.euPerTick;
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
            if (this.input2 != other.input2 && (this.input2 == null || !this.input2.equals(other.input2))) {
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
