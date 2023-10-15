package dataEstructures;

import customExceptions.EmptyQueueException;

public class Queue<T> implements IQueue<T> {

    public Node<T> front;
    public Node<T> rear;
    public int size;

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
        if (data != null) {
            Node<T> newNode = new Node<>(data);
            if (isEmpty()) {
                front = newNode;
            } else {
                rear.setNext(newNode);
            }
            rear = newNode;
            size++;
        }
    }

    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("No se puede hacer dequeue en una cola vacía.");
        }
        T data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    public T front() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("No se puede obtener el frente de una cola vacía.");
        }
        return front.getData();
    }

}