package model;

public class Stack<T> implements IStack<T> {

    private Node<T> top;
    private int size;

    // Constructor
    public Stack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return top.data;
    }

}
