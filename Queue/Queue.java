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
            throw new IllegalArgumentException("Queue is full");
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
            throw new IllegalArgumentException("Cannot be removed because queue is empty");
        }
        int first = elements[0];
        for (int index = 0; index < size - 1; index++) {
            elements[index] = elements[index + 1];
        }
        size--;
        return first;
    }

    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        int first = elements[0];
        for (int index = 0; index < size - 1; index++) {
            elements[index] = elements[index + 1];
        }
        size--;
        return first;
    }

    public int element() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
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
}
