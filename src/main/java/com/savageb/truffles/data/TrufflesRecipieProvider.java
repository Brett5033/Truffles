package com.savageb.truffles.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class TrufflesRecipieProvider extends RecipeProvider implements IConditionBuilder {
    public TrufflesRecipieProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
}
