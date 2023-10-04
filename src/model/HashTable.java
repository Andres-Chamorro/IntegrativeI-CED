package model;

public class HashTable<K, V> implements IHash<K, V> {
    private int size;
    private NodeHash<K, V>[] tabla;

    public HashTable(int size) {
        this.size = size;
        tabla = new NodeHash[size];
    }

    public void put(K key, V value) {
        int hash = getHash(key);
        NodeHash<K, V> node = new NodeHash<>(key, value);

        if (tabla[hash] == null) {
            tabla[hash] = node;
        } else {
            NodeHash<K, V> current = tabla[hash];
            while (current.getNext() != null) {
                current = (NodeHash<K, V>) current.getNext();
            }
            current.setNext(node);
        }
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        if (tabla[hash] != null) {
            NodeHash<K, V> temp = tabla[hash];

            while (temp != null) {
                if (temp.getKey().equals(key)) {
                    return temp.getValue();
                }
                temp = (NodeHash<K, V>) temp.getNext();
            }
        }
        return null; // returns null if the key is not found.
    }

    @Override
    public boolean containsKey(K key) {
        int hash = getHash(key);
        if (tabla[hash] == null)
            return false;
        else {
            NodeHash<K, V> temp = tabla[hash];
            while (temp != null) {
                if (temp.getKey().equals(key))
                    return true;
                temp = (NodeHash<K, V>) temp.getNext();
            }
            return false;
        }
    }

    @Override
    public void remove(K key) {
        int hash = getHash(key);
        if (tabla[hash] != null) {
            NodeHash<K, V> prevEntry = null;
            NodeHash<K, V> entry = tabla[hash];

            while (entry.getNext() != null && !entry.getKey().equals(key)) {
                prevEntry = entry;
                entry = (NodeHash<K, V>) entry.getNext();
            }
            if (entry.getKey().equals(key)) {
                if (prevEntry == null)
                    tabla[hash] = (NodeHash<K, V>) entry.getNext();
                else
                    prevEntry.setNext(entry.getNext());
            }
        }
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public NodeHash<K, V>[] getTabla() {
        return this.tabla;
    }

    public void setTabla(NodeHash<K, V>[] tabla) {
        this.tabla = tabla;
    }

    /*
     * public Node<K, V> getBucket(int index) {
     * if (index < 0 || index >= size) {
     * throw new IndexOutOfBoundsException("Índice fuera de rango.");
     * }
     * return tabla[index];
     * }
     */

}