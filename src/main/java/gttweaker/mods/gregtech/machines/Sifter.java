package gttweaker.mods.gregtech.machines;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.Arrays;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provides access to the Sifter recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Sifter")
@ModOnly(MOD_ID)
public class Sifter {
    /**
     * Adds a Sifter recipe.
     *
     * @param outputs       1-9 outputs
     * @param input         primary input
     * @param outChances    chances of 1-9 output
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] outputs, IItemStack input, int[] outChances, int durationTicks, int euPerTick) {
        if (outputs.length < 1) {
            MineTweakerAPI.logError("Sifter must have at least 1 output");
        } else if(outputs.length!=outChances.length){
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        }else {
            MineTweakerAPI.apply(new AddRecipeAction(outputs, input, outChances, durationTicks, euPerTick));
        }
    }

// ######################
// ### Action classes ###
// ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack[] output;
        private final IItemStack input;
        private final int[] chances;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack[] output, IItemStack input, int[] outChances, int duration, int euPerTick) {

            this.output = output;
            this.input = input;
            this.chances = outChances;
            this.duration = duration;
            this.euPerTick = euPerTick;


        }

        @Override
        public void apply() {
            RA.addSifterRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStacks(output),
                    chances,
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Sifter recipe for " + input ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            hash = 76 * hash + Arrays.deepHashCode(this.output);
            hash = 76 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 76 * hash + this.duration;
            hash = 76 * hash + this.euPerTick;

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
            if (!Arrays.deepEquals(this.output, other.output)) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
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