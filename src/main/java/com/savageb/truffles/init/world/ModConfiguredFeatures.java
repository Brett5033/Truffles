package com.savageb.truffles.init.world;

import com.google.common.collect.ImmutableSet;
import com.savageb.truffles.util.RegistryHandler;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.IDecoratable;
import net.minecraft.world.gen.feature.Feature;
/*
public class ModConfiguredFeatures {

    //public static final ConfiguredFeature<?, ?> TRUFFLED_DIRT = register("truffled_dirt", Feature.RANDOM_PATCH.withConfiguration(Configs.TRUFFLED_DIRT).func_242731_b(3));
    //public static final ConfiguredFeature<?, ?> TRUFFLED_DIRT = register("truffled_dirt", ModFeatures.TRUFFLED_DIRT.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(128).func_242731_b(50));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }

    public static final class Configs {
        public static final BlockClusterFeatureConfig TRUFFLED_DIRT = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(RegistryHandler.TRUFFLED_DIRT.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(10).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getDefaultState().getBlock())).xSpread(0).ySpread(0).zSpread(0).build();
    }
}*/
