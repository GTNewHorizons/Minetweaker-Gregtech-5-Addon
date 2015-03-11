package gttweaker;

import cpw.mods.fml.common.Loader;

import java.util.ArrayList;

public class TweakerPlugin {
	private static ArrayList<String> isLoaded = new ArrayList();

	public static void register(String mod, Class clazz) {
		if (Loader.isModLoaded(mod)) {
			try {
				clazz.newInstance();
				isLoaded.add(mod);
			} catch (Exception e) {
				isLoaded.remove(mod);
			}
		}
	}

	public static boolean isLoaded(String string) {
		return isLoaded.contains(string);
	}
}
