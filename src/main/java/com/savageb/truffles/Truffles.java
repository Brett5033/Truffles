package com.savageb.truffles;

import com.savageb.truffles.config.Config;
import com.savageb.truffles.config.TruffleConfig;
import com.savageb.truffles.util.RegistryHandler;
import com.savageb.truffles.world.gen.BiomeGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.Configuration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("truffles")
public class Truffles
{

    public static final String MOD_ID = "truffles";
    public static Truffles instance;

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static Configuration config;

    public static final ItemGroup TRUFFLES_ITEM_GROUP =  new ItemGroup("truffles") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.TRUFFLE.get());
        }
    };

    public Truffles() {
        instance = this;

        RegistryHandler.init();

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        modBus.addListener(this::setup);
        // Register the doClientStuff method for modloading
        modBus.addListener(this::doClientStuff);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TruffleConfig.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        //Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml").toString());
        //Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-server.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        BiomeGen.registerOres();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }



}
