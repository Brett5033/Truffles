package com.savageb.truffles.items;

import com.savageb.truffles.Truffles;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FancySteak extends Item {
    public FancySteak() {
        super(new Item.Properties().group(Truffles.TRUFFLES_ITEM_GROUP)
                .maxStackSize(64)
                .food(new Food.Builder()
                        .effect(() -> new EffectInstance(Effects.LUCK,1200,1,false,false),1f)
                        .hunger(10)
                        .saturation(14f)
                        .build()));
    }
}
