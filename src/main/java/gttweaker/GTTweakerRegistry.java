package gttweaker;

import java.util.List;

import gttweaker.mods.gregtech.expand.*;

public class GTTweakerRegistry {

    public static void getClasses(List<Class> classes) {
        classes.add(OreDictEntryExpansion.class);
    }
}
