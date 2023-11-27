package gttweaker.mods.gregtech;

import java.util.HashMap;

import gregtech.api.recipe.RecipeMap;

public class GTRecipeMap {

    public static HashMap<String, RecipeMap<?>> recipeMaps = new HashMap<>();

    public static RecipeMap<?> getRecipeMap(String map) {
        if (!recipeMaps.containsKey(map)) {
            recipeMaps.put(
                map,
                RecipeMap.ALL_RECIPE_MAPS.values()
                    .stream()
                    .filter(m -> m.unlocalizedName.equals(map))
                    .findAny()
                    .orElse(null));
        }
        return recipeMaps.get(map);
    }
}
