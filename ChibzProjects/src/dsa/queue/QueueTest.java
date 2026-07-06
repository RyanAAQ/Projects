package queue;

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
    public void newQueueSizeIsZero() {
        assertEquals(0, queue.size());
    }

    @Test
    public void addIncreasesSize() {
        queue.add(5);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void peekOnAnEmptyQueueReturnsMinusOne() {
        assertEquals(-1, queue.peek());
    }

    @Test
    public void peekReturnsHeadWithoutRemoving() {
        queue.add(10);
        assertEquals(10, queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    public void elementOnAnEmptyQueueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> queue.element());
    }

    @Test
    public void elementReturnsFirstWithoutRemovingIt() {
        queue.add(10);
        assertEquals(10, queue.element());
        assertEquals(1, queue.size());
    }

    @Test
    public void removeOnAnEmptyQueueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> queue.remove());
    }

    @Test
    public void removeReturnsFirst() {
        queue.add(7);
        assertEquals(7, queue.remove());
    }

    @Test
    public void removeDeleteFirst() {
        queue.add(7);
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void pollOnEmptyQueueReturnsMinusOne() {
        assertEquals(-1, queue.poll());
    }

    @Test
    public void pollReturnsAndRemovesTheFirst() {
        queue.add(7);
        assertEquals(7, queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void addOnFullQueueThrowsException() {
        for (int index = 0; index < 6; index++) {
            queue.add(index);
        }
        assertThrows(IllegalArgumentException.class, () -> queue.add(99));
    }

    @Test
    public void offerOnFullQueueReturnsFalse() {
        for (int index = 0; index < 6; index++){
            queue.offer(index);
        }
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


