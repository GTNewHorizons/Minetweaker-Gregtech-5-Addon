package gttweaker.mods.gregtech;

import java.lang.reflect.Field;
import java.util.HashMap;

import gregtech.api.util.GT_Recipe;

public class GTRecipeMap {

    public static HashMap<String, GT_Recipe.GT_Recipe_Map> recipeMaps = new HashMap<>();

    public static GT_Recipe.GT_Recipe_Map getRecipeMap(String map) {
        if (!recipeMaps.containsKey(map)) {
            try {
                Field f = GT_Recipe.GT_Recipe_Map.class.getDeclaredField(map);
                recipeMaps.put(map, (GT_Recipe.GT_Recipe_Map) f.get(null));
            } catch (Exception ex) {
                recipeMaps.put(map, null);
            }
        }
        return recipeMaps.get(map);
    }
}
