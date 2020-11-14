package com.savageb.truffles.world.gen;

import com.savageb.truffles.Truffles;
//import com.savageb.truffles.config.TruffleConfig;
import com.savageb.truffles.config.WorldGenConfig;
import com.savageb.truffles.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class BiomeGen {

    public static ArrayList<Block> SpawnTrufflesUnder = new ArrayList<Block>();
    public static ArrayList<String> SpawnTrufflesBiomes = new ArrayList<String>();


    private static final ArrayList<ConfiguredFeature<?, ?>> overworldGen = new ArrayList<ConfiguredFeature<?, ?>>();
    //private static final ArrayList<ConfiguredFeature<?, ?>> netherGen = new ArrayList<ConfiguredFeature<?, ?>>();
    //private static final ArrayList<ConfiguredFeature<?, ?>> endGen = new ArrayList<ConfiguredFeature<?, ?>>();

    public static void registerOres(){
        //field_241882_a is for generating in stone, granite, diorite, and andesite
        //field_241883_b is for generating in netherrack
        //field_241884_c is for generating in netherrack, basalt and blackstone

        // FIX: Not sure where to add acceptable blocks & biomes from config but imma do it here
        // Blocks to Spawn Under
        SpawnTrufflesUnder.add(Blocks.MUSHROOM_STEM);
        SpawnTrufflesUnder.add(Blocks.DARK_OAK_LOG);
        SpawnTrufflesUnder.add(Blocks.ACACIA_LOG);
        SpawnTrufflesUnder.add(Blocks.BIRCH_LOG);
        SpawnTrufflesUnder.add(Blocks.JUNGLE_LOG);
        SpawnTrufflesUnder.add(Blocks.SPRUCE_LOG);
        SpawnTrufflesUnder.add(Blocks.OAK_LOG);
        //SpawnTrufflesUnder.add(Blocks.GRASS_BLOCK);
        //SpawnTrufflesUnder.add(Blocks.PODZOL);
        //SpawnTrufflesUnder.add(Blocks.COARSE_DIRT);

        // Biomes Types to Spawn In
        SpawnTrufflesBiomes.add("dark_forest");
        SpawnTrufflesBiomes.add("dark_forest_hills");
        SpawnTrufflesBiomes.add("giant_spruce_taiga");
        SpawnTrufflesBiomes.add("giant_spruce_taiga_hills");


        //Overworld Ore Register
        overworldGen.add(register("truffled_dirt", RegistryHandler.TRUFFLED_DIRT_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(WorldGenConfig.TRUFFLE_MAX_HEIGHT.get()).func_242731_b(100)));
        /*overworldOres.add(register("truffled_dirt", Feature.ORE.withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.DIRT), RegistryHandler.TRUFFLED_DIRT.get().getDefaultState(), 4)) //Vein Size
                .range(80).square() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency

        /*
        //Nether Ore Register
        netherOres.add(register("example_nether_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER, RegistryHandler.TRUFFLED_DIRT.get().getDefaultState(), 4)) //Vein Size
                .range(64).square() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency

        //The End Ore Register
        endOres.add(register("example_end_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                new BlockMatchRuleTest(Blocks.END_STONE), RegistryHandler.TRUFFLED_DIRT.get().getDefaultState(), 4)) //Vein Size
                .range(128).square() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency
        */

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        ResourceLocation name = event.getName();
        /*
        if(event.getCategory().equals(Biome.Category.NETHER)){
            for(ConfiguredFeature<?, ?> ore : netherGen){
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
        if(event.getCategory().equals(Biome.Category.THEEND)){
            for(ConfiguredFeature<?, ?> ore : endGen){
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
         */
        boolean validBiome = false;
        for(String key: SpawnTrufflesBiomes){
            if(name.equals(new ResourceLocation(key))){
                validBiome = true;
                break;
            }
        }
        if(validBiome) {
        //if(event.getCategory().equals(Biome.Category.byName()))
            for (ConfiguredFeature<?, ?> ore : overworldGen) {
                //System.out.println("Generating Truffles in biome: " + name.toString());
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Truffles.MOD_ID + ":" + name, configuredFeature);
    }
}
