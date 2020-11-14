package com.savageb.truffles.config;

import com.savageb.truffles.Truffles;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityConfig {

    public static ForgeConfigSpec.BooleanValue PIG_SEARCH_CONFIG;
    public static ForgeConfigSpec.IntValue PIG_SEARCH_RADIUS_CONFIG;
    public static ForgeConfigSpec.IntValue PIG_SEARCH_HEIGHT_CONFIG;


    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER){
        SERVER_BUILDER.comment("World Gen Config");

        PIG_SEARCH_CONFIG = SERVER_BUILDER.comment("Pigs search for Truffles? [default: true]").
                translation(Truffles.MOD_ID + ".config" + "PIG_SEARCH").define("PIG_SEARCH", true);
        SERVER_BUILDER.push("Pig Search Values:");
        PIG_SEARCH_RADIUS_CONFIG = SERVER_BUILDER.comment("Maximum search distance of Pigs [default: 10]").
                translation(Truffles.MOD_ID + ".config" + "PIG_SEARCH_RADIUS").defineInRange("PIG_SEARCH_RADIUS", 50,0,100);
        PIG_SEARCH_HEIGHT_CONFIG = SERVER_BUILDER.comment("Maximum height (+/-) for Pigs to search [default: 20]").
                translation(Truffles.MOD_ID + ".config" + "PIG_SEARCH_HEIGHT").defineInRange("PIG_SEARCH_HEIGHT", 5,1,40);
        SERVER_BUILDER.pop();

    }
}
