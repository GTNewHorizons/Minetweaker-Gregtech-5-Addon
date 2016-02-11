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
 * Provides access to the Separator recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Separator")
@ModOnly(MOD_ID)
public class Separator{
    /**
     * Adds a Separator recipe.
     *
     * @param input         recipe input
     * @param output        Item output Slot 1-3
     * @param outChances    Item output chances
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, int[] outChances, int durationTicks, int euPerTick) {
        if (output.length < 1) {
            MineTweakerAPI.logError("Seperator must have at least 1 output");
        } else if(output.length!=outChances.length){
            MineTweakerAPI.logError("Number of Outputs does not equal number of Chances");
        }else {
            MineTweakerAPI.apply(new AddRecipeAction(output, input, outChances, durationTicks, euPerTick));
        }
    }
// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack[] output;
        private final IIngredient input;
        private final int[] chances;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack[] output, IIngredient input, int[] outChances, int duration, int euPerTick) {

            this.output = output;
            this.input = input;
            this.chances = outChances;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }
        @Override
        public void apply() {
            RA.addElectromagneticSeparatorRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(output[0]),
                    output.length > 1 ? MineTweakerMC.getItemStack(output[1]) : null,
                    output.length > 2 ? MineTweakerMC.getItemStack(output[2]) : null,
                    chances,
                    duration,
                    euPerTick);
        }
        @Override
        public String describe() {return "Adding Separator recipe for " + input;}

        @Override
        public Object getOverrideKey() {return null;}

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 39 * hash + (this.output != null ? this.output.hashCode() : 0);
            hash = 39 * hash + (this.input != null ? this.input.hashCode() : 0);
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
