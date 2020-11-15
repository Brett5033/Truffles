package com.savageb.truffles.world.gen;

import com.mojang.serialization.Codec;
//import com.savageb.truffles.config.TruffleConfig;
import com.savageb.truffles.config.TruffleConfig;
import com.savageb.truffles.init.ModBlocks;
import com.savageb.truffles.items.Truffle;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.Random;

public class TruffleDirtFeature extends Feature<NoFeatureConfig> {

    private int maxVeinSize = 6;
    private int trufflesMade = 0;


    public TruffleDirtFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        //System.out.println("Generate Check");
        //if(!TruffleConfig.truffleGenerate)
        //    return false;
        maxVeinSize = TruffleConfig.truffleMaxVeinSize;
        // Looks to see if it has picked a dirt block
        if(Tags.Blocks.DIRT.contains(reader.getBlockState(pos).getBlock())){
            boolean validBlock = false;
            //Array Implementation - Used for non-Tagged Blocks
            for(Block block: BiomeGen.SpawnTrufflesUnder){
                if(reader.getBlockState(pos.up()).getBlock() == block){
                    validBlock = true;
                    break;
                }
            }
            if(validBlock) {
                ArrayList<Direction> ValidDirections = new ArrayList<Direction>();

                for (Direction direction1 : Direction.values()) {
                    if (reader.getBlockState(pos.offset(direction1)).isAir(reader, pos.offset(direction1)))
                        return false; // Truffle is not surrounded by blocks, don't generate
                }
                // Reset that bad boy
                trufflesMade = 0;

                // Just in case the config is set to 0
                if(trufflesMade >= maxVeinSize)
                    return false;

                // Generate Truffle
                reader.setBlockState(pos, ModBlocks.TRUFFLED_DIRT.get().getDefaultState(), 2);
                System.out.println("FINDME: Generating Truffle at: " + pos.toString());


                // I know this is bad most likely, but have to check that the head block forms before we make more in the vein
                for (Direction direction1 : Direction.values()) {
                        generateHelper(reader, generator, rand, pos.offset(direction1), config, 2);
                    }
                }
                return true;
            }
        return false;
    }

    //Recursive helper to spawn truffle chunks. Is recursive bad, I think so, is this the best way I can think of, yes.
    private void generateHelper(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config, int blockAllowance) {
        if(!Tags.Blocks.DIRT.contains(reader.getBlockState(pos).getBlock()))
            return;
        for (Direction direction2 : Direction.values()) {
            if (blockAllowance <= 0 || trufflesMade >= maxVeinSize)
                return;
            if (reader.getBlockState(pos.offset(direction2)).isAir(reader, pos.offset(direction2)))
                return; // Truffle Neighbor is not surrounded by blocks, don't generate
        }
        // Chance for Truffle
        if (rand.nextDouble() < TruffleConfig.truffleChanceForVein) {
            // Truffle Generates, look for neighbors if allowance
            reader.setBlockState(pos, ModBlocks.TRUFFLED_DIRT.get().getDefaultState(), 2);
            trufflesMade++;

            // Check neighbors for valid pos
            for (Direction direction2 : Direction.values()) {
                generateHelper(reader, generator, rand, pos.offset(direction2), config, blockAllowance - 1);
            }
        }

    }
}
