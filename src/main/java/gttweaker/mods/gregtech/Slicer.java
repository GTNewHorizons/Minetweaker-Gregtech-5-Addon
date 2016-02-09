package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
/**
 * Provides access to the Slicer recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Slicer")
@ModOnly(MOD_ID)
public class Slicer {
    /**
     * Adds an Slicer recipe.
     *
     * @param output        primary output
     * @param input         primary input
     * @param blade         blade Slot
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, IItemStack blade, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output, input, blade, durationTicks, euPerTick));
    }
// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {
        private final IItemStack output;
        private final IIngredient input;
        private final IItemStack blade;
        private final int duration;
        private final int euPerTick;
        public AddRecipeAction(IItemStack output, IIngredient input, IItemStack blade, int duration, int euPerTick) {

            this.output = output;
            this.input = input;
            this.blade = blade;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }
        @Override
        public void apply() {
            RA.addSlicerRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(blade),
                    MineTweakerMC.getItemStack(output),
                    duration,
                    euPerTick);
        }
        @Override
        public String describe() {
            return "Adding Slicer recipe for " + output;
        }
        @Override
        public Object getOverrideKey() {
            return null;
        }
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 37 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 37 * hash + (this.blade != null ? this.blade.hashCode() : 0);
            hash = 37 * hash + this.duration;
            hash = 37 * hash + this.euPerTick;
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
            if (this.blade != other.blade && (this.blade == null || !this.blade.equals(other.blade))) {
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
