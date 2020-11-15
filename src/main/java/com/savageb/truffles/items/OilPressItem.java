package com.savageb.truffles.items;

import com.savageb.truffles.Truffles;
import com.savageb.truffles.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class OilPressItem extends BlockItem {

    public OilPressItem(Block blockIn) {
        super(blockIn, new Properties().group(ModItemGroups.TRUFFLES_ITEM_GROUP));
    }
}
