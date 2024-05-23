package net.votion.votioncraftmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.votion.votioncraftmod.VotionCraftMod;
import net.votion.votioncraftmod.item.custom.OrbThrowerItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VotionCraftMod.MOD_ID);

    public static final RegistryObject<Item> PowerOrb = ITEMS.register("powerorb",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BasicGear = ITEMS.register("basicgear",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OrbThrower = ITEMS.register("orbthrower",
            () -> new OrbThrowerItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

