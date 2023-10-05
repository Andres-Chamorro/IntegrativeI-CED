package dataEstructures;

public class PriorityQueue<T extends Comparable<T>> extends Queue<T> {

    public PriorityQueue() {
        super();
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty() || data.compareTo(front.getData()) < 0) {

            newNode.setNext(front);
            front = newNode;
        } else {
            Node<T> current = front;

            while (current.getNext() != null &&
                    data.compareTo(current.getNext().getData()) >= 0) {
                current = current.getNext();
            }

            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }

        size++;
    }

}
