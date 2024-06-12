package net.syamil.tutorialmod.inventory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

public class PotionSatchelInventory extends BlockEntity implements IItemHandler {

    private final ItemStack[] slots;

    public PotionSatchelInventory(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState); // Call the constructor of the superclass with the provided parameters
        this.slots = new ItemStack[9]; // Initialize the slots array
    }


    public CompoundTag save(CompoundTag compound) {
        super.saveToItem(ItemStack.of(compound));
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i].isEmpty()) {
                compound.put(String.valueOf(i), slots[i].serializeNBT());
            }
        }
        return compound;
    }

    @Override
    public void load(@NotNull CompoundTag compound) {
        super.load(compound);
        for (int i = 0; i < slots.length; i++) {
            if (compound.contains(String.valueOf(i), 10)) {
                slots[i] = ItemStack.of(compound.getCompound(String.valueOf(i)));
            } else {
                slots[i] = ItemStack.EMPTY;
            }
        }
    }

    public int getContainerSize() {
        return slots.length;
    }

    public ItemStack getItem(int slot) {
        return slots[slot];
    }

    public ItemStack removeItem(int slot, int amount) {
        if (slots[slot].getCount() <= amount) {
            ItemStack stack = slots[slot];
            slots[slot] = ItemStack.EMPTY;
            setChanged();
            return stack;
        }
        ItemStack split = slots[slot].split(amount);
        if (slots[slot].getCount() == 0) {
            slots[slot] = ItemStack.EMPTY;
        }
        setChanged();
        return split;
    }

    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stack = slots[slot];
        slots[slot] = ItemStack.EMPTY;
        return stack;
    }

    public void setItem(int slot, ItemStack stack) {
        slots[slot] = stack;
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
    }

    public boolean stillValid(net.minecraftforge.common.util.LazyOptional<?> holder) {
        return true;
    }

    public void clearContent() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = ItemStack.EMPTY;
        }
    }

    public int getMaxStackSize() {
        return 64; // Maximum stack size for potions
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (getLevel() != null && !getLevel().isClientSide) {
            setChanged();
        }
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return null;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return false;
    }
}
