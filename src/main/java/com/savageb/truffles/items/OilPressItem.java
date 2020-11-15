package com.savageb.truffles.items;

import com.savageb.truffles.Truffles;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class OilPressItem extends BlockItem {

    public OilPressItem(Block blockIn) {
        super(blockIn, new Properties().group(Truffles.TRUFFLES_ITEM_GROUP));
    }
}
