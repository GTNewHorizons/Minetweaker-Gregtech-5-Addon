package gttweaker.mods.gregtech;

import gttweaker.util.exception.AnyIngredientException;
import gttweaker.util.exception.EmptyIngredientException;
import gttweaker.util.exception.OutOfStackSizeException;
import minetweaker.MineTweakerAPI;
import minetweaker.OneWayAction;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;

import java.util.*;

/**
 * Created by Techlone
 */
public abstract class AddMultipleRecipeAction extends OneWayAction {
    private static void extendBySingle(Object singleArg, List<List<Object>> recipesData) {
        for (List<Object> recipeData : recipesData) {
            recipeData.add(singleArg);
        }
    }

    private static void extendByPlural(List args, List<List<Object>> recipesData) {
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

    protected String description;
    private List<List<Object>> recipesData;

    protected AddMultipleRecipeAction(String description, Object... recipeArgs) {
        this.description = description;

        recipesData = new ArrayList<List<Object>>(recipeArgs.length);
        recipesData.add(new ArrayList<Object>());

        try {
            for (Object recipeArg : recipeArgs) {
                addArgument(recipeArg);
            }
        } catch (Exception e) {
            MineTweakerAPI.logError(e.toString());
        }
    }

    protected void addArgument(Object recipeArg) {
        if (recipeArg == null) {
            throw new NullPointerException("Null argument at '" + description + "'");
        }
        if (recipeArg instanceof ILiquidStack) {
            extendBySingle(MineTweakerMC.getLiquidStack((ILiquidStack) recipeArg), recipesData);
        } else if (recipeArg instanceof ILiquidStack[]) {
            extendBySingle(MineTweakerMC.getLiquidStacks((ILiquidStack[]) recipeArg), recipesData);
        } else if (recipeArg instanceof IItemStack[]) {
            extendBySingle(MineTweakerMC.getItemStacks((IItemStack[]) recipeArg), recipesData);
        } else if (recipeArg instanceof IIngredient) {
            IIngredient ingredientArg = (IIngredient) recipeArg;
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
            extendByPlural(itemStackList, recipesData);
        } else {
            extendBySingle(recipeArg, recipesData);
        }
    }

    protected abstract void applySingleRecipe(Object[] recipeArgs);

    @Override
    public void apply() {
        for (List<Object> recipeData : recipesData) {
            applySingleRecipe(recipeData.toArray());
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
