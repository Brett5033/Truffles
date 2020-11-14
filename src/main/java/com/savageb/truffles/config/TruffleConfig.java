package com.savageb.truffles.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.savageb.truffles.Truffles;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
/*
@Mod.EventBusSubscriber
public class TruffleConfig {
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;
    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }
    public static Boolean TRUFFLE_GENERATE;
    public static int TRUFFLE_MAX_VEIN_SIZE;
    public static int TRUFFLE_MAX_HEIGHT;
    public static double TRUFFLE_CHANCE_FOR_VEIN;

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

    public static void bakeConfig(){
        TRUFFLE_GENERATE = CLIENT.TRUFFLE_GENERATE.get();
        TRUFFLE_MAX_VEIN_SIZE = CLIENT.TRUFFLE_MAX_VEIN_SIZE.get();
        TRUFFLE_MAX_HEIGHT = CLIENT.TRUFFLE_MAX_HEIGHT.get();
        TRUFFLE_CHANCE_FOR_VEIN = CLIENT.TRUFFLE_CHANCE_FOR_VEIN.get();
    }

    // Doesn't need to be an inner class
    public static class ClientConfig {

        public final ForgeConfigSpec.BooleanValue TRUFFLE_GENERATE;
        public final ForgeConfigSpec.IntValue TRUFFLE_MAX_VEIN_SIZE;
        public final ForgeConfigSpec.IntValue TRUFFLE_MAX_HEIGHT;
        public final ForgeConfigSpec.DoubleValue TRUFFLE_CHANCE_FOR_VEIN;
        public ClientConfig(ForgeConfigSpec.Builder builder) {
            TRUFFLE_GENERATE = builder.comment("Generate Truffles? [default: true]").
                    translation(Truffles.MOD_ID + ".config" + "TRUFFLE_GENERATE").define("TRUFFLE_GENERATE", false);
            builder.push("Generation Values:");
            TRUFFLE_MAX_VEIN_SIZE = builder.comment("Maximum size of Truffle veins [default: 6]").
                    translation(Truffles.MOD_ID + ".config" + "TRUFFLE_MAX_VEIN_SIZE").defineInRange("TRUFFLE_MAX_VEIN_SIZE", 6,0,100);
            TRUFFLE_CHANCE_FOR_VEIN = builder.comment("Chance for larger Truffle veins [default: 0.25]").
                    translation(Truffles.MOD_ID + ".config" + "TRUFFLE_CHANCE_FOR_VEIN").defineInRange("TRUFFLE_CHANCE_FOR_VEIN", 0.25,0.0,1.0);
            TRUFFLE_MAX_HEIGHT = builder.comment("Maximum height for Truffle to generate [default: 90]").
                    translation(Truffles.MOD_ID + ".config" + "TRUFFLE_MAX_HEIGHT").defineInRange("TRUFFLE_MAX_HEIGHT", 90,1,256);
            builder.pop();
        }

    }
}*/
