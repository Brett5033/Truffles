package com.savageb.truffles.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class TruffledDirt extends Block {
    public TruffledDirt() {
        super(Properties.create(Material.EARTH)
            .hardnessAndResistance(1.0f,1.1f)
            .sound(SoundType.GROUND)
            .harvestLevel(0)
            .harvestTool(ToolType.SHOVEL)
            .setLightLevel(value -> 0)
        );
    }
}
