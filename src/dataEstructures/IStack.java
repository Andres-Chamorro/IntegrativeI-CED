package dataEstructures;

public interface IStack<T> {
    public boolean isEmpty();

    public void push(T data);

    public T pop();

    public T front();
}