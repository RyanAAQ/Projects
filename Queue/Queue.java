package Queue;

public class Queue {
    private int[] elements;
    private int size;

    public Queue() {
        elements = new int[10];
        size = 0;
    }

    public boolean add(int item) {
        if (size == elements.length) {
            throw new IllegalStateException("Queue is full");
        }
        elements[size] = item;
        size++;
        return true;
    }

    public boolean offer(int item) {
        if (size == elements.length) {
            return false;
        }
        elements[size] = item;
        size++;
        return true;
    }

    public int remove() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        return shift();
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        return shift();
    }

    public int element() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        return elements[0];
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return elements[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int shift() {
        int front = elements[0];
        for (int i = 0; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return front;
    }
}
