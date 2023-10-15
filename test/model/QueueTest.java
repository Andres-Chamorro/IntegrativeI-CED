package model;

import dataEstructures.Queue;
import customExceptions.EmptyQueueException;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void testEnqueueStandard() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());
        assertEquals(Integer.valueOf(1), queue.front());
    }

    @Test
    public void testEnqueueLimit() {
        for (int i = 1; i <= 1000; i++) {
            queue.enqueue(i);
        }

        assertEquals(1000, queue.size());
        assertEquals(Integer.valueOf(1), queue.front());
    }

    @Test
    public void testEnqueueNull() {
        queue.enqueue(null);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueStandard() throws EmptyQueueException {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(2, queue.size());
    }

    @Test
    public void testDequeueLimit() throws EmptyQueueException {
        for (int i = 1; i <= 1000; i++) {
            queue.enqueue(i);
        }
        for (int i = 1; i <= 1000; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertTrue(queue.isEmpty());
        try {
            queue.dequeue();
            fail("Expected EmptyQueueException");
        } catch (EmptyQueueException e) {
            // Excepción esperada
        }
    }

    @Test
    public void testFrontStandard() throws EmptyQueueException {
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(15);

        assertEquals(Integer.valueOf(5), queue.front());
    }

    @Test
    public void testFrontLimit() throws EmptyQueueException {
        for (int i = 1; i <= 1000; i++) {
            queue.enqueue(i);
        }
        assertEquals(Integer.valueOf(1), queue.front());
    }

    @Test
    public void testFrontEmptyQueue() {
        assertTrue(queue.isEmpty());

        try {
            queue.front();
            fail("Expected EmptyQueueException");
        } catch (EmptyQueueException e) {
            // Excepción esperada
        }
    }

    @Test
    public void testIsEmpty_standard() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty_limit() {
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testIsEmpty_interesting() {
        queue.enqueue(1);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testSize_standard() {
        assertEquals(0, queue.size());
    }

    @Test
    public void testSize_limit() {
        queue.enqueue(1);
        assertEquals(1, queue.size());
    }

    @Test
    public void testSize_interesting() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
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