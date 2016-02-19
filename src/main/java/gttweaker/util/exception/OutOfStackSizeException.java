package gttweaker.util.exception;

import minetweaker.api.item.IIngredient;

/**
 * Created by Techlone
 */
public class OutOfStackSizeException extends RuntimeException {
    private IIngredient ingredient;
    private int amount;

    public OutOfStackSizeException(IIngredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Impossible add ingredient '" + ingredient + "' with amount " + amount;
    }
}
