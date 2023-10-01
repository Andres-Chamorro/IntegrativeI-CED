package model;

import customExceptions.EmptyQueueException;

public class Queue<T> implements IQueue<T> {

    private INode<T, T> front;
    private INode<T, T> rear;
    private int size;

    // Constructor
    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T data) {
        Node<T, T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
            ;
        }
        rear = newNode;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("No se puede hacer dequeue en una cola vacía.");
        }
        T data = front.getValue();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    public T front() {
        if (isEmpty()) {
            throw new EmptyQueueException("No se puede obtener el frente de una pila vacía.");
        }
        return front.getValue();
    }

}