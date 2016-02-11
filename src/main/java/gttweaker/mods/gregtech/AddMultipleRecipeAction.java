package gttweaker.mods.gregtech;

import gttweaker.util.exception.EmptyOreDictException;
import minetweaker.OneWayAction;
import minetweaker.api.item.IIngredient;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.api.oredict.IOreDictEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Techlone
 */
public abstract class AddMultipleRecipeAction extends OneWayAction {
    private static void extendBySingle(Object singleArg, List<List<Object>> recipesData) {
        for (List<Object> recipeData : recipesData) {
            if (singleArg instanceof IIngredient) {
                recipeData.add(MineTweakerMC.getItemStack((IIngredient) singleArg));
            } else {
                recipeData.add(singleArg);
            }
        }
    }

    private static void extendByPlural(IOreDictEntry oreDictArg, List<List<Object>> recipesData) {
        List<List<Object>> oldData = fullCopy(recipesData);
        recipesData.clear();
        for (Object singleArg : oreDictArg.getItems()) {
            List<List<Object>> tmp = fullCopy(oldData);
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

    public AddMultipleRecipeAction(String description, Object... recipeArgs) throws EmptyOreDictException {
        this(recipeArgs);
        this.description = description;
    }

    protected AddMultipleRecipeAction(Object... recipeArgs) throws EmptyOreDictException {
        recipesData = new ArrayList<List<Object>>(recipeArgs.length);
        recipesData.add(new ArrayList<Object>());

        for (Object recipeArg : recipeArgs) {
            if (recipeArg instanceof IOreDictEntry) {
                if (((IOreDictEntry) recipeArg).getItems().size() == 0) {
                    throw new EmptyOreDictException((IOreDictEntry) recipeArg);
                }
                extendByPlural((IOreDictEntry) recipeArg, recipesData);
            } else {
                extendBySingle(recipeArg, recipesData);
            }
        }
    }

    protected abstract void applySingleRecipe(Object... recipeArgs);

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
