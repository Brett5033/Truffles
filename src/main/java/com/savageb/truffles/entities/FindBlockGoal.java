package com.savageb.truffles.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.EnumSet;
import java.util.Random;

public class FindBlockGoal extends MoveToBlockGoal {

    public float MinSearch = 1;
    public float MaxSearch = 10;
    public Block BlockToSearch;
    public FindBlockGoal(Block blockIn,PigEntity pigIn, double speed, int length, float min) {
        super(pigIn, speed, length, 10);
        this.field_203112_e = 0; // Start Search Radius, In -> Out
        MaxSearch = length;
        MinSearch = min;
        BlockToSearch = blockIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        if(!creature.isBeingRidden())
            return false;
        if(creature.getPosition().withinDistance(destinationBlock, MinSearch) && creature.world.getBlockState(destinationBlock).getBlock() == BlockToSearch)
        {
            //System.out.println("Close to the Truffle, Stopping Search");
            SpawnFoundParticles();
            return false;
        }
        //System.out.println("Pig is being ridden, check if should execute");
        boolean shouldExecute = super.shouldExecute();
        if(shouldExecute) {
            //System.out.println("Adjusting pos: " + destinationBlock.toString() + " to");
            adjustDestination(destinationBlock);
            //System.out.println("New Adjusted pos: " + destinationBlock.toString());
        }
        return shouldExecute;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
    }

    private void SpawnFoundParticles(){
        if (!creature.world.isRemote) {
            //creature.playSound(SoundEvents.ENTITY_PIG_SADDLE, 0.15F, 1.0F);
            Random random =  new Random();
            double d0 = 0.08D;
            ((ServerWorld)creature.world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(BlockToSearch)), (double)creature.getPosition().getX() + 0.5D, (double)creature.getPosition().getY() + 0.7D, (double)creature.getPosition().getZ() + 0.5D, 3, ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D, ((double)random.nextFloat() - 0.5D) * 0.08D, (double)0.15F);
            //System.out.println("Found the Truffle");
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        if(!creature.isBeingRidden()) // Only Ridden Pigs search for Truffles
            return false;
        if(creature.getPosition().withinDistance(destinationBlock, MaxSearch)) {
            // Pig has left search radius
            return false;
        }
        boolean shouldContinue = super.shouldContinueExecuting();
        if(shouldContinue && creature.getPosition().withinDistance(destinationBlock, MinSearch) && creature.world.getBlockState(destinationBlock).getBlock() == BlockToSearch){
            SpawnFoundParticles();
        }
        return shouldContinue;
    }

    protected int getRunDelay(CreatureEntity creatureIn) {
        return 40;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
    }

    /**
     * Return true to set given position as destination
     */
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
        //System.out.println("Looking for Truffle at: " + worldIn.getBlockState(pos).getBlock().toString());
        boolean validPos = worldIn.getBlockState(pos).getBlock() == BlockToSearch;
        if(validPos) {
            //System.out.println("Found Truffle at: " + pos.toString());
            if (!creature.world.isRemote) {
                //creature.playSound(SoundEvents.ENTITY_PIG_AMBIENT, 0.15F, 1.0F);
                //Random random = new Random();
                //double d0 = 0.08D;
                //((ServerWorld) creature.world).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, new ItemStack(Items.COARSE_DIRT)), (double) creature.getPosition().getX() + 0.5D, (double) creature.getPosition().getY() + .7D, (double) creature.getPosition().getZ() + 0.5D, 1, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, ((double) random.nextFloat() - 0.5D) * 0.08D, (double) 0.15F);
            }
        }
        return validPos;
    }

    public void adjustDestination(BlockPos location){

        BlockPos traversal = location;
        float shortestDistance = 0;
        for(int k = location.getY(); k <= location.getY() + 5; k++) {
            for(int i = location.getX() - 5; i < location.getX() + 5; i++){
                for(int j = location.getZ() - 5; j < location.getZ() + 5; j++){
                    traversal.add(i, k, j);
                    if (this.creature.world.isAirBlock(traversal) && !this.creature.world.isAirBlock(traversal.down())) {
                        //if(location.distanceSq(traversal) < )
                        destinationBlock = traversal;
                        System.out.println("Adjusted block position to " + destinationBlock.toString());
                        return;
                    }
                    }
                }
            }
    }
}
