package dev.su5ed.mffs.util;

import dev.su5ed.mffs.api.fortron.FortronStorage;
import dev.su5ed.mffs.setup.ModFluids;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FortronStorageImpl implements FortronStorage {
    private final BlockEntity owner;
    private final FluidTank fortronTank;
    private final Runnable onContentsChanged;

    private int frequency;

    public FortronStorageImpl(BlockEntity owner, int capacity, Runnable onContentsChanged) {
        this.owner = owner;
        this.fortronTank = new FortronFluidTank(capacity);
        this.onContentsChanged = onContentsChanged;
    }

    public FluidTank getFortronTank() {
        return this.fortronTank;
    }

    public void setCapacity(int capacity) {
        this.fortronTank.setCapacity(capacity);
        if (!this.fortronTank.isEmpty()) {
            this.fortronTank.getFluid().setAmount(Math.min(this.fortronTank.getFluidAmount(), capacity));
        }
    }

    @Override
    public BlockEntity getOwner() {
        return this.owner;
    }

    @Override
    public int getStoredFortron() {
        return this.fortronTank.getFluidAmount();
    }

    @Override
    public void setStoredFortron(int energy) {
        this.fortronTank.setFluid(Fortron.getFortron(energy));
    }

    @Override
    public int getFortronCapacity() {
        return this.fortronTank.getCapacity();
    }

    @Override
    public int extractFortron(int joules, boolean simulate) {
        return this.fortronTank.drain(joules, simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE).getAmount();
    }

    @Override
    public int insertFortron(int joules, boolean simulate) {
        return this.fortronTank.fill(Fortron.getFortron(joules), simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("fortronTank", this.fortronTank.writeToNBT(new CompoundTag()));
        tag.putInt("frequency", this.frequency);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.fortronTank.readFromNBT(nbt.getCompound("fortronTank"));
        this.frequency = nbt.getInt("frequency");
    }

    @Override
    public int getFrequency() {
        return this.frequency;
    }

    @Override
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    private class FortronFluidTank extends FluidTank {
        public FortronFluidTank(int capacity) {
            super(capacity);
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == ModFluids.FORTRON_FLUID.get();
        }

        @Override
        protected void onContentsChanged() {
            FortronStorageImpl.this.onContentsChanged.run();
        }
    }
}
