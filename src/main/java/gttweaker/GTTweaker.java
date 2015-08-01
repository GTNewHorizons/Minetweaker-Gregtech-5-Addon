package gttweaker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import gttweaker.mods.gregtech.*;
import minetweaker.MineTweakerAPI;
import gregtech.api.*;

@Mod(modid = "GTTweaker", name = "GTTweaker", dependencies = "", version = "0.7.4")
public class GTTweaker {
	@EventHandler
	public void init(FMLInitializationEvent event) {
                MineTweakerAPI.registerClass(Amplifabricator.class);
                MineTweakerAPI.registerClass(AssemblerLiq.class);
                MineTweakerAPI.registerClass(Autoclave.class);
                MineTweakerAPI.registerClass(BlastFurnaceLiq.class);
                MineTweakerAPI.registerClass(Brewery.class);
                MineTweakerAPI.registerClass(CentrifugeLiq.class);
                MineTweakerAPI.registerClass(ChemicalBathLiq.class);
                MineTweakerAPI.registerClass(ChemicalReactorLiq.class);
                MineTweakerAPI.registerClass(Distillery.class);
                MineTweakerAPI.registerClass(ElectrolyzerLiq.class);
                MineTweakerAPI.registerClass(Fermenter.class);
                MineTweakerAPI.registerClass(FluidCanner.class);
                MineTweakerAPI.registerClass(FluidExtractor.class);
                MineTweakerAPI.registerClass(FluidHeater.class);
                MineTweakerAPI.registerClass(FluidSolidifier.class);
                MineTweakerAPI.registerClass(FormingPress.class);
                MineTweakerAPI.registerClass(Mixer.class);
                MineTweakerAPI.registerClass(Packer.class);
                MineTweakerAPI.registerClass(Polarizer.class);
                MineTweakerAPI.registerClass(PrecisionLaser.class);
                MineTweakerAPI.registerClass(Printer.class);
                MineTweakerAPI.registerClass(Pulverizer.class);
                MineTweakerAPI.registerClass(SawLiq.class);
                MineTweakerAPI.registerClass(Separator.class);
                MineTweakerAPI.registerClass(Sifter.class);
                MineTweakerAPI.registerClass(Slicer.class);
                MineTweakerAPI.registerClass(Unpacker.class);
                if(GregTech_API.VERSION>=508){
                MineTweakerAPI.registerClass(ArcFurnace.class);
                MineTweakerAPI.registerClass(DistillationTowerLiq.class);
                MineTweakerAPI.registerClass(FusionReactorLiq.class);
                MineTweakerAPI.registerClass(PlasmaArcFurnace.class);}
            }
}
