package model;

import org.junit.jupiter.api.Test;

import customExceptions.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import dataEstructures.PriorityQueue;

public class PriorityQueueTest {

    private PriorityQueue<Integer> priorityQueue;

    @BeforeEach
    public void setUp() {
        priorityQueue = new PriorityQueue<>();
    }

    @Test
    public void testEnqueueStandard() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueueLimit() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testDequeueStandard() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.enqueue(1);
        assertEquals(1, queue.dequeue());
    }

    @Test
    public void testDequeueLimit() throws EmptyQueueException {
        for (int i = 1; i <= 1000; i++) {
            priorityQueue.enqueue(i);
        }
        for (int i = 1; i <= 1000; i++) {
            assertEquals(Integer.valueOf(i), priorityQueue.dequeue());
        }

        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    void testRemoveElementStandard() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        assertFalse(queue.removeElement(1));
    }

    @Test
    public void testRemoveElementLimit() {
        for (int i = 1; i <= 1000; i++) {
            priorityQueue.enqueue(i);
        }

        for (int i = 1; i <= 1000; i++) {
            priorityQueue.removeElement(i);
        }

        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void testIsEmptyStandard() {
        assertTrue(priorityQueue.isEmpty());

        priorityQueue.enqueue(5);
        assertFalse(priorityQueue.isEmpty());

        priorityQueue.dequeue();
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void testIsEmptyLimit() {
        for (int i = 1; i <= 1000; i++) {
            priorityQueue.enqueue(i);
        }

        for (int i = 1; i <= 1000; i++) {
            priorityQueue.dequeue();
        }

        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void testIsEmptyAfterEnqueueDequeue() {
        // Escenario: Verificación de isEmpty después de agregar y eliminar elementos
        // Verificar que isEmpty devuelva true después de agregar y eliminar elementos
        // en la cola.
        assertTrue(priorityQueue.isEmpty());

        priorityQueue.enqueue(5);
        assertFalse(priorityQueue.isEmpty());

        priorityQueue.dequeue();
        assertTrue(priorityQueue.isEmpty());
    }
}
