//package net.syamil.tutorialmod.registry;
//
//import net.minecraft.world.inventory.MenuType;
//import net.minecraftforge.common.extensions.IForgeMenuType;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//import net.syamil.tutorialmod.container.PotionBagContainer;
//
//@Mod.EventBusSubscriber(modid = "tutorialmod", bus = Mod.EventBusSubscriber.Bus.MOD)
//public class ModContainers {
//
//    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.HOLDER_SET_TYPES, "tutorialmod");
//
//    public static final RegistryObject<MenuType<PotionBagContainer>> POTION_BAG = CONTAINERS.register("potion_bag",
//            () -> IForgeMenuType.create(PotionBagContainer::new));
//
//    public static void register(IEventBus eventBus) {
//        CONTAINERS.register(eventBus);
//    }
//}
