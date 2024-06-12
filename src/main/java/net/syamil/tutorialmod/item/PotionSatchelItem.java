package net.syamil.tutorialmod.item;
import net.syamil.tutorialmod.utils.SinglyLinkedList;
import net.syamil.tutorialmod.container.PotionSatchelContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class PotionSatchelItem extends Item {

    private final SinglyLinkedList<MobEffectInstance> potionList;

    public PotionSatchelItem(Properties properties) {
        super(properties);
        this.potionList = new SinglyLinkedList<>();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            if (!potionList.isEmpty()) {
                MobEffectInstance effect = potionList.poll();
                player.addEffect(effect);
                return InteractionResultHolder.success(player.getItemInHand(hand));
            } else {
                NetworkHooks.openScreen((ServerPlayer) player, getMenuProvider(player.getItemInHand(hand)), buf -> buf.writeItem(player.getItemInHand(hand)));
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private MenuProvider getMenuProvider(ItemStack stack) {
        return new SimpleMenuProvider((id, inventory, player) -> new PotionSatchelContainer(id, inventory, stack), Component.literal("Potion Satchel"));
    }

    public void addPotion(MobEffectInstance effect) {
        potionList.add(effect);
    }
}
