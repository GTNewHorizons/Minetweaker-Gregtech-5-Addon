package gttweaker.mods.gregtech;

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
 * Provides access to the Saw recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.SawLiq")
@ModOnly(MOD_ID)
public class SawLiq {
    /**
     * Adds a Saw recipe.
     *
     * @param output1      recipe output1
     * @param output2      recipe output2
     * @param input        primary input
     * @param Lubricant   primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IItemStack input, ILiquidStack Lubricant, int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(new AddRecipeAction(output1, output2, input, Lubricant, durationTicks, euPerTick));
    }

// ######################
// ### Action classes ###
// ######################
    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack output1;
        private final IItemStack output2;
        private final IItemStack input;
        private final ILiquidStack Lubricant;
        private final int duration;
        private final int euPerTick;

        public AddRecipeAction(IItemStack output1, IItemStack output2, IItemStack input, ILiquidStack Lubricant, int duration, int euPerTick1) {

            this.output1 = output1;
            this.output2 = output2;
            this.input = input;
            this.Lubricant = Lubricant;
            this.duration = duration;
            this.euPerTick = euPerTick1;
        }

        @Override
        public void apply() {
            RA.addCutterRecipe(
                    MineTweakerMC.getItemStack(input),
                    MineTweakerMC.getLiquidStack(Lubricant),
                    MineTweakerMC.getItemStack(output1),
                    MineTweakerMC.getItemStack(output2),
                    duration,
                    euPerTick);
        }

        @Override
        public String describe() {
            return "Adding Cutting Saw recipe for " + input;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 99 * hash + (this.output1 != null ? this.output1.hashCode() : 0);
            hash = 99 * hash + (this.output2 != null ? this.output2.hashCode() : 0);
            hash = 99 * hash + (this.input != null ? this.input.hashCode() : 0);
            hash = 99 * hash + (this.Lubricant != null ? this.Lubricant.hashCode() : 0);
            hash = 99 * hash + this.duration;
            hash = 99 * hash + this.euPerTick;
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
            if (this.input != other.input && (this.input == null || !this.input.equals(other.input))) {
                return false;
            }
            if (this.Lubricant != other.Lubricant && (this.Lubricant == null || !this.Lubricant.equals(other.Lubricant))) {
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
