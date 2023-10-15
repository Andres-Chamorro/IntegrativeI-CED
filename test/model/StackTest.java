package model;

import dataEstructures.Stack;
import customExceptions.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    private Stack<Integer> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.size());

        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    public void testFront() {
        try {
            stack.front();
        } catch (EmptyStackException e) {
            assertEquals("No se puede obtener el frente de una pila vacía.", e.getMessage());
        }

        stack.push(42);
        assertEquals(Integer.valueOf(42), stack.front());

        stack.push(100);
        assertEquals(Integer.valueOf(100), stack.front());

        stack.pop();
        assertEquals(Integer.valueOf(42), stack.front());
    }

    @Test
    public void testStress() {
        for (int i = 0; i < 1000000; i++) {
            stack.push(i);
        }
        assertEquals(1000000, stack.size());
        for (int i = 999999; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), stack.pop());
        }
    }

    @Test(expected = EmptyStackException.class)
    public void testPopFromEmptyStackThrowsException() {
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void testFrontOfEmptyStackThrowsException() {
        stack.front();
    }
}