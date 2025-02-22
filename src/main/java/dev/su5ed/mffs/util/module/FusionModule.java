package dev.su5ed.mffs.util.module;

import dev.su5ed.mffs.api.FrequencyBlock;
import dev.su5ed.mffs.api.Projector;
import dev.su5ed.mffs.api.fortron.FortronStorage;
import dev.su5ed.mffs.setup.ModCapabilities;
import dev.su5ed.mffs.util.FrequencyGrid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Set;

public class FusionModule extends ModuleBase {

    public FusionModule() {
        super(1);
    }

    @Override
    public boolean beforeProject(Projector projector, Set<BlockPos> field) {
        int frequency = ((BlockEntity) projector).getCapability(ModCapabilities.FORTRON)
            .map(FrequencyBlock::getFrequency)
            .orElseThrow();
        Set<FortronStorage> machines = FrequencyGrid.instance().get(frequency);

        for (FortronStorage storage : machines) {
            storage.getOwner().getCapability(ModCapabilities.PROJECTOR)
                .filter(compareProjector ->  compareProjector != projector
                    && ((BlockEntity) compareProjector).getLevel() == ((BlockEntity) projector).getLevel()
                    && compareProjector.isActive() && compareProjector.getMode().isPresent())
                .ifPresent(compareProjector -> field.removeIf(pos -> compareProjector.getMode().orElseThrow().isInField(compareProjector, pos)));
        }
        return super.beforeProject(projector, field);
    }
}
