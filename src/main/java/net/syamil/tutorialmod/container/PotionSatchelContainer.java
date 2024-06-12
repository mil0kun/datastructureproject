package net.syamil.tutorialmod.container;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.syamil.tutorialmod.ModContainers;
import net.syamil.tutorialmod.item.PotionSatchelItem;

public class PotionSatchelContainer extends AbstractContainerMenu {
    private final ItemStack satchelStack;
    private final int potionSlots = 9; // Define the number of slots for the potions

    public PotionSatchelContainer(int id, Inventory playerInventory, ItemStack satchelStack) {
        super(ModContainers.POTION_SATCHEL_CONTAINER.get(), id);
        this.satchelStack = satchelStack;

        // Potion Satchel Inventory
        for (int i = 0; i < potionSlots; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 18) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.getItem() == Items.POTION;
                }

                @Override
                public void setChanged() {
                    super.setChanged();
                    if (!this.getItem().isEmpty()) {
                        ((PotionSatchelItem) satchelStack.getItem()).addPotion(MobEffectInstance.load(this.getItem().getOrCreateTag()));
                    }
                }
            });
        }

        // Player Inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 50 + y * 18));
            }
        }

        // Player Hotbar
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 108));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public static PotionSatchelContainer createContainerServerSide(int id, Inventory playerInventory, ItemStack satchelStack) {
        return new PotionSatchelContainer(id, playerInventory, satchelStack);
    }

    public static PotionSatchelContainer createContainerClientSide(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
        ItemStack satchelStack = extraData.readItem();
        return new PotionSatchelContainer(id, playerInventory, satchelStack);
    }
}