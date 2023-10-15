package model;

import dataEstructures.Stack;
import customExceptions.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPushStandard() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.size());
        assertEquals(Integer.valueOf(3), stack.front());
    }

    @Test
    public void testPushLimit() {
        for (int i = 1; i <= 1000; i++) {
            stack.push(i);
        }

        assertEquals(1000, stack.size());
        assertEquals(Integer.valueOf(1000), stack.front());
    }

    @Test
    public void testPushEmptyStack() {
        assertTrue(stack.isEmpty());
        stack.push(42);

        assertFalse(stack.isEmpty());
        assertEquals(Integer.valueOf(42), stack.front());
    }

    @Test
    public void testPopStandard() throws EmptyStackException {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(Integer.valueOf(3), stack.pop());
        assertEquals(2, stack.size());
    }

    @Test
    public void testPopLimit() throws EmptyStackException {
        for (int i = 1; i <= 1000; i++) {
            stack.push(i);
        }

        for (int i = 1000; i >= 1; i--) {
            assertEquals(Integer.valueOf(i), stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        assertTrue(stack.isEmpty());

        try {
            stack.pop();
            fail("Expected EmptyStackException");
        } catch (EmptyStackException e) {
        }
    }

    @Test
    public void testSize_standard() {
        assertEquals(0, stack.size());
    }

    @Test
    public void testSize_limit() {
        stack.push(1);
        assertEquals(1, stack.size());
    }

    @Test
    public void testSize_interesting() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
    }

    @Test
    public void testFrontStandard() throws EmptyStackException {
        stack.push(5);
        stack.push(10);
        stack.push(15);

        assertEquals(Integer.valueOf(15), stack.front());
    }

    @Test
    public void testFrontEmptyStack() {
        assertTrue(stack.isEmpty());

        try {
            stack.front();
            fail("Expected EmptyStackException");
        } catch (EmptyStackException e) {
        }
    }

    @Test
    public void testFrontLimit() throws EmptyStackException {
        for (int i = 1; i <= 1000; i++) {
            stack.push(i);
        }
        assertEquals(Integer.valueOf(1000), stack.front());
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
    public void testStress() {
        for (int i = 0; i < 1000000; i++) {
            stack.push(i);
        }
        assertEquals(1000000, stack.size());
        for (int i = 999999; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), stack.pop());
        }
    }
}