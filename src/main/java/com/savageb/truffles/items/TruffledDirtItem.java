package com.savageb.truffles.items;

import com.savageb.truffles.Truffles;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class TruffledDirtItem extends BlockItem {

    public TruffledDirtItem(Block blockIn) {
        super(blockIn, new Item.Properties().group(Truffles.TRUFFLES_ITEM_GROUP));
    }
}
