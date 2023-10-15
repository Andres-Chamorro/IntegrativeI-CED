package model;

import dataEstructures.Queue;
import customExceptions.EmptyQueueException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.enqueue(2);
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    public void testEnqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testFront() throws EmptyQueueException {
        try {
            queue.front();
        } catch (EmptyQueueException e) {
            assertEquals("No se puede obtener el frente de una cola vacía.", e.getMessage());

        }

        queue.enqueue(42);
        assertEquals(Integer.valueOf(42), queue.front());

        queue.enqueue(100);
        assertEquals(Integer.valueOf(42), queue.front());

        queue.dequeue();
        assertEquals(Integer.valueOf(100), queue.front());
    }

    @Test(expected = EmptyQueueException.class)
    public void testDequeueFromEmptyQueueThrowsException() throws EmptyQueueException {
        queue.dequeue();
    }

    @Test(expected = EmptyQueueException.class)
    public void testFrontOfEmptyQueueThrowsException() throws EmptyQueueException {
        queue.front();
    }

    @Test
    public void testStress() {
        for (int i = 0; i < 1000000; i++) {
            queue.enqueue(i);
        }
        assertEquals(1000000, queue.size());
        for (int i = 0; i < 1000000; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }
    }

    @Test
    public void testConsistency() {
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }

}