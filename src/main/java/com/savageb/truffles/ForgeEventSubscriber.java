package com.savageb.truffles;

import com.savageb.truffles.config.TruffleConfig;
import com.savageb.truffles.entities.FindBlockGoal;
import com.savageb.truffles.entities.FindTruffleGoal;
import com.savageb.truffles.init.ModItems;
import com.savageb.truffles.world.gen.BiomeGen;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onPigSpawn(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof PigEntity){
            PigEntity pig = (PigEntity)event.getEntity();
            //System.out.println("Adding Pig AI!");
            pig.goalSelector.addGoal(4, new TemptGoal(pig, 1.2D, Ingredient.fromItems(ModItems.TRUFFLE.get()), false));
            pig.goalSelector.addGoal(2,  new FindTruffleGoal(pig, 1.2D, TruffleConfig.pigSearchRadius, 2f));
            pig.goalSelector.addGoal(2,  new FindBlockGoal(Blocks.BEE_NEST,pig, 1.2D, TruffleConfig.pigSearchRadius, 2f));
        }
    }



}
