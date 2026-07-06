package stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {

    private Stack stack;

    @BeforeEach
    public void setup() {
        stack = new Stack();
    }

    @Test
    public void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void newStackSizeIsZero() {
        assertEquals(0, stack.size());
    }

    @Test
    public void afterPushStackIsNotEmpty() {
        stack.push(5);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void peekReturnsTopItem() {
        stack.push(10);
        assertEquals(10, stack.peek());
    }

    @Test
    public void popReturnsTopItem() {
        stack.push(7);
        assertEquals(7, stack.pop());
    }

    @Test
    public void popRemovesItem() {
        stack.push(7);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void pushAndPopIsLastInFirstOut() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void popOnEmptyStackThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stack.pop());
    }

    @Test
    public void peekOnEmptyStackThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stack.peek());
    }
}
