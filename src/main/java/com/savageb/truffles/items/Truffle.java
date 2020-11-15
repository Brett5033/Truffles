package com.savageb.truffles.items;

import com.savageb.truffles.Truffles;
import com.savageb.truffles.init.ModItemGroups;
import javafx.util.Pair;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class Truffle extends Item{
    public Truffle() {
        super(new Item.Properties().group(ModItemGroups.TRUFFLES_ITEM_GROUP)
        .maxStackSize(64)
        .food(new Food.Builder()
            .effect(() -> new EffectInstance(Effects.LUCK,200,1,false,false),1f)
            .hunger(2)
            .saturation(2.4f)
            .build()));
    }

    //public static Food TRUFFLE_FOOD = (new Food.Builder()).hunger(2).saturation(2.5f).fastToEat().effect(() -> new EffectInstance(Effects.LUCK,1200,1),1f).build();
}
