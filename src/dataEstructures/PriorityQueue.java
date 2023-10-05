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

    public boolean removeElement(T element) {
        boolean valid;
        if (element != null) {
            Queue<T> temp = new Queue<>();

            while (!isEmpty()) {
                T currentItem = dequeue();

                if (!currentItem.equals(element)) {
                    temp.enqueue(currentItem);
                }
            }

            while (!temp.isEmpty()) {
                enqueue(temp.dequeue());
            }

            valid = !isEmpty();
        } else {
            valid = false;
        }
        return valid;
    }

}
