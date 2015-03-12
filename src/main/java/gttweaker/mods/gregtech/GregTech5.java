package gttweaker.mods.gregtech;

import minetweaker.MineTweakerAPI;

public class GregTech5 {
	public GregTech5(){
		MineTweakerAPI.registerClass(AssemblerLiq.class);
        MineTweakerAPI.registerClass(ChemicalReactorLiq.class);
        MineTweakerAPI.registerClass(Mixer.class);
        MineTweakerAPI.registerClass(ChemicalBath.class);
        MineTweakerAPI.registerClass(Autoclave.class);
        MineTweakerAPI.registerClass(Distillery.class);
        MineTweakerAPI.registerClass(Brewery.class);
        MineTweakerAPI.registerClass(PrecisionLaser.class);
        MineTweakerAPI.registerClass(FormingPress.class);
        MineTweakerAPI.registerClass(Fermenter.class);
        MineTweakerAPI.registerClass(SawLiq.class);
        MineTweakerAPI.registerClass(Printer.class);
	}
}