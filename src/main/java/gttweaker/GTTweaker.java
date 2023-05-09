package gttweaker;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import gttweaker.mods.gregtech.Fuels;
import gttweaker.mods.gregtech.machines.*;
import gttweaker.mods.gtpp.machines.*;
import minetweaker.MineTweakerAPI;
import minetweaker.api.oredict.IOreDictEntry;

@Mod(modid = "GTTweaker", useMetadata = true)
public class GTTweaker {

    private static final Logger TCLOG = LogManager.getLogger("[SCRIPTS TO CODE TRANSLATOR]");

    public static void info(String s) {
        TCLOG.info(s);
    }

    public static String convertStack(ItemStack stack) {
        if (stack == null) return "null";
        GameRegistry.UniqueIdentifier itemIdentifier = GameRegistry.findUniqueIdentifierFor(stack.getItem());
        int meta = stack.getItemDamage();
        int size = stack.stackSize;
        NBTTagCompound tagCompound = stack.stackTagCompound;
        if (tagCompound == null || tagCompound.hasNoTags()) {
            return "getModItem(\"" + itemIdentifier.modId
                + "\", \""
                + itemIdentifier.name
                + "\", "
                + size
                + ", "
                + meta
                + ", missing)";
        } else {
            return "createItemStack(\"" + itemIdentifier.modId
                + "\", \""
                + itemIdentifier.name
                + "\", "
                + size
                + ", "
                + meta
                + ", "
                + "\""
                + tagCompound.toString()
                    .replace("\"", "\\\"")
                + "\""
                + ", missing)";
        }
    }

    public static String convertStacks(ItemStack[] list) {
        StringBuilder stacks = new StringBuilder("new ItemStack[]{");
        for (ItemStack stack : list) {
            stacks.append(convertStack(stack))
                .append(", ");
        }
        stacks.append("}");
        return stacks.toString();
    }

    public static String convertFluidStack(FluidStack stack) {
        if (stack == null) return "null";
        return "FluidRegistry.getFluidStack(\"" + stack.getFluid()
            .getName() + "\", " + stack.amount + ")";
    }

