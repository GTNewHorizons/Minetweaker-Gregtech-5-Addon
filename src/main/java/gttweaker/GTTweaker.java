package gttweaker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import gttweaker.mods.gregtech.*;
import minetweaker.MineTweakerAPI;

@Mod(modid = "GTTweaker", name = "GTTweaker", dependencies = "required-after:MineTweaker3", version = "0.4.0")
public class GTTweaker {
	@EventHandler
	public void init(FMLInitializationEvent event) {
                MineTweakerAPI.registerClass(AssemblerLiq.class);
                MineTweakerAPI.registerClass(ChemicalReactorLiq.class);
                MineTweakerAPI.registerClass(Mixer.class);
                MineTweakerAPI.registerClass(ChemicalBathLiq.class);
                MineTweakerAPI.registerClass(Autoclave.class);
                MineTweakerAPI.registerClass(Distillery.class);
                MineTweakerAPI.registerClass(Brewery.class);
                MineTweakerAPI.registerClass(PrecisionLaser.class);
                MineTweakerAPI.registerClass(FormingPress.class);
                MineTweakerAPI.registerClass(Fermenter.class);
                MineTweakerAPI.registerClass(SawLiq.class);
                MineTweakerAPI.registerClass(Printer.class);
                MineTweakerAPI.registerClass(CentrifugeLiq.class);
                MineTweakerAPI.registerClass(ElectrolyzerLiq.class);
                MineTweakerAPI.registerClass(Sifter.class);
                MineTweakerAPI.registerClass(Pulverizer.class);
                MineTweakerAPI.registerClass(FluidSolidifier.class);
                MineTweakerAPI.registerClass(FluidExtractor.class);
                MineTweakerAPI.registerClass(BlastFurnaceLiq.class);
            }
}