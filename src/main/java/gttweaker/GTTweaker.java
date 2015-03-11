package gttweaker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import gttweaker.mods.gregtech.GregTech5;

@Mod(modid = "GTTweaker", name = "GTTweaker", dependencies = "required-after:MineTweaker3", version = "0.2.2")
public class GTTweaker {
	@EventHandler
	public void init(FMLInitializationEvent event) {
		TweakerPlugin.register("gregtech", GregTech5.class);
	}
}