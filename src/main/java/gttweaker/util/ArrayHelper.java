package gttweaker.util;

/**
 * @author Techlone
 */
public class ArrayHelper {

    public static <T> T itemOrNull(T[] array, int i) {
        return array.length > i ? array[i] : null;
    }
}
