package net.syamil.tutorialmod.blockentity;
import net.syamil.tutorialmod.utils.BinarySearchTree;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Comparator;

public class SortingChestBlockEntity extends BlockEntity {
    private BinarySearchTree<ItemStack> bst;

    public SortingChestBlockEntity(BlockEntityType<?> type) {
        super(type);
        bst = new BinarySearchTree<>(Comparator.comparing(ItemStack::getDisplayName));
    }

    public void addItem(ItemStack item) {
        bst.insert(item);
    }

    public ItemStack getItem(ItemStack item) {
        return bst.search(item);
    }
}

