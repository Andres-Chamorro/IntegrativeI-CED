package dataEstructures;

public class PriorityQueue<T extends Comparable<T>> extends Queue<T> {
    private Heap<T> heap;

    public PriorityQueue() {
        heap = new Heap<>();
    }

    public void enqueue(T item) {
        heap.insert(item);
    }

    public T dequeue() {
        return heap.extractMin();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public boolean removeElement(T element) {
        if (element == null) {
            return false;
        }
        Heap<T> tempHeap = new Heap<>();

        while (!isEmpty()) {
            T currentItem = dequeue();

            if (!currentItem.equals(element)) {
                tempHeap.insert(currentItem);
            }
        }

        heap = tempHeap;

        return !isEmpty();
    }

}
