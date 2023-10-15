package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import dataEstructures.Heap;
import customExceptions.NoSuchElementException;

public class HeapTest {

    private Heap<Integer> heap;

    @BeforeEach
    public void setUp() {
        heap = new Heap<>();
    }

    @Test
    public void testInsertStandardScenario() {
        heap.insert(3);
        heap.insert(6);
        heap.insert(8);
        assertEquals(Integer.valueOf(3), heap.extractMin());
    }

    @Test
    public void testInsertManyElements() {
        for (int i = 0; i < 1000000; i++) {
            heap.insert(i);
        }
    }

    @Test
    public void testInsertEdgeCase() {
        heap.insert(5);
        assertEquals(Integer.valueOf(5), heap.extractMin());
    }

    @Test
    public void testInsertInterestingScenario() {
        heap.insert(10);
        heap.insert(7);
        heap.insert(1);
        // Verificar que el mínimo sea 1
        assertEquals(Integer.valueOf(1), heap.extractMin());
    }

    @Test
    public void testHeapifyStandardScenario() {
        heap.insert(8);
        heap.insert(4);
        heap.insert(2);

        heap.heapify(0);

        assertEquals(Integer.valueOf(2), heap.extractMin());
    }

    @Test
    public void testHeapifyEdgeCase() {
        heap.insert(5);
        heap.heapify(0);

        assertEquals(Integer.valueOf(5), heap.extractMin());
    }

    @Test
    public void testHeapifyInterestingScenario() {
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        heap.insert(3);

        heap.heapify(0);

        assertEquals(Integer.valueOf(1), heap.extractMin());

        heap.heapify(0);

        assertEquals(Integer.valueOf(3), heap.extractMin());
    }

    @Test
    public void testExtractMin() {
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        assertEquals(1, heap.extractMin());
    }

    @Test
    public void testExtractMinOrderSucetion() {
        heap.insert(6);
        heap.insert(1);
        heap.insert(9);
        assertEquals(Integer.valueOf(1), heap.extractMin());
        assertEquals(Integer.valueOf(6), heap.extractMin());
        assertEquals(Integer.valueOf(9), heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testExtractMinFromEmptyHeap() {
        try {
            heap.extractMin();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void testIsEmptyStandardScenario() {
        assertTrue(heap.isEmpty());
        heap.insert(3);
        assertFalse(heap.isEmpty());
        heap.extractMin();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testIsEmptyEdgeCase() {
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testIsEmptyAfterInsertion() {
        heap.insert(7);
        heap.insert(5);
        heap.insert(2);
        assertFalse(heap.isEmpty());
    }
}