package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.GTTweaker;
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
@ModOnly(MOD_ID)
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
                    ItemStack a1 = i.nextItem();
                    FluidStack a2 = i.nextFluid();
                    ItemStack a3 = i.nextItem();
                    int a4 = i.nextInt();
                    int a5 = i.nextInt();
                    int a6 = i.nextInt();
                    boolean a7 = i.nextBool();
                    RA.addAutoclaveRecipe(a1, a2, a3, a4, a5, a6, a7);
                    GTTweaker.logGTRecipe(
                        new ItemStack[] { a1 },
                        new ItemStack[] { a3 },
                        new int[] { a4 },
                        new FluidStack[] { a2 },
                        null,
                        a5,
                        a6,
                        "sAutoclaveRecipes");
                }
            });
    }

    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, ILiquidStack fluidInput, int chance,
        int durationTicks, int euPerTick) {
        addRecipe(output, input, fluidInput, chance, durationTicks, euPerTick, false);
    }
}
