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
 * Provides access to the Unpacker recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Unpacker")
@ModOnly(MOD_ID)
public class Unpacker{
    /**
     * Adds a Unpacker recipe.
     *
     * @param output1       recipe output Slot 1
     * @param output2       recipe output Slot 2
     * @param input         recipe Input Slot
     * @param durationTicks duration time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IIngredient output1, IIngredient output2, IIngredient input, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output1, output2, input, durationTicks, euPerTick));
    }
// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IIngredient output1;
        private final IIngredient output2;
        private final IIngredient input;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IIngredient output1, IIngredient output2, IIngredient input, int duration, int euPerTick) {

            this.output1 = output1;
            this.output2 = output2;
            this.input = input;
            this.duration = duration;
            this.euPerTick = euPerTick;
        }
        @Override
        public void apply() {
            RA.addUnboxingRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getItemStack(output1),
                    MineTweakerMC.getItemStack(output2),
                    duration,
                    euPerTick);
        }
        @Override
        public String describe() {return "Adding Unpacker recipe for " + input;}

        @Override
        public Object getOverrideKey() {return null;}

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 39 * hash + (this.output1 != null ? this.output1.hashCode() : 0);
            hash = 39 * hash + (this.output2 != null ? this.output2.hashCode() : 0);
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
            if (this.output1 != other.output1 && (this.output1 == null || !this.output1.equals(other.output1))) {
                return false;
            }
            if (this.output2 != other.output2 && (this.output2 == null || !this.output2.equals(other.output2))) {
                return false;
            }
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
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
