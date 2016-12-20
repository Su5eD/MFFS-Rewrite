package com.mffs.common.items.modules.interdiction;

import com.builtbroken.mc.core.registry.implement.IRecipeContainer;
import com.mffs.common.items.modules.BaseModule;
import com.mffs.common.items.modules.MatrixModule;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;

import java.util.List;

/**
 * @author Calclavia
 */
public class ItemModuleBlockAlter extends MatrixModule implements IRecipeContainer {

    @Override
    public void genRecipes(List<IRecipe> list) {
        list.add(newShapedRecipe(this,
                " G ", "GFG", " G ",
                'G', Blocks.gold_block,
                'F', Item.itemRegistry.getObject("mffs:moduleBlockAccess")));
    }

    /**
     * Constructor.
     */
    public ItemModuleBlockAlter() {
        setMaxStackSize(1);
        setCost(15F);
    }
}
