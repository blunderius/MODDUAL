package net.votion.votioncraftmod.item;

import net.votion.votioncraftmod.VotionCraftMod;
import net.votion.votioncraftmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VotionCraftMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PowerOrb.get()))
                    .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PowerOrb.get());
                        pOutput.accept(ModItems.BasicGear.get());
                        pOutput.accept(ModItems.OrbThrower.get());

                        pOutput.accept(ModBlocks.SCRAP_BLOCK.get());
                        pOutput.accept(ModBlocks.ARTIFICER_TABLE.get());
                        pOutput.accept(ModBlocks.ENHANCEMENT_STATION.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}