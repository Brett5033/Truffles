package com.savageb.truffles.init;

import com.savageb.truffles.Truffles;
import com.savageb.truffles.world.gen.TruffleDirtFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Truffles.MOD_ID);

    public static final RegistryObject<Feature<NoFeatureConfig>> TRUFFLE_DIRT_FEATURE = FEATURES.register("truffle_dirt_feature", () -> new TruffleDirtFeature(NoFeatureConfig.field_236558_a_));

}
