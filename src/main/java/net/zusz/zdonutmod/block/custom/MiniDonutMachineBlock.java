package net.zusz.zdonutmod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zusz.zdonutmod.block.entity.MiniDonutMachineBlockEntity;
import org.jetbrains.annotations.Nullable;
import net.zusz.zdonutmod.block.entity.ModBlockEntities;


    public class MiniDonutMachineBlock extends BaseEntityBlock {

        public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
        public static final MapCodec<MiniDonutMachineBlock> CODEC = simpleCodec(MiniDonutMachineBlock::new);

        public MiniDonutMachineBlock(Properties properties) {
            super(properties);
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Override
        protected MapCodec<? extends BaseEntityBlock> codec() {
            return CODEC;
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(FACING);
        }

        @Nullable
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }

        @Override
        public BlockState rotate(BlockState state, Rotation rotation) {
            return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
        }

        @Override
        public BlockState mirror(BlockState state, Mirror mirror) {
            return state.rotate(mirror.getRotation(state.getValue(FACING)));
        }

        @Override
        public RenderShape getRenderShape(BlockState state) {
            return RenderShape.MODEL;
        }

        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new MiniDonutMachineBlockEntity(pos, state);
        }

        @Override
        public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
            if (state.getBlock() != newState.getBlock()) {
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof MiniDonutMachineBlockEntity donutMachineBlockEntity) {
                    donutMachineBlockEntity.drops();
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }

        @Override
        protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                                  Player player, InteractionHand hand, BlockHitResult hitResult) {
            if (!level.isClientSide()) {
                BlockEntity entity = level.getBlockEntity(pos);
                if (entity instanceof MiniDonutMachineBlockEntity donutMachineBlockEntity) {
                    ((ServerPlayer) player).openMenu(
                            new SimpleMenuProvider(donutMachineBlockEntity, Component.literal("Donut Machine")), pos
                    );
                } else {
                    throw new IllegalStateException("Missing Mini Donut Machine Block Entity!");
                }
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        @Nullable
        @Override
        public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
            if (level.isClientSide()) return null;

            return createTickerHelper(type, ModBlockEntities.MINI_DONUT_MACHINE_BE.get(),
                    (lvl, pos, st, be) -> be.tick(lvl, pos, st));
        }

        @Override
        public boolean isOcclusionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
            return false;
        }

        @Override
        public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
            return false;
        }

        @Override
        public boolean useShapeForLightOcclusion(BlockState state) {
            return true;
        }

        private static final VoxelShape MINI_DONUT_MACHINE_SHAPE = Block.box(2, 0, 2, 14, 8, 14);

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            Direction facing = state.getValue(FACING);
            return MINI_DONUT_MACHINE_SHAPE;
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
            Direction facing = state.getValue(FACING);
            return MINI_DONUT_MACHINE_SHAPE;
        }
    }