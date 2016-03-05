package gttweaker.mods.gregtech.machines;

import gttweaker.mods.gregtech.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

/**
 * Provider access to the Canner recipes.
 * 
 * @author Stan Hebben
 */
@ZenClass("mods.gregtech.Canner")
@ModOnly(MOD_ID)
public class Canner {
	/**
	 * Adds a canner recipe with a single output.
	 * 
	 * @param output 		crafting output
	 * @param input1 		primary input
	 * @param input2 		secondary input (optional
	 * @param durationTicks crafting duration, in ticks
	 * @param euPerTick 	eu consumption per tick
	 */
	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
		MineTweakerAPI.apply(new AddRecipeAction(output, null, input1, input2, durationTicks, euPerTick));
	}

	/**
	 * Adds a canner recipe with multiple outputs.
	 * 
	 * @param output 		array with 1 or 2 outputs
	 * @param input1  		primary inputs
	 * @param input2  		secondary inputs
	 * @param durationTicks crafting duration, in ticks
 	 * @param euPerTick 	eu consumption per tick
	 */
	@ZenMethod
	public static void addRecipe(IItemStack[] output, IIngredient input1, IIngredient input2, int durationTicks, int euPerTick) {
		if (output.length == 0) {
			MineTweakerAPI.logError("canner requires at least 1 output");
		} else {
			MineTweakerAPI.apply(new AddRecipeAction(output[0], output.length > 1 ? output[1] : null, input1, input2, durationTicks, euPerTick));
		}
	}
	
	// ######################
	// ### Action classes ###
	// ######################
	
	private static class AddRecipeAction extends AddMultipleRecipeAction {
		public AddRecipeAction(IItemStack output1, IItemStack output2, IIngredient input1, IIngredient input2, int duration, int euPerTick) {
			super("Adding canner recipe for " + output1, input1, input2, output1, output2, duration, euPerTick);
		}

		@Override
		protected void applySingleRecipe(Object[] args) {
			RA.addCannerRecipe(
					(ItemStack) args[0],
					(ItemStack) args[1],
					(ItemStack) args[2],
					(ItemStack) args[3],
					(Integer) args[4],
					(Integer) args[5]);
		}
	}
}
