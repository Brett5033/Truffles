package com.savageb.truffles.entities;

import com.savageb.truffles.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.PigEntity;

public class FindTruffleGoal extends FindBlockGoal{
    public FindTruffleGoal(PigEntity pigIn, double speed, int length, float min) {
        super(RegistryHandler.TRUFFLED_DIRT.get(), pigIn, speed, length, min);
    }
}
