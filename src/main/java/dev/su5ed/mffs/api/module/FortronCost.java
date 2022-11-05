package dev.su5ed.mffs.api.module;

/**
 * @author Calclavia
 */
public interface FortronCost {
    /**
     * The amount of Fortron this module consumes per tick.
     */
    public float getFortronCost(float amplifier);
}
