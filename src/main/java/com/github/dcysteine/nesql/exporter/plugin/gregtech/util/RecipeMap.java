package com.github.dcysteine.nesql.exporter.plugin.gregtech.util;

import com.github.dcysteine.nesql.sql.base.recipe.Dimension;
import gregtech.api.enums.ItemList;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_Recipe;

/** Enum of supported GregTech recipe maps. */
public enum RecipeMap {
    ORE_WASHER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.orewasher"),
            "orewasher",
            ItemList.Machine_HV_OreWasher,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(3, 1),
            new Dimension(0, 0)),

    THERMAL_CENTRIFUGE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.thermalcentrifuge"),
            "thermalcentrifuge",
            ItemList.Machine_HV_ThermalCentrifuge,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(3, 1),
            new Dimension(0, 0)),

    COMPRESSOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.compressor"),
            "compressor",
            ItemList.Machine_HV_Compressor,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    EXTRACTOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.extractor"),
            "extractor",
            ItemList.Machine_HV_Extractor,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),
    FUSION(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fusionreactor"),
            "fusion",
            ItemList.FusionComputer_LuV,
            true,
            new Dimension(0, 0),
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1)),

    COMPLEX_FUSION(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("kubatech.defusioncrafter"),
            "complexfusion",
            ItemList.FusionComputer_UV,
            true,
            new Dimension(0, 0),
            new Dimension(2, 2),
            new Dimension(0, 0),
            new Dimension(2, 1)),
    BLAST(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.blastfurnace"),
            "blast",
            ItemList.Machine_Multi_BlastFurnace,
            true,
            new Dimension(3, 2),
            new Dimension(1, 1),
            new Dimension(3, 2),
            new Dimension(1, 1)),    SCANNER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.scanner"),
            "scanner",
            ItemList.Machine_HV_Scanner,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    ROCK_BREAKER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.rockbreaker"),
            "rockbreaker",
            ItemList.Machine_HV_RockBreaker,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    REPLICATOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.replicator"),
            "replicator",
            ItemList.Machine_HV_Replicator,
            true,
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    ASSEMBLY_LINE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fakeAssemblylineProcess"),
            "assemblyline",
            ItemList.Machine_Multi_Assemblyline,
            false,
            new Dimension(4, 4),
            new Dimension(4, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    PLASMA_ARC_FURNACE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.plasmaarcfurnace"),
            "plasmaarcfurnace",
            ItemList.Machine_HV_PlasmaArcFurnace,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(2, 2),
            new Dimension(1, 1)),

    ARC_FURNACE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.arcfurnace"),
            "arcfurnace",
            ItemList.Machine_HV_ArcFurnace,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(2, 2),
            new Dimension(0, 0)),

    PRINTER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.printer"),
            "printer",
            ItemList.Machine_HV_Printer,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    SIFTER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.sifter"),
            "sifter",
            ItemList.Machine_HV_Sifter,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(3, 3),
            new Dimension(1, 1)),

    PRESS(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.press"),
            "press",
            ItemList.Machine_HV_Press,
            true,
            new Dimension(3, 2),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    LASER_ENGRAVER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.laserengraver"),
            "laserengraver",
            ItemList.Machine_HV_LaserEngraver,
            true,
            new Dimension(2, 2),
            new Dimension(2, 1),
            new Dimension(2, 2),
            new Dimension(2, 1)),

    MIXER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.mixer"),
            "mixer",
            ItemList.Machine_HV_Mixer,
            true,
            new Dimension(3, 3),
            new Dimension(1, 1),
            new Dimension(2, 2),
            new Dimension(1, 1)),

    AUTOCLAVE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.autoclave"),
            "autoclave",
            ItemList.Machine_HV_Autoclave,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(2, 2),
            new Dimension(1, 1)),

    ELECTROMAGNETIC_SEPARATOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.electromagneticseparator"),
            "electromagneticseparator",
            ItemList.Machine_HV_ElectromagneticSeparator,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(3, 1),
            new Dimension(0, 0)),

    POLARIZER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.polarizer"),
            "polarizer",
            ItemList.Machine_HV_Polarizer,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    MACERATOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.macerator"),
            "macerator",
            ItemList.Machine_HV_Macerator,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(2, 2),
            new Dimension(0, 0)),

    CHEMICAL_BATH(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.chemicalbath"),
            "chemicalbath",
            ItemList.Machine_HV_ChemicalBath,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(3, 1),
            new Dimension(1, 1)),

    FLUID_CANNER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fluidcanner"),
            "fluidcanner",
            ItemList.Machine_HV_FluidCanner,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1)),

    BREWING(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.brewer"),
            "brewing",
            ItemList.Machine_HV_Brewery,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1)),

    FLUID_HEATER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fluidheater"),
            "fluidheater",
            ItemList.Machine_HV_FluidHeater,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1)),

    DISTILLERY(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.distillery"),
            "distillery",
            ItemList.Machine_HV_Distillery,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1)),

    FERMENTING(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fermenter"),
            "fermenting",
            ItemList.Machine_HV_Fermenter,
            true,
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1)),

    FLUID_SOLIDIFICATION(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fluidsolidifier"),
            "fluidsolidification",
            ItemList.Machine_HV_FluidSolidifier,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    FLUID_EXTRACTION(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fluidextractor"),
            "fluidextraction",
            ItemList.Machine_HV_FluidExtractor,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(1, 1)),

    BOXINATOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.packager"),
            "packager",
            ItemList.Machine_HV_Boxinator,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    UNBOXINATOR(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.unpackager"),
            "unpackager",
            ItemList.Machine_HV_Unboxinator,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(2, 1),
            new Dimension(0, 0)),


    CENTRIFUGE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.centrifuge"),
            "centrifuge",
            ItemList.Machine_HV_Centrifuge,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(3, 2),
            new Dimension(1, 1)),

    ELECTROLYZER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.electrolyzer"),
            "electrolyzer",
            ItemList.Machine_HV_Electrolyzer,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(3, 2),
            new Dimension(1, 1)),

    PLASMA_FORGE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.plasmaforge"),
            "plasmaforge",
            ItemList.Machine_Multi_PlasmaForge,
            true,
            new Dimension(3, 3),
            new Dimension(3, 3),
            new Dimension(3, 3),
            new Dimension(3, 3)),

    PRIMITIVE_BLAST(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.primitiveblastfurnace"),
            "primitiveblast",
            ItemList.Machine_Bricked_BlastFurnace,
            true,
            new Dimension(3, 1),
            new Dimension(0, 0),
            new Dimension(3, 1),
            new Dimension(0, 0)),

    IMPLOSION(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.implosioncompressor"),
            "implosion",
            ItemList.Machine_Multi_ImplosionCompressor,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(2, 1),
            new Dimension(0, 0)),

    VACUUM(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.vacuumfreezer"),
            "vacuum",
            ItemList.Machine_Multi_VacuumFreezer,
            true,
            new Dimension(1, 1),
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(1, 1)),

    CHEMICAL(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.chemicalreactor"),
            "chemical",
            ItemList.Machine_HV_ChemicalReactor,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(2, 1),
            new Dimension(1, 1)),

    MULTIBLOCK_CHEMICAL(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.largechemicalreactor"),
            "multiblockchemical",
            ItemList.Machine_Multi_LargeChemicalReactor,
            true,
            new Dimension(3, 2),
            new Dimension(3, 2),
            new Dimension(3, 2),
            new Dimension(3, 2)),

    DISTILLATION(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.distillationtower"),
            "distillation",
            ItemList.Distillation_Tower,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(3, 4)),

    CRACKING(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.craker"),
            "cracking",
            ItemList.OilCracker,
            true,
            new Dimension(1, 1),
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(1, 1)),

    PYROLYSE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.pyro"),
            "pyrolyse",
            ItemList.PyrolyseOven,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(1, 1)),

    WIREMILL(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.wiremill"),
            "wiremill",
            ItemList.Machine_HV_Wiremill,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    BENDER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.metalbender"),
            "bender",
            ItemList.Machine_HV_Bender,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    ALLOY_SMELTER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.alloysmelter"),
            "alloysmelter",
            ItemList.Machine_HV_AlloySmelter,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    ASSEMBLER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.assembler"),
            "assembler",
            ItemList.Machine_HV_Assembler,
            true,
            new Dimension(3, 3),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    CIRCUIT_ASSEMBLER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.circuitassembler"),
            "circuitassembler",
            ItemList.Machine_HV_CircuitAssembler,
            true,
            new Dimension(3, 2),
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    CANNER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.fluidcanner"),
            "canner",
            ItemList.Machine_HV_Canner,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(2, 1),
            new Dimension(0, 0)),

    LATHE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.lathe"),
            "lathe",
            ItemList.Machine_HV_Lathe,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(2, 1),
            new Dimension(0, 0)),

    CUTTER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.cuttingsaw"),
            "cutter",
            ItemList.Machine_HV_Cutter,
            true,
            new Dimension(2, 1),
            new Dimension(1, 1),
            new Dimension(2, 2),
            new Dimension(0, 0)),

    SLICER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.slicer"),
            "slicer",
            ItemList.Machine_HV_Slicer,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    EXTRUDER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.extruder"),
            "extruder",
            ItemList.Machine_HV_Extruder,
            true,
            new Dimension(2, 1),
            new Dimension(0, 0),
            new Dimension(1, 1),
            new Dimension(0, 0)),

    HAMMER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.hammer"),
            "hammer",
            ItemList.Machine_HV_Hammer,
            true,
            new Dimension(2, 1),
            new Dimension(2, 1),
            new Dimension(2, 1),
            new Dimension(2, 1)),

    AMPLIFIERS(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.uuamplifier"),
            "amplifiers",
            ItemList.Machine_HV_Amplifab,
            true,
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(0, 0),
            new Dimension(1, 1)),

    MASS_FAB(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.massfab"),
            "massfab",
            ItemList.Machine_HV_Massfab,
            true,
            new Dimension(1, 1),
            new Dimension(1, 1),
            new Dimension(0, 0),
            new Dimension(1, 1)),

    MULTIBLOCK_ELECTROLYZER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.largeelectrolyzer"),
            "multiblockelectrolyzer",
            ItemList.Machine_IV_Electrolyzer,
            true,
            new Dimension(3, 2),
            new Dimension(3, 2),
            new Dimension(3, 2),
            new Dimension(3, 2)),

    MULTIBLOCK_CENTRIFUGE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.largecentrifuge"),
            "multiblockcentrifuge",
            ItemList.Machine_IV_Centrifuge,
            true,
            new Dimension(3, 2),
            new Dimension(3, 2),
            new Dimension(3, 2),
            new Dimension(3, 2)),

    MULTIBLOCK_MIXER(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.largemixer"),
            "multiblockmixer",
            ItemList.Machine_IV_Mixer,
            true,
            new Dimension(3, 3),
            new Dimension(3, 2),
            new Dimension(3, 3),
            new Dimension(3, 2)),

    NANO_FORGE(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.nanoforge"),
            "nanoforge",
            ItemList.NanoForge,
            true,
            new Dimension(3, 2),
            new Dimension(3, 1),
            new Dimension(2, 1),
            new Dimension(0, 0)),

    PCB_FACTORY(
            gregtech.api.recipe.RecipeMap.ALL_RECIPE_MAPS.get("gt.recipe.pcbfactory"),
            "pcbfactory",
            ItemList.PCBFactory,
            true,
            new Dimension(3, 2),
            new Dimension(3, 1),
            new Dimension(3, 3),
            new Dimension(0, 0)),
    ;



    private final gregtech.api.recipe.RecipeMap<?> recipeMap;
    /** Used for IDs. */
    private final String shortName;
    private final String name;
    private final ItemList icon;
    private final boolean shapeless;
    private final Dimension itemInputDimension;
    private final Dimension fluidInputDimension;
    private final Dimension itemOutputDimension;
    private final Dimension fluidOutputDimension;

    RecipeMap(
            gregtech.api.recipe.RecipeMap<?> recipeMap, String shortName, ItemList icon, boolean shapeless,
            Dimension itemInputDimension, Dimension fluidInputDimension,
            Dimension itemOutputDimension, Dimension fluidOutputDimension) {
        this.recipeMap = recipeMap;
        this.shortName = shortName;
        this.name = GT_LanguageManager.getTranslation(recipeMap.unlocalizedName);
        this.icon = icon;
        this.shapeless = shapeless;
        this.itemInputDimension = itemInputDimension;
        this.fluidInputDimension = fluidInputDimension;
        this.itemOutputDimension = itemOutputDimension;
        this.fluidOutputDimension = fluidOutputDimension;
    }

    public gregtech.api.recipe.RecipeMap<?> getRecipeMap() {
        return recipeMap;
    }

    public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }

    public String getName(Voltage voltage) {
        return String.format("%s (%s)", name, voltage.getName());
    }

    public ItemList getIcon() {
        return icon;
    }

    public int getAmperage() {
        return recipeMap.getAmperage();
    }

    public boolean isShapeless() {
        return shapeless;
    }

    public Dimension getItemInputDimension() {
        return itemInputDimension;
    }

    public Dimension getFluidInputDimension() {
        return fluidInputDimension;
    }

    public Dimension getItemOutputDimension() {
        return itemOutputDimension;
    }

    public Dimension getFluidOutputDimension() {
        return fluidOutputDimension;
    }
}
