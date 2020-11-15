package com.savageb.truffles.items;

import com.savageb.truffles.Truffles;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TruffleOil extends Item{
    public TruffleOil() {
        super(new Properties().group(Truffles.TRUFFLES_ITEM_GROUP)
        .maxStackSize(16));
    }

    //public static Food TRUFFLE_FOOD = (new Food.Builder()).hunger(2).saturation(2.5f).fastToEat().effect(() -> new EffectInstance(Effects.LUCK,1200,1),1f).build();
}
