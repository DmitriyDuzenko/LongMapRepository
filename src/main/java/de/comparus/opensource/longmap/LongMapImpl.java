package de.comparus.opensource.longmap;

import java.util.LinkedList;

public class LongMapImpl<V> implements LongMap<V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private LinkedList<Node<V>>[] table;
    private int capacity;

    public LongMapImpl() {
        table = new LinkedList[DEFAULT_CAPACITY];
    }

    public V put(long key, V value) {
        if (capacity >= table.length * LOAD_FACTOR) {
            resizeTable();
        }
        int index = getNodeIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList();
        }
        for (Node<V> node : table[index]) {
            if (node.key == key) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        table[index].add(new Node<>(key, value));
        capacity++;
        return null;
    }

    public V get(long key) {
        int index = getNodeIndex(key);

        if (table[index] != null) {
            for (Node<V> node : table[index]) {
                if (node.key == key) {
                    return node.value;
                }
            }
        }
        return null;
    }

    public V remove(long key) {
        int index = getNodeIndex(key);

        if (table[index] != null) {
            for (Node<V> node : table[index]) {
                if (node.key == key) {
                    table[index].remove(node);
                    capacity--;
                    return node.value;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public boolean containsKey(long key) {
        int index = getNodeIndex(key);
        if (table[index] != null) {
            for (Node<V> node : table[index]) {
                if (node.key == key) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Node<V> node : table[i]) {
                    if (node.value.equals(value)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public long[] keys() {
        long[] keys = new long[capacity];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Node<V> node : table[i]) {
                    keys[index] = node.key;
                    index++;
                }
            }
        }
        return keys;
    }

    public V[] values() {
        V[] values = (V[]) new Object[capacity];
        int index = 0;

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Node<V> node : table[i]) {
                    values[index] = node.value;
                    index++;
                }
            }
        }
        return values;
    }

    public long size() {
        return capacity;
    }

    public void clear() {
        table = new LinkedList[DEFAULT_CAPACITY];
        capacity = 0;
    }

    private int getNodeIndex(long key) {
        return (int) (key & (table.length - 1));
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        LinkedList<Node<V>>[] newTable = new LinkedList[newCapacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Node<V> node : table[i]) {
                    int index = (int) (node.key % newCapacity);
                    if (newTable[index] == null) {
                        newTable[index] = new LinkedList<>();
                    }
                    newTable[index].add(node);
                }
            }
        }
        table = newTable;
    }

    private static class Node<V> {

        private final long key;
        private V value;


        public Node(long key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
