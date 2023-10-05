package dataEstructures;

public interface INode<T> {
    public T getdata();

    public void setData(T value);

    public INode<T> getNext();

    public void setNext(INode<T> next);
}