    public static String convertArrayInLine(Object[] arr) {
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0, arrLength = arr.length; i < arrLength; i++) {
            Object o = arr[i];
            if (o instanceof String) arrayString.append("\"")
                .append((String) o)
                .append("\"");
            else if (o instanceof Character) arrayString.append("'")
                .append((char) o)
                .append("'");
            else if (o instanceof ItemStack) arrayString.append(convertStack((ItemStack) o));
            else if (o instanceof FluidStack) arrayString.append(convertFluidStack((FluidStack) o));
            // else if (o instanceof IItemStack) arrayString.append(convertStack((IIngredient) o));
            else if (o instanceof IOreDictEntry) arrayString.append("\"")
                .append((String) ((IOreDictEntry) o).getInternal())
                .append("\"");
            else if (o instanceof Integer) arrayString.append(((Integer) o).intValue());
            else if (o == null) arrayString.append("null");
            else arrayString.append(o);
            if (i + 1 < arrLength) arrayString.append(", ");
        }
        return arrayString.toString();
    }

    public static void logGTRecipe(ItemStack[] itemInputs, ItemStack[] itemOutputs, int[] chances,
        FluidStack[] fluidInputs, FluidStack[] fluidOutputs, int duration, int eut, Integer specialValue,
        String recipeMapVariable) {
        StringBuilder builder = new StringBuilder("GT_Values.RA.stdBuilder()");
        Function<ItemStack[], ItemStack[]> isAllObjectsNullItemStack = arr -> {
            if (arr == null || arr.length == 0
                || Arrays.stream(arr)
                    .allMatch(Objects::isNull))
                return null;
            return Arrays.stream(arr)
                .filter(Objects::nonNull)
                .toArray(ItemStack[]::new);
        };
        Function<FluidStack[], FluidStack[]> isAllObjectsNullFluidStack = arr -> {
            if (arr == null || arr.length == 0
                || Arrays.stream(arr)
                    .allMatch(Objects::isNull))
                return null;
            return Arrays.stream(arr)
                .filter(Objects::nonNull)
                .toArray(FluidStack[]::new);
        };
        itemInputs = isAllObjectsNullItemStack.apply(itemInputs);
        itemOutputs = isAllObjectsNullItemStack.apply(itemOutputs);
        fluidInputs = isAllObjectsNullFluidStack.apply(fluidInputs);
        fluidOutputs = isAllObjectsNullFluidStack.apply(fluidOutputs);
        if (itemInputs == null || itemInputs.length == 0) builder.append(".noItemInputs()");
        else builder.append(".itemInputs(")
            .append(convertArrayInLine(itemInputs))
            .append(")");
        if (itemOutputs == null || itemOutputs.length == 0) builder.append(".noItemOutputs()");
        else builder.append(".itemOutputs(")
            .append(convertArrayInLine(itemOutputs))
            .append(")");
        if (chances != null && chances.length > 0) builder.append(".outputChances(")
            .append(
                convertArrayInLine(
                    Arrays.stream(chances)
                        .boxed()
                        .toArray(Integer[]::new)))
            .append(")");
        if (fluidInputs == null || fluidInputs.length == 0) builder.append(".noFluidInputs()");
        else builder.append(".fluidInputs(")
            .append(convertArrayInLine(fluidInputs))
            .append(")");
        if (fluidOutputs == null || fluidOutputs.length == 0) builder.append(".noFluidOutputs()");
        else builder.append(".fluidOutputs(")
            .append(convertArrayInLine(fluidOutputs))
            .append(")");
        builder.append(".duration(")
            .append(duration)
            .append(")");
        builder.append(".eut(")
            .append(eut)
            .append(")");
        if (specialValue != null) builder.append(".specialValue(")
            .append(specialValue.intValue())
            .append(")");
        builder.append(".addTo(")
            .append(recipeMapVariable)
            .append(");");

        info(builder.toString());
    }

    public static void logGTRecipe(ItemStack[] itemInputs, ItemStack[] itemOutputs, FluidStack[] fluidInputs,
        FluidStack[] fluidOutputs, int duration, int eut, String recipeMapVariable) {
        logGTRecipe(itemInputs, itemOutputs, null, fluidInputs, fluidOutputs, duration, eut, null, recipeMapVariable);
    }

    public static void logGTRecipe(ItemStack[] itemInputs, ItemStack[] itemOutputs, int[] chances,
        FluidStack[] fluidInputs, FluidStack[] fluidOutputs, int duration, int eut, String recipeMapVariable) {
        logGTRecipe(
            itemInputs,
            itemOutputs,
            chances,
            fluidInputs,
            fluidOutputs,
            duration,
            eut,
            null,
            recipeMapVariable);
    }

    public static void logGTRecipe(ItemStack[] itemInputs, ItemStack[] itemOutputs, FluidStack[] fluidInputs,
        FluidStack[] fluidOutputs, int duration, int eut, int specialValue, String recipeMapVariable) {
        logGTRecipe(
            itemInputs,
            itemOutputs,
            null,
            fluidInputs,
            fluidOutputs,
            duration,
            eut,
            specialValue,
            recipeMapVariable);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MineTweakerAPI.registerClass(AlloySmelter.class);
        MineTweakerAPI.registerClass(Amplifabricator.class);
        MineTweakerAPI.registerClass(Assembler.class);
        MineTweakerAPI.registerClass(Autoclave.class);
        MineTweakerAPI.registerClass(BlastFurnace.class);
        MineTweakerAPI.registerClass(Brewery.class);
        MineTweakerAPI.registerClass(Canner.class);
        MineTweakerAPI.registerClass(Centrifuge.class);
        MineTweakerAPI.registerClass(ChemicalBath.class);
        MineTweakerAPI.registerClass(ChemicalReactor.class);
        MineTweakerAPI.registerClass(CuttingSaw.class);
        MineTweakerAPI.registerClass(Distillery.class);
        MineTweakerAPI.registerClass(Electrolyzer.class);
        MineTweakerAPI.registerClass(Extruder.class);
        MineTweakerAPI.registerClass(Fermenter.class);
        MineTweakerAPI.registerClass(FluidCanner.class);
        MineTweakerAPI.registerClass(FluidExtractor.class);
        MineTweakerAPI.registerClass(FluidHeater.class);
        MineTweakerAPI.registerClass(FluidSolidifier.class);
        MineTweakerAPI.registerClass(ForgeHammer.class);
        MineTweakerAPI.registerClass(FormingPress.class);
        MineTweakerAPI.registerClass(Fuels.class);
        MineTweakerAPI.registerClass(ImplosionCompressor.class);
        MineTweakerAPI.registerClass(Lathe.class);
        MineTweakerAPI.registerClass(Mixer.class);
        MineTweakerAPI.registerClass(Packer.class);
        MineTweakerAPI.registerClass(PlateBender.class);
        MineTweakerAPI.registerClass(Polarizer.class);
        MineTweakerAPI.registerClass(PrecisionLaser.class);
        MineTweakerAPI.registerClass(Printer.class);
        MineTweakerAPI.registerClass(Pulverizer.class);
        MineTweakerAPI.registerClass(Separator.class);
        MineTweakerAPI.registerClass(Sifter.class);
        MineTweakerAPI.registerClass(Slicer.class);
        MineTweakerAPI.registerClass(Unpacker.class);
        MineTweakerAPI.registerClass(VacuumFreezer.class);
        MineTweakerAPI.registerClass(Wiremill.class);
        if (GregTech_API.VERSION >= 508) {
            MineTweakerAPI.registerClass(ArcFurnace.class);
            MineTweakerAPI.registerClass(DistillationTower.class);
            MineTweakerAPI.registerClass(FusionReactor.class);
            MineTweakerAPI.registerClass(PlasmaArcFurnace.class);
        }
        if (GregTech_API.VERSION >= 509) {
            MineTweakerAPI.registerClass(PyrolyseOven.class);
            MineTweakerAPI.registerClass(OilCracker.class);
            MineTweakerAPI.registerClass(AssemblyLine.class);
            MineTweakerAPI.registerClass(CircuitAssembler.class);
            MineTweakerAPI.registerClass(Compressor.class);
            MineTweakerAPI.registerClass(Extractor.class);
            MineTweakerAPI.registerClass(OreWasher.class);
            MineTweakerAPI.registerClass(ThermalCentrifuge.class);
            MineTweakerAPI.registerClass(PrimitiveBlastFurnace.class);
        }
        if (GregTech_API.mGTPlusPlus) {
            MineTweakerAPI.registerClass(BlastSmelter.class);
            MineTweakerAPI.registerClass(CokeOven.class);
            MineTweakerAPI.registerClass(Dehydrator.class);
            MineTweakerAPI.registerClass(MatterFabricator.class);
            MineTweakerAPI.registerClass(MultiblockCentrifuge.class);
            MineTweakerAPI.registerClass(MultiblockElectrolyzer.class);
        }
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent ev) {
        MineTweakerAPI.registerClassRegistry(GTTweakerRegistry.class);
    }
}
