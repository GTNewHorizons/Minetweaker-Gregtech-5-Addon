package gttweaker.mods;

import java.util.*;

import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gttweaker.util.exception.AnyIngredientException;
import gttweaker.util.exception.EmptyIngredientException;
import gttweaker.util.exception.OutOfStackSizeException;

/**
 * Created by Techlone
 */
public abstract class AddMultipleRecipeAction extends OneWayAction {

    private static List<List<Object>> createNewMatrix(int initCount) {
        return new ArrayList<List<Object>>(initCount) {

            {
                this.add(new ArrayList<Object>());
            }
        };
    }

    private static void extendBySingle(Object singleArg, List<List<Object>> recipesData) {
        for (List<Object> recipeData : recipesData) {
            recipeData.add(singleArg);
        }
    }

    private static void extendByMultiple(List args, List<List<Object>> recipesData) {
        List<List<Object>> originData = fullCopy(recipesData);
        recipesData.clear();
        for (Object singleArg : args) {
            List<List<Object>> tmp = fullCopy(originData);
            extendBySingle(singleArg, tmp);
            recipesData.addAll(tmp);
        }
    }

    private static List<List<Object>> fullCopy(List<List<Object>> recipesData) {
        List<List<Object>> result = new ArrayList<List<Object>>(recipesData.size());
        for (List<Object> recipeData : recipesData) {
            result.add(new ArrayList<Object>(recipeData));
        }
        return result;
    }

    private String description;
    private List<List<Object>> recipesData;

    private void addArgument(Object recipeArg) {
        if (recipeArg instanceof ILiquidStack) {
            extendBySingle(MineTweakerMC.getLiquidStack((ILiquidStack) recipeArg), recipesData);
        } else if (recipeArg instanceof ILiquidStack[]) {
            extendBySingle(MineTweakerMC.getLiquidStacks((ILiquidStack[]) recipeArg), recipesData);
        } else if (recipeArg instanceof IItemStack) {
            extendBySingle(MineTweakerMC.getItemStack((IItemStack) recipeArg), recipesData);
        } else if (recipeArg instanceof IItemStack[]) {
            extendBySingle(MineTweakerMC.getItemStacks((IItemStack[]) recipeArg), recipesData);
        } else if (recipeArg instanceof IIngredient) {
            extendByMultiple(getItemStacks((IIngredient) recipeArg), recipesData);
        } else if (recipeArg instanceof IIngredient[]) {
            extendByMultiple(getItemStackArrays((IIngredient[]) recipeArg), recipesData);
        } else {
            extendBySingle(recipeArg, recipesData);
        }
    }

    private List<ItemStack[]> getItemStackArrays(IIngredient[] recipeArg) {
        List<List<Object>> tempArgs = createNewMatrix(recipeArg.length);
        for (IIngredient ingredient : recipeArg) {
            extendByMultiple(getItemStacks(ingredient), tempArgs);
        }

        List<ItemStack[]> result = new ArrayList<ItemStack[]>(tempArgs.size());
        for (List<Object> tempArg : tempArgs) {
            ItemStack[] arg = new ItemStack[tempArg.size()];
            for (int i = 0; i < arg.length; i++) arg[i] = (ItemStack) tempArg.get(i);
            result.add(arg);
        }

        return result;
    }

    private List<ItemStack> getItemStacks(IIngredient ingredientArg) {
        List<IItemStack> items = ingredientArg.getItems();
        if (items == null) {
            throw new AnyIngredientException();
        }
        if (items.size() == 0) {
            throw new EmptyIngredientException(ingredientArg);
        }
        List<ItemStack> itemStackList = Arrays.asList(MineTweakerMC.getItemStacks(items));
        int amount = ingredientArg.getAmount();
        if (amount < 0) {
            throw new RuntimeException("Negative amount for ingredient " + ingredientArg);
        }
        for (ItemStack stack : itemStackList) {
            if (amount > stack.getMaxStackSize()) {
                throw new OutOfStackSizeException(ingredientArg, amount);
            }
            stack.stackSize = amount;
        }
        return itemStackList;
    }

    protected AddMultipleRecipeAction(String description, Object... recipeArgs) {
        this.description = description;
        recipesData = createNewMatrix(recipeArgs.length);

        try {
            for (Object recipeArg : recipeArgs) {
                addArgument(recipeArg);
            }
        } catch (Exception e) {
            MineTweakerAPI.logError(e.toString());
        }
    }

    protected abstract void applySingleRecipe(ArgIterator i);

    protected static class ArgIterator {

        private Iterator<Object> iterator;

        public ArgIterator(List<Object> args) {
            this.iterator = args.iterator();
        }

        public ItemStack nextItem() {
            return (ItemStack) iterator.next();
        }

        public ItemStack[] nextItemArr() {
            return (ItemStack[]) iterator.next();
        }

        public FluidStack nextFluid() {
            return (FluidStack) iterator.next();
        }

        public FluidStack[] nextFluidArr() {
            return (FluidStack[]) iterator.next();
        }

        public int nextInt() {
            return (Integer) iterator.next();
        }

        public int[] nextIntArr() {
            return (int[]) iterator.next();
        }

        public boolean nextBool() {
            return (Boolean) iterator.next();
        }
    }

    @Override
    public void apply() {
        for (List<Object> recipeData : recipesData) {
            applySingleRecipe(new ArgIterator(recipeData));
        }
    }

    @Override
    public String describe() {
        return this.description;
    }

    @Override
    public Object getOverrideKey() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddMultipleRecipeAction that = (AddMultipleRecipeAction) o;
        return recipesData != null ? recipesData.equals(that.recipesData) : that.recipesData == null;
    }

    @Override
    public int hashCode() {
        return recipesData != null ? recipesData.hashCode() : 0;
    }
}
