package gttweaker.mods.gregtech.machines;

import static gregtech.api.enums.GT_Values.MOD_ID;
import static gregtech.api.enums.GT_Values.RA;
import static gttweaker.util.ArrayHelper.itemOrNull;

import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import gttweaker.GTTweaker;
import gttweaker.mods.AddMultipleRecipeAction;

/**
 * Provides access to the Saw recipes.
 *
 * @author DreamMasterXXL
 * @author bculkin2442
 */
@ZenClass("mods.gregtech.CuttingSaw")
@ModOnly(MOD_ID)
public class CuttingSaw {

    /**
     * Adds a Cutting Saw recipe.
     *
     * @param output1       recipe output1
     * @param output2       recipe output2
     * @param input         primary input
     * @param lubricant     primary fluidInput
     * @param durationTicks reaction time, in ticks
     * @param euPerTick     eu consumption per tick
     */
    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, ILiquidStack lubricant,
        int durationTicks, int euPerTick) {
        if (lubricant == null) {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Cutting Saw recipe for " + input,
                    input,
                    output1,
                    output2,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        ItemStack a2 = i.nextItem();
                        ItemStack a3 = i.nextItem();
                        int a4 = i.nextInt();
                        int a5 = i.nextInt();
                        RA.addCutterRecipe(a1, a2, a3, a4, a5);

                        GTTweaker.info(
                            "CuttingRecipes.recipeWithClassicFluids(new ItemStack[]{" + GTTweaker.convertStack(a1)
                                + "}, new ItemStack[]{"
                                + GTTweaker.convertStack(a2)
                                + ""
                                + (a3 != null ? (", " + GTTweaker.convertStack(a3)) : "")
                                + "}, "
                                + a4
                                + ", "
                                + a5
                                + ", false);");

                    }
                });
        } else {
            MineTweakerAPI.apply(
                new AddMultipleRecipeAction(
                    "Adding Cutting Saw recipe for " + input,
                    input,
                    lubricant,
                    output1,
                    output2,
                    durationTicks,
                    euPerTick) {

                    @Override
                    protected void applySingleRecipe(ArgIterator i) {
                        ItemStack a1 = i.nextItem();
                        FluidStack a2 = i.nextFluid();
                        ItemStack a3 = i.nextItem();
                        ItemStack a4 = i.nextItem();
                        int a5 = i.nextInt();
                        int a6 = i.nextInt();

                        RA.addCutterRecipe(a1, a2, a3, a4, a5, a6);
                        GTTweaker.logGTRecipe(
                            new ItemStack[] { a1 },
                            new ItemStack[] { a3, a4 },
                            new FluidStack[] { a2 },
                            null,
                            a5,
                            a6,
                            "sCutterRecipes");
                    }
                });
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack[] output, IIngredient input, ILiquidStack lubricant, int durationTicks,
        int euPerTick) {
        if (output.length == 0) {
            MineTweakerAPI.logError("canner requires at least 1 output");
        } else {
            addRecipe(output[0], itemOrNull(output, 1), input, lubricant, durationTicks, euPerTick);
        }
    }

    @ZenMethod
    public static void addRecipe(IItemStack output1, IItemStack output2, IIngredient input, IItemStack circuit,
        int durationTicks, int euPerTick) {
        MineTweakerAPI.apply(
            new AddMultipleRecipeAction(
                "Adding Cutting Saw recipe for " + input,
                input,
                circuit,
                output1,
                output2,
                durationTicks,
                euPerTick) {

                @Override
                protected void applySingleRecipe(ArgIterator i) {
                    ItemStack a1 = i.nextItem();
                    ItemStack a2 = i.nextItem();
                    ItemStack a3 = i.nextItem();
                    ItemStack a4 = i.nextItem();
                    int a5 = i.nextInt();
                    int a6 = i.nextInt();

                    RA.addCutterRecipe(a1, a2, a3, a4, a5, a6);

                    GTTweaker.info(
                        "CuttingRecipes.recipeWithClassicFluids(new ItemStack[]{" + GTTweaker.convertStack(a1)
                            + ""
                            + (a2 != null ? (", " + GTTweaker.convertStack(a2)) : "")
                            + "}, new ItemStack[]{"
                            + GTTweaker.convertStack(a3)
                            + ""
                            + (a4 != null ? (", " + GTTweaker.convertStack(a4)) : "")
                            + "}, "
                            + a4
                            + ", "
                            + a5
                            + ", false);");
                }
            });
    }
}
