package dev.su5ed.mffs.api.fortron;

import net.minecraftforge.fluids.capability.IFluidHandler;

public interface FortronStorage {
    /**
     * Sets the amount of fortron energy.
     *
     * @param joules
     */
	void setFortronEnergy(int joules);

    /**
     * @return Gets the amount of fortron stored.
     */
    int getFortronEnergy();

    /**
     * @return Gets the maximum possible amount of fortron that can be stored.
     */
    int getFortronCapacity();

    /**
     * Called to use and consume fortron energy from this storage unit.
     *
     * @param joules - Amount of fortron energy to use.
     * @param action
     * @return joules - The amount of energy that was actually provided.
     */
    int requestFortron(int joules, IFluidHandler.FluidAction action);

    /**
     * Called to use and give fortron energy from this storage unit.
     *
     * @param joules - Amount of fortron energy to give.
     * @param action
     * @return joules - The amount of energy that was actually injected.
     */
    int provideFortron(int joules, IFluidHandler.FluidAction action);
}
