package gttweaker.mods.gregtech;

import java.util.Arrays;
import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.enums.GT_Values;
import gregtech.api.util.GT_RecipeBuilder;
import gttweaker.GTTweaker;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenClass("mods.gregtech.RA2")
@ModOnly("gregtech")
public class RA2Builder {

    private GT_RecipeBuilder recipeBuilder;

    public RA2Builder() {}

    public RA2Builder(GT_RecipeBuilder recipeBuilder) {
        this.recipeBuilder = recipeBuilder;
    }

    @ZenMethod
    public static RA2Builder builder() {
        return new RA2Builder(GT_Values.RA.stdBuilder());
    }

    @ZenMethod
    public RA2Builder noItemInputs() {
        recipeBuilder.noItemInputs();
        return this;
    }

    @ZenMethod
    public RA2Builder itemInputs(IIngredient[] inputs) {
        if (inputs.length == 0) recipeBuilder.noItemInputs();
        else recipeBuilder.itemInputs(
            Arrays.stream(inputs)
                .map(GTTweaker::getItemStackOrNull)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder noItemOutputs() {
        recipeBuilder.noItemOutputs();
        return this;
    }

    @ZenMethod
    public RA2Builder itemOutputs(IIngredient[] outputs) {
        if (outputs.length == 0) recipeBuilder.noItemOutputs();
        else recipeBuilder.itemOutputs(
            Arrays.stream(outputs)
                .map(GTTweaker::getItemStackOrNull)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder noFluidInputs() {
        recipeBuilder.noFluidInputs();
        return this;
    }

    @ZenMethod
    public RA2Builder fluidInputs(IIngredient[] inputs) {
        if (inputs.length == 0) recipeBuilder.noFluidInputs();
        else recipeBuilder.fluidInputs(
            Arrays.stream(inputs)
                .map(GTTweaker::getFluidStackOrNull)
                .filter(Objects::nonNull)
                .toArray(FluidStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder noFluidOutputs() {
        recipeBuilder.noFluidOutputs();
        return this;
    }

    @ZenMethod
    public RA2Builder fluidOutputs(IIngredient[] outputs) {
        if (outputs.length == 0) recipeBuilder.noFluidOutputs();
        else recipeBuilder.fluidOutputs(
            Arrays.stream(outputs)
                .map(GTTweaker::getFluidStackOrNull)
                .filter(Objects::nonNull)
                .toArray(FluidStack[]::new));
        return this;
    }

    @ZenMethod
    public RA2Builder duration(int duration) {
        recipeBuilder.duration(duration);
        return this;
    }

    @ZenMethod
    public RA2Builder eut(int eut) {
        recipeBuilder.eut(eut);
        return this;
    }

    @ZenMethod
    public void addTo(String recipeMap) {
        recipeBuilder.addTo(GTRecipeMap.getRecipeMap(recipeMap));
    }
}
