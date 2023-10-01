package model;

public interface INode<K, V> {
    public K getKey();

    public V getValue();

    public void setValue(V value);

    public INode<K, V> getNext();

    public void setNext(INode<K, V> next);
}