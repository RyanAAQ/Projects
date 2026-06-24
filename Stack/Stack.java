package Stack;

public class Stack {
    private int[] elements;
    private int size;

    public Stack() {
        elements = new int[10];
        size = 0;
    }

    public void push(int item) {
        elements[size] = item;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot pop from an empty stack");
        }
        int top = elements[size - 1];
        size--;
        return top;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot peek at an empty stack");
        }
        return elements[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
