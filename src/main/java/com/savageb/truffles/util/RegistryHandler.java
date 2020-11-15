package com.savageb.truffles.util;

import com.savageb.truffles.Truffles;
import com.savageb.truffles.blocks.*;
import com.savageb.truffles.entities.PigTracker;
import com.savageb.truffles.items.*;
import com.savageb.truffles.world.gen.TruffledDirtFeature;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    // Registries:
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Truffles.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Truffles.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Truffles.MOD_ID);
    // public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Truffles.MOD_ID);

    // Items:
    public static final RegistryObject<Item> TRUFFLE = ITEMS.register("truffle", Truffle::new);
    public static final RegistryObject<Item> FANCY_STEAK = ITEMS.register("fancy_steak", FancySteak::new);
    public static final RegistryObject<Item> TRUFFLE_OIL = ITEMS.register("truffle_oil", TruffleOil::new);

    // Blocks:
    public static final RegistryObject<Block> TRUFFLED_DIRT = BLOCKS.register("truffled_dirt", TruffledDirt::new);
    public static final RegistryObject<Block> OIL_PRESS = BLOCKS.register("oil_press", OilPress::new);


    // Block Items:
    public static final RegistryObject<Item> TRUFFLED_DIRT_ITEM = ITEMS.register("truffled_dirt", () -> new TruffledDirtItem(TRUFFLED_DIRT.get()));
    public static final RegistryObject<Item> OIL_PRESS_ITEM = ITEMS.register("oil_press", () -> new OilPressItem(OIL_PRESS.get()));

    // Features:
    public static final RegistryObject<Feature<NoFeatureConfig>> TRUFFLED_DIRT_FEATURE = FEATURES.register("truffled_dirt", () -> new TruffledDirtFeature(NoFeatureConfig.field_236558_a_));

    // Entity Types:
    // public static final RegistryObject<EntityType<PigTracker>> PIG_TRACKER = ENTITY_TYPES
    //        .register("pig", () -> EntityType.Builder.create(PigTracker::new, EntityClassification.CREATURE).size(0.9f,0.9f)
    //        .build(new ResourceLocation(Truffles.MOD_ID, "pig").toString()));

    public static void init(){
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modBus);
        Truffles.LOGGER.info("Items registered.");
        BLOCKS.register(modBus);
        Truffles.LOGGER.info("Blocks registered.");
        FEATURES.register(modBus);
        Truffles.LOGGER.info("Features registered");
        //ENTITY_TYPES.register(modBus);
        //Truffles.LOGGER.info("Entity Types registered");
    }
}
