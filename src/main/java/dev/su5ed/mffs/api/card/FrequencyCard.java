package dev.su5ed.mffs.api.card;

import net.minecraft.world.item.ItemStack;

public interface FrequencyCard {
    int getFrequency(ItemStack stack);

    void setFrequency(ItemStack stack, int frequency);
}
