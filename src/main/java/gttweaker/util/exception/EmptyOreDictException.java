package gttweaker.util.exception;

import minetweaker.api.oredict.IOreDictEntry;

/**
 * Created by Techlone
 */
public class EmptyOreDictException extends Exception {
    public IOreDictEntry emptyOreDict;

    public EmptyOreDictException(IOreDictEntry oreDict) {
        emptyOreDict = oreDict;
    }

    @Override
    public String toString() {
        return "Ore dictionary " + emptyOreDict + " is empty. Can not to add recipe without items.";
    }
}
