package gttweaker.mods.gregtech;

import java.util.HashMap;

import gregtech.api.util.GT_Recipe;

public class GTRecipeMap {

    public static HashMap<String, GT_Recipe.GT_Recipe_Map> recipeMaps = new HashMap<>();

    public static GT_Recipe.GT_Recipe_Map getRecipeMap(String map) {
        if (!recipeMaps.containsKey(map)) {
            recipeMaps.put(
                map,
                GT_Recipe.GT_Recipe_Map.sMappings.stream()
                    .filter(m -> m.mUnlocalizedName.equals(map))
                    .findAny()
                    .orElse(null));
        }
        return recipeMaps.get(map);
    }
}
