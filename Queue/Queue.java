package Queue;

public class Queue {
    private int[] data;
    private int size;

    public Queue() {
        data = new int[10];
        size = 0;
    }

    // Add to the back of the queue
    public void enqueue(int item) {
        data[size] = item;
        size++;
    }

    // Remove and return from the front of the queue
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        int front = data[0];
        // Shift everything left by one
        for (int i = 0; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return front;
    }

    // Return the front item without removing it
    public int peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot peek at an empty queue");
        }
        return data[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
