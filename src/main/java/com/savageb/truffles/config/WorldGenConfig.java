package com.savageb.truffles.config;

import com.savageb.truffles.Truffles;
import net.minecraftforge.common.ForgeConfigSpec;

public class WorldGenConfig {


    public static ForgeConfigSpec.BooleanValue TRUFFLE_GENERATE;
    public static ForgeConfigSpec.IntValue TRUFFLE_MAX_VEIN_SIZE;
    public static ForgeConfigSpec.IntValue TRUFFLE_MAX_HEIGHT;
    public static ForgeConfigSpec.DoubleValue TRUFFLE_CHANCE_FOR_VEIN;

    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER){
        SERVER_BUILDER.comment("World Gen Config");

        TRUFFLE_GENERATE = SERVER_BUILDER.comment("Generate Truffles? [default: true]").
                translation(Truffles.MOD_ID + ".config" + "TRUFFLE_GENERATE").define("TRUFFLE_GENERATE", true);
        SERVER_BUILDER.push("Generation Values:");
        TRUFFLE_MAX_VEIN_SIZE = SERVER_BUILDER.comment("Maximum size of Truffle veins [default: 6]").
                translation(Truffles.MOD_ID + ".config" + "TRUFFLE_MAX_VEIN_SIZE").defineInRange("TRUFFLE_MAX_VEIN_SIZE", 6,0,100);
        TRUFFLE_CHANCE_FOR_VEIN = SERVER_BUILDER.comment("Chance for larger Truffle veins [default: 0.25]").
                translation(Truffles.MOD_ID + ".config" + "TRUFFLE_CHANCE_FOR_VEIN").defineInRange("TRUFFLE_CHANCE_FOR_VEIN", 0.25,0.0,1.0);
        TRUFFLE_MAX_HEIGHT = SERVER_BUILDER.comment("Maximum height for Truffle to generate [default: 90]").
                translation(Truffles.MOD_ID + ".config" + "TRUFFLE_MAX_HEIGHT").defineInRange("TRUFFLE_MAX_HEIGHT", 90,1,256);
        SERVER_BUILDER.pop();

    }
}
