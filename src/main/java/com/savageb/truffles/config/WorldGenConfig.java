package com.savageb.truffles.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WorldGenConfig {

    public static ForgeConfigSpec.BooleanValue TRUFFLE_GENERATE;
    public static ForgeConfigSpec.IntValue TRUFFLE_MAX_VEIN_SIZE;
    public static ForgeConfigSpec.IntValue TRUFFLE_MAX_HEIGHT;
    public static ForgeConfigSpec.DoubleValue TRUFFLE_CHANCE_FOR_VEIN;


    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER){
        TRUFFLE_GENERATE = SERVER_BUILDER.comment("Generate Truffles? [default: true]").define("gen.truffle.generate", true);
        TRUFFLE_MAX_VEIN_SIZE = SERVER_BUILDER.comment("Maximum size of Truffle veins [default: 6]").defineInRange("gen.truffle.max_vein_size", 6, 0, 100);
        TRUFFLE_CHANCE_FOR_VEIN = SERVER_BUILDER.comment("Chance for larger Truffle veins [default: 0.25]").defineInRange("gen.truffle.chance", 0.25, 0.0, 1.0);
        TRUFFLE_MAX_HEIGHT = SERVER_BUILDER.comment("Maximum height for Truffle to generate [default: 90]").defineInRange("gen.arcane_crystal.max_height", 90, 1, 256);

    }
}
