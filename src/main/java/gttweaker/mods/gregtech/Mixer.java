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
* Provides access to the Mixer recipes.
*
* @author Stan Hebben
* @author DreamMasterXXL
*/
@ZenClass("mods.gregtech.Mixer")
@ModOnly(MOD_ID)
public class Mixer{
/**
* Adds a Mixer recipe.
*
* @param input1 primary input
* @param input2 secondary input
* @param input3 third input
* @param input4 fourth input
* @param fluidInput1 primary fluidInput
* @param fluidOutput1 primary fluidInput
* @param output recipe output
* @param durationTicks reaction time, in ticks
* @param euPerTick eu consumption per tick
*/
@ZenMethod
public static void addRecipe(IItemStack input1, IItemStack input2, IItemStack input3, IItemStack input4, ILiquidStack fluidInput1, ILiquidStack fluidOutput1, IItemStack output, int durationTicks, int euPerTick) {
MineTweakerAPI.apply(new AddRecipeAction(input1, input2, input3, input4, fluidInput1, fluidOutput1, output, durationTicks, euPerTick, euPerTick));
}
// ######################
// ### Action classes ###
// ######################
private static class AddRecipeAction extends OneWayAction {
private final IItemStack input1;
private final IItemStack input2;
private final IItemStack input3;
private final IItemStack input4;
private final ILiquidStack fluidInput1;
private final ILiquidStack fluidOutput1;
private final IItemStack output;
private final int duration;
private final int euPerTick;
public AddRecipeAction(IItemStack input1, IItemStack input2, IItemStack input3, IItemStack input4, ILiquidStack fluidInput1, ILiquidStack fluidOutput1, IItemStack output, int duration, int euPerTick, int euPerTick1) {
this.input1 = input1;
this.input2 = input2;
this.input3 = input3;
this.input4 = input4;
this.fluidInput1 = fluidInput1;
this.fluidOutput1 = fluidOutput1;
this.output = output;
this.duration = duration;
this.euPerTick = euPerTick1;
}
@Override
public void apply() {
GregTech_API.sRecipeAdder.addMixerRecipe(
MineTweakerMC.getItemStack(input1),
MineTweakerMC.getItemStack(input2),
MineTweakerMC.getItemStack(input3),
MineTweakerMC.getItemStack(input4),
MineTweakerMC.getLiquidStack(fluidInput1),
MineTweakerMC.getLiquidStack(fluidOutput1),
MineTweakerMC.getItemStack(output),
duration,
euPerTick);
}
@Override
public String describe() {return "Adding mixer recipe for " + output;}
@Override
public Object getOverrideKey() {return null;}
@Override
public int hashCode() {
int hash = 6;
hash = 67 * hash + (this.input1 != null ? this.input1.hashCode() : 0);
hash = 67 * hash + (this.input2 != null ? this.input2.hashCode() : 0);
hash = 67 * hash + (this.input3 != null ? this.input3.hashCode() : 0);
hash = 67 * hash + (this.input4 != null ? this.input4.hashCode() : 0);
hash = 67 * hash + (this.fluidInput1 != null ? this.fluidInput1.hashCode() : 0);
hash = 67 * hash + (this.fluidOutput1 != null ? this.fluidOutput1.hashCode() : 0);
hash = 67 * hash + (this.output != null ? this.output.hashCode() : 0);
hash = 67 * hash + this.duration;
hash = 67 * hash + this.euPerTick;
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
if (this.input2 != other.input2 && (this.input2 == null || !this.input2.equals(other.input2))) {
return false;
}
if (this.input3 != other.input3 && (this.input3 == null || !this.input3.equals(other.input3))) {
return false;
}
if (this.input4 != other.input4 && (this.input4 == null || !this.input4.equals(other.input4))) {
return false;
}
if (this.fluidInput1 != other.fluidInput1 && (this.fluidInput1 == null || !this.fluidInput1.equals(other.fluidInput1))) {
return false;
}
if (this.fluidOutput1 != other.fluidOutput1 && (this.fluidOutput1 == null || !this.fluidOutput1.equals(other.fluidOutput1))) {
return false;
}
if (this.output != other.output && (this.output == null || !this.output.equals(other.output))) {
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
