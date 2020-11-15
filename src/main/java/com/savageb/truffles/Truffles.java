package com.savageb.truffles;

import com.savageb.truffles.config.Config;
//import com.savageb.truffles.config.TruffleConfig;
import com.savageb.truffles.config.EntityConfig;
import com.savageb.truffles.entities.FindBlockGoal;
import com.savageb.truffles.entities.FindTruffleGoal;
import com.savageb.truffles.entities.PigTracker;
import com.savageb.truffles.util.RegistryHandler;
import com.savageb.truffles.world.gen.BiomeGen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.ParallelDispatchEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.Configuration;
import java.util.Iterator;
import java.util.Set;

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

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml").toString());
        Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-server.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        BiomeGen.registerOres();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class EventSubscribers {

        @SubscribeEvent
        public static void onPigSpawn(EntityJoinWorldEvent event){
            if(event.getEntity() instanceof PigEntity){
                PigEntity pig = (PigEntity)event.getEntity();
                //System.out.println("Adding Pig AI!");
                pig.goalSelector.addGoal(4, new TemptGoal(pig, 1.2D, Ingredient.fromItems(RegistryHandler.TRUFFLE.get()), false));
                pig.goalSelector.addGoal(2,  new FindTruffleGoal(pig, 1.4D, EntityConfig.PIG_SEARCH_RADIUS_CONFIG.get(), 2f));
            }
        }
    }
}
