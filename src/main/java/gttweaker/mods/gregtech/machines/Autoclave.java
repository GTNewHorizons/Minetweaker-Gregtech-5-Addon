package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GTValues.RA;
import static gregtech.api.recipe.RecipeMaps.autoclaveRecipes;
import static gregtech.api.util.GTRecipeConstants.LOW_GRAVITY;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.mods.AddMultipleRecipeAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the Autoclave recipes.
 *
 * @author DreamMasterXXL
 */
@ZenClass("mods.gregtech.Autoclave")
@ModOnly("gregtech")
public class Autoclave {

    /**
     * Adds an Autoclave recipe.
     *
     * @param output        primary output
     * @param input         primary input
     * @param fluidInput    primary fluidInput
     * @param chance        chance
     * @param durationTicks assembling duration, in ticks
     * @param euPerTick     eu consumption per tick
     * @param lowGravity    the low gravity requirement
     */
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chance,
        int durationTicks, int euPerTick, boolean lowGravity) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Autoclave recipe for " + output,
                input,
                fluidInput,
                output,
                chance,
                durationTicks,
                euPerTick,
                lowGravity) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack input = i.nextItem();
                    FluidStack fluidInput = i.nextFluid();
                    ItemStack output = i.nextItem();
                    int chance = i.nextInt();
                    int duration = i.nextInt();
                    int eut = i.nextInt();
                    boolean requiresLowGravity = i.nextBool();

                    RA.stdBuilder()
                        .itemInputs(input)
                        .itemOutputs(output)
                        .outputChances(chance)
                        .fluidInputs(fluidInput)
                        .duration(duration)
                        .eut(eut)
                        .metadata(LOW_GRAVITY, requiresLowGravity)
                        .addTo(autoclaveRecipes);
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chance,
        int durationTicks, int euPerTick) {
        addRecipe(output, input, fluidInput, chance, durationTicks, euPerTick, false);
    }
}
