package com.savageb.truffles.entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindBlockGoal extends MoveToBlockGoal {
    public FindBlockGoal(CreatureEntity creature, double speedIn, int length) {
        super(creature, speedIn, length);
    }

    @Override
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
        worldIn.getBlockState(pos);

        return false;
    }
}
