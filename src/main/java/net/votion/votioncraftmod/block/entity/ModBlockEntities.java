package net.votion.votioncraftmod.block.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.votion.votioncraftmod.VotionCraftMod;
import net.votion.votioncraftmod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VotionCraftMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ArtificerTableEntity>> ARTIFICER_TABLE_BE =
            BLOCK_ENTITIES.register("artificer_table_be", () ->
                    BlockEntityType.Builder.of(ArtificerTableEntity::new,
                            ModBlocks.ARTIFICER_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<EnhancementStationEntity>> ENHANCEMENT_STATION_BE =
            BLOCK_ENTITIES.register("enhancement_station_be", () ->
                    BlockEntityType.Builder.of(EnhancementStationEntity::new,
                            ModBlocks.ENHANCEMENT_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
