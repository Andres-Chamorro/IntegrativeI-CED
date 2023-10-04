package model;

public class NodeHash<K, V> {
    public K key;
    public V value;
    public NodeHash<K, V> next;

    public NodeHash(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NodeHash<K, V> getNext() {
        return next;
    }

    public void setNext(NodeHash<K,V> next) {
        this.next = next;
    }

}