package model;

public class Node<K, V> implements INode<K, V> {
    private K key;
    private V value;
    private INode<K, V> next;

    // Constructor para la pila
    public Node(V value) {
        this(null, value, null);
    }

    // Constructor para la tabla hash
    public Node(K key, V value) {
        this(key, value, null);
    }

    // Constructor general
    public Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    // Métodos getter y setter
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public INode<K, V> getNext() {
        return next;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(INode<K, V> next) {
        this.next = next;
    }
}