package net.syamil.tutorialmod.utils;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BinarySearchTree<T> {
    private class Node {
        T item;
        Node left;
        Node right;

        Node(T item) {
            this.item = item;
        }
    }

    private Node root;
    private Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T item) {
        root = insertRecursive(root, item);
    }

    private Node insertRecursive(Node node, T item) {
        if (node == null) {
            return new Node(item);
        }
        if (comparator.compare(item, node.item) < 0) {
            node.left = insertRecursive(node.left, item);
        } else {
            node.right = insertRecursive(node.right, item);
        }
        return node;
    }

    public T search(T item) {
        return searchRecursive(root, item);
    }

    private T searchRecursive(Node node, T item) {
        if (node == null || comparator.compare(item, node.item) == 0) {
            return node != null ? node.item : null;
        }
        if (comparator.compare(item, node.item) < 0) {
            return searchRecursive(node.left, item);
        } else {
            return searchRecursive(node.right, item);
        }
    }
}

