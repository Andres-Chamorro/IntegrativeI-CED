package model;

import customExceptions.EmptyStackException;

public class Stack<T> implements IStack<T> {

    private INode<T, T> top;
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
        Node<T, T> newNode = new Node<>(null, data);
        newNode.setNext(top);
        ;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException("No se puede hacer pop en una pila vacía.");
        }
        T data = top.getValue();
        top = top.getNext();
        size--;
        return data;
    }

    public T front() {
        if (isEmpty()) {
            throw new EmptyStackException("No se puede obtener el frente de una pila vacía.");
        }
        return top.getValue();
    }

}
