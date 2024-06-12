package net.syamil.tutorialmod;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.MenuProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;
import net.syamil.tutorialmod.container.PotionSatchelContainer;
import net.syamil.tutorialmod.item.PotionSatchelItem;
import net.syamil.tutorialmod.inventory.PotionSatchelInventory;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,TutorialMod.MOD_ID);

   public static final RegistryObject<MenuType<PotionSatchelContainer>> POTION_SATCHEL_CONTAINER =
           CONTAINERS.register("potion_satchel_container",()-> IForgeMenuType.create((windowId, inv, data) -> new PotionSatchelContainer(windowId,inv,new PotionSatchelInventory()))); //PotionSatchelInventory constructor need to have 3 parameter (look at PotionSatchelInventory class)

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}