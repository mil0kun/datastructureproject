package net.syamil.tutorialmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

import static net.syamil.tutorialmod.TutorialMod.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final RegistryObject<Item> SAPPHIRE =ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POTION_BAG = ITEMS.register("potion_bag",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SORTING_CHEST_ITEM = ITEMS.register("potion_bag",
            ()-> new Item(new Item.Properties()));
//    public static final RegistryObject<Block> SORTING_CHEST_BLOCK = BLOCKS.register("sorting_chest",
//            ()-> new Block(new BlockBehaviour.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
