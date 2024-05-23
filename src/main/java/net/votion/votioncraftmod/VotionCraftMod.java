package net.votion.votioncraftmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.votion.votioncraftmod.block.ModBlocks;
import net.votion.votioncraftmod.block.custom.EnhancementStation;
import net.votion.votioncraftmod.block.entity.ModBlockEntities;
import net.votion.votioncraftmod.item.ModCreativeModTabs;
import net.votion.votioncraftmod.item.ModItems;
import net.votion.votioncraftmod.screen.ArtificerTableScreen;
import net.votion.votioncraftmod.screen.EnhancementStationScreen;
import net.votion.votioncraftmod.screen.ModMenuTypes;
import org.slf4j.Logger;

import javax.swing.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VotionCraftMod.MOD_ID)
public class VotionCraftMod {
    public static final String MOD_ID = "votioncraftmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public VotionCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        ModMenuTypes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.PowerOrb);
            event.accept(ModItems.BasicGear);
            event.accept(ModItems.OrbThrower);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(ModMenuTypes.ARTIFICER_TABLE_MENU.get(), ArtificerTableScreen::new);
            MenuScreens.register(ModMenuTypes.ENHANCEMENT_STATION_MENU.get(), EnhancementStationScreen::new);

        }
    }
}