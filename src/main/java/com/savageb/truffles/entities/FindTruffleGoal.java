package com.savageb.truffles.entities;

import com.savageb.truffles.init.ModBlocks;
import net.minecraft.entity.passive.PigEntity;

public class FindTruffleGoal extends FindBlockGoal{
    public FindTruffleGoal(PigEntity pigIn, double speed, int length, float min) {
        super(ModBlocks.TRUFFLED_DIRT.get(), pigIn, speed, length, min);
    }
}
