package net.syamil.tutorialmod.inventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class PotionSatchelInventory extends BlockEntity implements IItemHandler {

    private final ItemStack[] slots;

    public PotionSatchelInventory(BlockEntityType<?> type) {
        super(type);
        this.slots = new ItemStack[9]; // 9 slots for potion storage
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i].isEmpty()) {
                compound.put(String.valueOf(i), slots[i].serializeNBT());
            }
        }
        return compound;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        for (int i = 0; i < slots.length; i++) {
            if (compound.contains(String.valueOf(i), net.minecraft.nbt.Constants.NBT.TAG_COMPOUND)) {
                slots[i] = ItemStack.of(compound.getCompound(String.valueOf(i)));
            } else {
                slots[i] = ItemStack.EMPTY;
            }
        }
    }

    @Override
    public int getContainerSize() {
        return slots.length;
    }

    @Override
    public ItemStack getItem(int slot) {
        return slots[slot];
    }

    @Override
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

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stack = slots[slot];
        slots[slot] = ItemStack.EMPTY;
        return stack;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        slots[slot] = stack;
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
    }

    @Override
    public boolean stillValid(net.minecraftforge.common.util.LazyOptional<?> holder) {
        return true;
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = ItemStack.EMPTY;
        }
    }

    @Override
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

    public IItemHandler getItemHandler(Level world, int x, int y, int z) {
        return new InvWrapper(this);
    }
}
