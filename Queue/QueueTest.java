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
    public void addIncreasesSize() {
        queue.add(5);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void peekOnEmptyQueueReturnsMinusOne() {
        assertEquals(-1, queue.peek());
    }

    @Test
    public void peekReturnsHeadWithoutRemoving() {
        queue.add(10);
        assertEquals(10, queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    public void elementOnEmptyQueueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> queue.element());
    }

    @Test
    public void elementReturnsHeadWithoutRemoving() {
        queue.add(10);
        assertEquals(10, queue.element());
        assertEquals(1, queue.size());
    }

    @Test
    public void removeOnEmptyQueueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> queue.remove());
    }

    @Test
    public void removeReturnsHead() {
        queue.add(7);
        assertEquals(7, queue.remove());
    }

    @Test
    public void removeDeletesHead() {
        queue.add(7);
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void pollOnEmptyQueueReturnsMinusOne() {
        assertEquals(-1, queue.poll());
    }

    @Test
    public void pollReturnsAndRemovesHead() {
        queue.add(7);
        assertEquals(7, queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void addOnFullQueueThrowsException() {
        for (int i = 0; i < 10; i++) queue.add(i);
        assertThrows(IllegalArgumentException.class, () -> queue.add(99));
    }

    @Test
    public void offerOnFullQueueReturnsFalse() {
        for (int i = 0; i < 10; i++) queue.offer(i);
        assertFalse(queue.offer(99));
    }

    @Test
    public void queueIsFirstInFirstOut() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(1, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
    }
}
