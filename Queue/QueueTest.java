package Queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTest {

    private Queue queue;

    @BeforeEach
    public void setup() {
        queue = new Queue();
    }

    @Test
    public void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void newQueueHasSizeZero() {
        assertEquals(0, queue.size());
    }

    @Test
    public void afterEnqueueQueueIsNotEmpty() {
        queue.enqueue(5);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void peekReturnsFirstItem() {
        queue.enqueue(10);
        assertEquals(10, queue.peek());
    }

    @Test
    public void dequeueReturnsFirstItem() {
        queue.enqueue(7);
        assertEquals(7, queue.dequeue());
    }

    @Test
    public void dequeueRemovesItem() {
        queue.enqueue(7);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void enqueueAndDequeueIsFirstInFirstOut() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue()); // first in, first out
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    public void dequeueOnEmptyQueueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> queue.dequeue());
    }

    @Test
    public void peekOnEmptyQueueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> queue.peek());
    }
}
