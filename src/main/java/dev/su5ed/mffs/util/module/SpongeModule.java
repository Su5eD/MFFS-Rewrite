package dev.su5ed.mffs.util.module;

import dev.su5ed.mffs.api.Projector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.Set;

public class SpongeModule extends ModuleBase {

    public SpongeModule() {
        super(1);
    }

    @Override
    public boolean beforeProject(Projector projector, Set<BlockPos> field) {
        if (projector.getTicks() % 60 == 0) {
            Level level = ((BlockEntity) projector).getLevel();

            for (BlockPos pos : projector.getInteriorPoints()) {
                Block block = level.getBlockState(pos).getBlock();

                if (block instanceof LiquidBlock || block instanceof IFluidBlock) {
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }
        return super.beforeProject(projector, field);
    }
}
