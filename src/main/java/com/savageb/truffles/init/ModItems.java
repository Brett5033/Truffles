package com.savageb.truffles.init;

import com.savageb.truffles.Truffles;
import com.savageb.truffles.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Truffles.MOD_ID);

    // Items:
    public static final RegistryObject<Item> TRUFFLE = ITEMS.register("truffle", Truffle::new);
    public static final RegistryObject<Item> FANCY_STEAK = ITEMS.register("fancy_steak", FancySteak::new);
    public static final RegistryObject<Item> TRUFFLE_OIL = ITEMS.register("truffle_oil", TruffleOil::new);

    // Block Items:
    //public static final RegistryObject<Item> TRUFFLED_DIRT_ITEM = ITEMS.register("truffled_dirt", () -> new TruffledDirtItem(TRUFFLED_DIRT.get()));
    //public static final RegistryObject<Item> OIL_PRESS_ITEM = ITEMS.register("oil_press", () -> new OilPressItem(OIL_PRESS.get()));
}
