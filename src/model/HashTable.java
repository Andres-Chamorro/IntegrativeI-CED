package model;

public class HashTable<K, V> implements IHash<K, V> {
    private int size;
    private Node<K, V>[] tabla;

    public HashTable(int size) {
        this.size = size;
        tabla = new Node[size];
    }

    public void put(K key, V value) {
        int hash = getHash(key);
        Node<K, V> node = new Node<>(key, value);

        if (tabla[hash] == null) {
            tabla[hash] = node;
        } else {
            Node<K, V> current = tabla[hash];
            while (current.getNext() != null) {
                current = (Node<K, V>) current.getNext();
            }
            current.setNext(node);
        }
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        if (tabla[hash] != null) {
            Node<K, V> temp = tabla[hash];

            while (temp != null) {
                if (temp.getKey().equals(key)) {
                    return temp.getValue();
                }
                temp = (Node<K, V>) temp.getNext();
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
            Node<K, V> temp = tabla[hash];
            while (temp != null) {
                if (temp.getKey().equals(key))
                    return true;
                temp = (Node<K, V>) temp.getNext();
            }
            return false;
        }
    }

    @Override
    public void remove(K key) {
        int hash = getHash(key);
        if (tabla[hash] != null) {
            Node<K, V> prevEntry = null;
            Node<K, V> entry = tabla[hash];

            while (entry.getNext() != null && !entry.getKey().equals(key)) {
                prevEntry = entry;
                entry = (Node<K, V>) entry.getNext();
            }
            if (entry.getKey().equals(key)) {
                if (prevEntry == null)
                    tabla[hash] = (Node<K, V>) entry.getNext();
                else
                    prevEntry.setNext(entry.getNext());
            }
        }
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

}