package gttweaker.util.exception;

import minetweaker.api.item.IIngredient;

/**
 * Created by Techlone
 */
public class EmptyIngredientException extends RuntimeException {
    public IIngredient ingredient;

    public EmptyIngredientException(IIngredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Ingredient '" + ingredient + "' is empty. Can't add recipe without items.";
    }
}
