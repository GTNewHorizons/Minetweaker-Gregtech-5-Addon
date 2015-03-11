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
 * Provides access to the Distillery recipes.
 *
 * @author Stan Hebben
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Distillery")
@ModOnly(MOD_ID)
public class Distillery {
    /**
     * Adds a Distillery recipe.
     *
     * @param Circuit primary input
     * @param fluidInput1 primary fluidInput
     * @param fluidOutput1  first output
     * @param durationTicks reaction time, in ticks
     * @param euPerTick eu consumption per tick
     * @param Hidden hidden
     *
     */
    @ZenMethod
    public static void addRecipe(IItemStack Circuit, ILiquidStack fluidInput1, ILiquidStack fluidOutput1, int durationTicks, int euPerTick, boolean Hidden) {
        MineTweakerAPI.apply(new AddRecipeAction(Circuit, fluidInput1, fluidOutput1, durationTicks, euPerTick, Hidden));
    }

    // ######################
    // ### Action classes ###
    // ######################

    private static class AddRecipeAction extends OneWayAction {

        private final IItemStack Circuit;
        private final ILiquidStack fluidInput1;
        private final ILiquidStack fluidOutput1;
        private final int duration;
        private final int euPerTick;
        private final boolean Hidden;

        public AddRecipeAction(IItemStack Circuit, ILiquidStack fluidInput1, ILiquidStack fluidOutput1, int duration, int euPerTick, boolean Hidden) {

            this.Circuit = Circuit;
            this.fluidInput1 = fluidInput1;
            this.fluidOutput1 = fluidOutput1;
            this.duration = duration;
            this.euPerTick = euPerTick;
            this.Hidden = Hidden;
        }

        @Override
        public void apply() {
            GregTech_API.sRecipeAdder.addDistilleryRecipe(
                    MineTweakerMC.getItemStack(Circuit),
                    MineTweakerMC.getLiquidStack(fluidInput1),
                    MineTweakerMC.getLiquidStack(fluidOutput1),
                    duration,
                    euPerTick,
                    Hidden);
        }

        @Override
        public String describe() {
            return "Adding chemical bath recipe for " + fluidOutput1 ;
        }

        @Override
        public Object getOverrideKey() {
            return null;
        }

        @Override
        public int hashCode() {
            int hash = 9;
            hash = 8 * hash + (this.Circuit != null ? this.Circuit.hashCode() : 0);
            hash = 8 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
            hash = 8 * hash + (this.fluidOutput1 != null ? this.fluidOutput1.hashCode() : 0);
            hash = 8 * hash + this.duration;
            hash = 8 * hash + this.euPerTick;

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
            if (this.Circuit != other.Circuit && (this.Circuit == null || !this.Circuit.equals(other.Circuit))) {

            }
            if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
                return false;
            }
            if (this.fluidOutput1 != other.fluidOutput1 && (this.fluidOutput1 == null || !this.fluidOutput1.equals(other.fluidOutput1))) {
                return false;
            }
             if (this.duration != other.duration) {
                return false;
            }
            if (this.euPerTick != other.euPerTick) {
                return false;
            }
            if (this.Hidden != other.Hidden) {
                return false;
            }
            return true;
        }
    }
}