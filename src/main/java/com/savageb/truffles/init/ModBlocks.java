package com.savageb.truffles.init;

import com.savageb.truffles.Truffles;
import com.savageb.truffles.blocks.OilPress;
import com.savageb.truffles.blocks.TruffledDirt;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Truffles.MOD_ID);

    // Blocks:
    public static final RegistryObject<Block> TRUFFLED_DIRT = BLOCKS.register("truffled_dirt", TruffledDirt::new);
    public static final RegistryObject<Block> OIL_PRESS = BLOCKS.register("oil_press", OilPress::new);
}
