package com.savageb.truffles.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.client.model.b3d.B3DModel;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class OilPress extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    // Voxel Shapes
    // North
    private static final VoxelShape Shape_N = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 2, 15),
            Block.makeCuboidShape(12, 2, 5, 13, 3, 6),
            Block.makeCuboidShape(12, 2, 8, 13, 3, 9),
            Block.makeCuboidShape(9, 2, 5, 10, 3, 6),
            Block.makeCuboidShape(9, 2, 8, 10, 3, 9),
            Block.makeCuboidShape(9, 3, 5, 13, 4, 9),
            Block.makeCuboidShape(12, 4, 5, 13, 6, 9),
            Block.makeCuboidShape(9, 4, 5, 10, 6, 9),
            Block.makeCuboidShape(10, 4, 5, 12, 6, 6),
            Block.makeCuboidShape(10, 4, 8, 12, 6, 9),
            Block.makeCuboidShape(11, 4, 7, 12, 5, 8),
            Block.makeCuboidShape(10, 4, 6, 11, 5, 7),
            Block.makeCuboidShape(3, 2, 5, 6, 3, 8),
            Block.makeCuboidShape(3, 6, 5, 6, 7, 8),
            Block.makeCuboidShape(3, 7, 8, 6, 8, 9),
            Block.makeCuboidShape(3, 7, 4, 6, 8, 5),
            Block.makeCuboidShape(6, 7, 5, 7, 8, 8),
            Block.makeCuboidShape(2, 7, 5, 3, 8, 8),
            Block.makeCuboidShape(3, 3, 4, 6, 6, 9),
            Block.makeCuboidShape(2, 3, 5, 3, 6, 8),
            Block.makeCuboidShape(6, 3, 5, 7, 6, 8),
            Block.makeCuboidShape(10, 7, 6, 12, 8, 8),
            Block.makeCuboidShape(10, 10, 6, 12, 11, 8),
            Block.makeCuboidShape(10, 11, 5, 12, 12, 6),
            Block.makeCuboidShape(9, 11, 6, 10, 12, 8),
            Block.makeCuboidShape(12, 11, 6, 13, 12, 8),
            Block.makeCuboidShape(10, 11, 8, 12, 12, 9),
            Block.makeCuboidShape(10, 8, 5, 12, 10, 9),
            Block.makeCuboidShape(9, 8, 6, 10, 10, 8),
            Block.makeCuboidShape(12, 8, 6, 13, 10, 8),
            Block.makeCuboidShape(10, 11, 6, 11, 14, 7),
            Block.makeCuboidShape(4, 7, 6, 5, 14, 7),
            Block.makeCuboidShape(4.000000000000002, 14, 6, 11, 15, 7),
            Block.makeCuboidShape(7, 2, 10, 8, 15, 11),
            Block.makeCuboidShape(7, 14.999999999999998, 5, 8, 16, 11),
            Block.makeCuboidShape(7, 13.999999999999998, 5, 8, 15, 6),
            Block.makeCuboidShape(7, 12.999999999999998, 5, 8, 14, 7)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    // East
    private static final VoxelShape Shape_E = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 2, 15),
            Block.makeCuboidShape(10, 2, 12, 11, 3, 13),
            Block.makeCuboidShape(7, 2, 12, 8, 3, 13),
            Block.makeCuboidShape(10, 2, 9, 11, 3, 10),
            Block.makeCuboidShape(7, 2, 9, 8, 3, 10),
            Block.makeCuboidShape(7, 3, 9, 11, 4, 13),
            Block.makeCuboidShape(7, 4, 12, 11, 6, 13),
            Block.makeCuboidShape(7, 4, 9, 11, 6, 10),
            Block.makeCuboidShape(10, 4, 10, 11, 6, 12),
            Block.makeCuboidShape(7, 4, 10, 8, 6, 12),
            Block.makeCuboidShape(8, 4, 11, 9, 5, 12),
            Block.makeCuboidShape(9, 4, 10, 10, 5, 11),
            Block.makeCuboidShape(8, 2, 3, 11, 3, 6),
            Block.makeCuboidShape(8, 6, 3, 11, 7, 6),
            Block.makeCuboidShape(7, 7, 3, 8, 8, 6),
            Block.makeCuboidShape(11, 7, 3, 12, 8, 6),
            Block.makeCuboidShape(8, 7, 6, 11, 8, 7),
            Block.makeCuboidShape(8, 7, 2, 11, 8, 3),
            Block.makeCuboidShape(7, 3, 3, 12, 6, 6),
            Block.makeCuboidShape(8, 3, 2, 11, 6, 3),
            Block.makeCuboidShape(8, 3, 6, 11, 6, 7),
            Block.makeCuboidShape(8, 7, 10, 10, 8, 12),
            Block.makeCuboidShape(8, 10, 10, 10, 11, 12),
            Block.makeCuboidShape(10, 11, 10, 11, 12, 12),
            Block.makeCuboidShape(8, 11, 9, 10, 12, 10),
            Block.makeCuboidShape(8, 11, 12, 10, 12, 13),
            Block.makeCuboidShape(7, 11, 10, 8, 12, 12),
            Block.makeCuboidShape(7, 8, 10, 11, 10, 12),
            Block.makeCuboidShape(8, 8, 9, 10, 10, 10),
            Block.makeCuboidShape(8, 8, 12, 10, 10, 13),
            Block.makeCuboidShape(9, 11, 10, 10, 14, 11),
            Block.makeCuboidShape(9, 7, 4, 10, 14, 5),
            Block.makeCuboidShape(9, 14, 4.000000000000002, 10, 15, 11),
            Block.makeCuboidShape(5, 2, 7, 6, 15, 8),
            Block.makeCuboidShape(5, 14.999999999999998, 7, 11, 16, 8),
            Block.makeCuboidShape(10, 13.999999999999998, 7, 11, 15, 8),
            Block.makeCuboidShape(9, 12.999999999999998, 7, 11, 14, 8)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    // South
    private static final VoxelShape Shape_S = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 2, 15),
            Block.makeCuboidShape(3, 2, 10, 4, 3, 11),
            Block.makeCuboidShape(3, 2, 7, 4, 3, 8),
            Block.makeCuboidShape(6, 2, 10, 7, 3, 11),
            Block.makeCuboidShape(6, 2, 7, 7, 3, 8),
            Block.makeCuboidShape(3, 3, 7, 7, 4, 11),
            Block.makeCuboidShape(3, 4, 7, 4, 6, 11),
            Block.makeCuboidShape(6, 4, 7, 7, 6, 11),
            Block.makeCuboidShape(4, 4, 10, 6, 6, 11),
            Block.makeCuboidShape(4, 4, 7, 6, 6, 8),
            Block.makeCuboidShape(4, 4, 8, 5, 5, 9),
            Block.makeCuboidShape(5, 4, 9, 6, 5, 10),
            Block.makeCuboidShape(10, 2, 8, 13, 3, 11),
            Block.makeCuboidShape(10, 6, 8, 13, 7, 11),
            Block.makeCuboidShape(10, 7, 7, 13, 8, 8),
            Block.makeCuboidShape(10, 7, 11, 13, 8, 12),
            Block.makeCuboidShape(9, 7, 8, 10, 8, 11),
            Block.makeCuboidShape(13, 7, 8, 14, 8, 11),
            Block.makeCuboidShape(10, 3, 7, 13, 6, 12),
            Block.makeCuboidShape(13, 3, 8, 14, 6, 11),
            Block.makeCuboidShape(9, 3, 8, 10, 6, 11),
            Block.makeCuboidShape(4, 7, 8, 6, 8, 10),
            Block.makeCuboidShape(4, 10, 8, 6, 11, 10),
            Block.makeCuboidShape(4, 11, 10, 6, 12, 11),
            Block.makeCuboidShape(6, 11, 8, 7, 12, 10),
            Block.makeCuboidShape(3, 11, 8, 4, 12, 10),
            Block.makeCuboidShape(4, 11, 7, 6, 12, 8),
            Block.makeCuboidShape(4, 8, 7, 6, 10, 11),
            Block.makeCuboidShape(6, 8, 8, 7, 10, 10),
            Block.makeCuboidShape(3, 8, 8, 4, 10, 10),
            Block.makeCuboidShape(5, 11, 9, 6, 14, 10),
            Block.makeCuboidShape(11, 7, 9, 12, 14, 10),
            Block.makeCuboidShape(5, 14, 9, 11.999999999999998, 15, 10),
            Block.makeCuboidShape(8, 2, 5, 9, 15, 6),
            Block.makeCuboidShape(8, 14.999999999999998, 5, 9, 16, 11),
            Block.makeCuboidShape(8, 13.999999999999998, 10, 9, 15, 11),
            Block.makeCuboidShape(8, 12.999999999999998, 9, 9, 14, 11)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    // West
    private static final VoxelShape Shape_W = Stream.of(
            Block.makeCuboidShape(1, 0, 1, 15, 2, 15),
            Block.makeCuboidShape(5, 2, 3, 6, 3, 4),
            Block.makeCuboidShape(8, 2, 3, 9, 3, 4),
            Block.makeCuboidShape(5, 2, 6, 6, 3, 7),
            Block.makeCuboidShape(8, 2, 6, 9, 3, 7),
            Block.makeCuboidShape(5, 3, 3, 9, 4, 7),
            Block.makeCuboidShape(5, 4, 3, 9, 6, 4),
            Block.makeCuboidShape(5, 4, 6, 9, 6, 7),
            Block.makeCuboidShape(5, 4, 4, 6, 6, 6),
            Block.makeCuboidShape(8, 4, 4, 9, 6, 6),
            Block.makeCuboidShape(7, 4, 4, 8, 5, 5),
            Block.makeCuboidShape(6, 4, 5, 7, 5, 6),
            Block.makeCuboidShape(5, 2, 10, 8, 3, 13),
            Block.makeCuboidShape(5, 6, 10, 8, 7, 13),
            Block.makeCuboidShape(8, 7, 10, 9, 8, 13),
            Block.makeCuboidShape(4, 7, 10, 5, 8, 13),
            Block.makeCuboidShape(5, 7, 9, 8, 8, 10),
            Block.makeCuboidShape(5, 7, 13, 8, 8, 14),
            Block.makeCuboidShape(4, 3, 10, 9, 6, 13),
            Block.makeCuboidShape(5, 3, 13, 8, 6, 14),
            Block.makeCuboidShape(5, 3, 9, 8, 6, 10),
            Block.makeCuboidShape(6, 7, 4, 8, 8, 6),
            Block.makeCuboidShape(6, 10, 4, 8, 11, 6),
            Block.makeCuboidShape(5, 11, 4, 6, 12, 6),
            Block.makeCuboidShape(6, 11, 6, 8, 12, 7),
            Block.makeCuboidShape(6, 11, 3, 8, 12, 4),
            Block.makeCuboidShape(8, 11, 4, 9, 12, 6),
            Block.makeCuboidShape(5, 8, 4, 9, 10, 6),
            Block.makeCuboidShape(6, 8, 6, 8, 10, 7),
            Block.makeCuboidShape(6, 8, 3, 8, 10, 4),
            Block.makeCuboidShape(6, 11, 5, 7, 14, 6),
            Block.makeCuboidShape(6, 7, 11, 7, 14, 12),
            Block.makeCuboidShape(6, 14, 5, 7, 15, 11.999999999999998),
            Block.makeCuboidShape(10, 2, 8, 11, 15, 9),
            Block.makeCuboidShape(5, 14.999999999999998, 8, 11, 16, 9),
            Block.makeCuboidShape(5, 13.999999999999998, 8, 6, 15, 9),
            Block.makeCuboidShape(5, 12.999999999999998, 8, 7, 14, 9)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();



    public OilPress() {
        super(Block.Properties.create(Material.WOOD)
            .hardnessAndResistance(2.0f,2.2f)
            .sound(SoundType.WOOD)
            .harvestLevel(0)
            .harvestTool(ToolType.AXE)
            .setRequiresTool()
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(FACING)){
            case NORTH: return Shape_S;
            case EAST: return Shape_W;
            case SOUTH: return Shape_N;
            case WEST: return Shape_E;
            default: return Shape_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context){
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot){
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }

    /*@Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos){
        return 0.0f;
    }

     */
}
