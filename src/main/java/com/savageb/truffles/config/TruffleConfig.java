package com.savageb.truffles.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.savageb.truffles.Truffles;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber
public class TruffleConfig {

    // Config Holder "Class"
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ClientConfig CLIENT;
    public static final ServerConfig SERVER;
    static {
        {
            final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
            CLIENT_SPEC = specPair.getRight();
            CLIENT = specPair.getLeft();
        }
        {
            final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
            SERVER_SPEC = specPair.getRight();
            SERVER = specPair.getLeft();
        }
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }

    // Config Helper "Class"
    public static void bakeClient(final ModConfig config){
        oilPressGui = CLIENT.OIL_PRESS_GUI.get();
    }

    public static void bakeServer(final ModConfig config){
        truffleGenerate = SERVER.TRUFFLE_GENERATE.get();
        truffleMaxVeinSize = SERVER.TRUFFLE_MAX_VEIN_SIZE.get();
        truffleMaxHeight = SERVER.TRUFFLE_MAX_HEIGHT.get();
        truffleChanceForVein = SERVER.TRUFFLE_CHANCE_FOR_VEIN.get();

        pigSearch = ServerConfig.PIG_SEARCH.get();
        pigSearchRadius = ServerConfig.PIG_SEARCH_RADIUS.get();
        pigSearchHeight = ServerConfig.PIG_SEARCH_RADIUS.get();
    }

    // Baked Configs
    public static Boolean truffleGenerate;
    public static int truffleMaxVeinSize;
    public static int truffleMaxHeight;
    public static double truffleChanceForVein;

    public static Boolean pigSearch;
    public static int pigSearchRadius;
    public static int pigSearchHeight;

    public static Boolean oilPressGui;

    /* Unused
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
        if (configEvent.getConfig().getSpec() == TruffleConfig.CLIENT_SPEC) {
            bakeConfig();
        }
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).preserveInsertionOrder().sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
    */

    // Config Classes
    public static class ClientConfig {

        public final ForgeConfigSpec.BooleanValue OIL_PRESS_GUI;
        // Nothing Client Side Yet
        ClientConfig(ForgeConfigSpec.Builder builder){
            OIL_PRESS_GUI = builder
                    .comment("Show Oil Press UI? [default: true]").
                            translation(Truffles.MOD_ID + ".config" + "OIL_PRESS_GUI")
                    .define("OIL_PRESS_GUI", true);
        }
    }

    public static class ServerConfig {

        public final ForgeConfigSpec.BooleanValue TRUFFLE_GENERATE;
        public final ForgeConfigSpec.IntValue TRUFFLE_MAX_VEIN_SIZE;
        public final ForgeConfigSpec.IntValue TRUFFLE_MAX_HEIGHT;
        public final ForgeConfigSpec.DoubleValue TRUFFLE_CHANCE_FOR_VEIN;

        public static ForgeConfigSpec.BooleanValue PIG_SEARCH;
        public static ForgeConfigSpec.IntValue PIG_SEARCH_RADIUS;
        public static ForgeConfigSpec.IntValue PIG_SEARCH_HEIGHT;

        public ServerConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Generation Values:");
            TRUFFLE_GENERATE = builder
                    .comment("Generate Truffles? [default: true]").
                    translation(Truffles.MOD_ID + ".config" + "TRUFFLE_GENERATE")
                    .define("TRUFFLE_GENERATE", true);
            TRUFFLE_MAX_VEIN_SIZE = builder
                    .comment("Maximum size of Truffle veins [default: 6]")
                    .translation(Truffles.MOD_ID + ".config" + "TRUFFLE_MAX_VEIN_SIZE")
                    .defineInRange("TRUFFLE_MAX_VEIN_SIZE", 6,0,100);
            TRUFFLE_CHANCE_FOR_VEIN = builder
                    .comment("Chance for larger Truffle veins [default: 0.25]")
                    .translation(Truffles.MOD_ID + ".config" + "TRUFFLE_CHANCE_FOR_VEIN")
                    .defineInRange("TRUFFLE_CHANCE_FOR_VEIN", 0.25,0.0,1.0);
            TRUFFLE_MAX_HEIGHT = builder
                    .comment("Maximum height for Truffle to generate [default: 90]")
                    .translation(Truffles.MOD_ID + ".config" + "TRUFFLE_MAX_HEIGHT")
                    .defineInRange("TRUFFLE_MAX_HEIGHT", 90,1,256);
            builder.pop();

            builder.push("Pig Search Values:");
            PIG_SEARCH = builder
                    .comment("Pigs search for Truffles? [default: true]")
                    .translation(Truffles.MOD_ID + ".config" + "PIG_SEARCH")
                    .define("PIG_SEARCH", true);
            PIG_SEARCH_RADIUS = builder
                    .comment("Maximum search distance of Pigs [default: 10]")
                    .translation(Truffles.MOD_ID + ".config" + "PIG_SEARCH_RADIUS")
                    .defineInRange("PIG_SEARCH_RADIUS", 50,0,100);
            PIG_SEARCH_HEIGHT = builder
                    .comment("Maximum height (+/-) for Pigs to search [default: 20]")
                    .translation(Truffles.MOD_ID + ".config" + "PIG_SEARCH_HEIGHT")
                    .defineInRange("PIG_SEARCH_HEIGHT", 5,1,40);
            builder.pop();
        }

    }
}
