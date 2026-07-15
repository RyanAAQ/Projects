package dsa.set;
import dsa.arraylist.ArrayList;

public class Set {

    int [] elements = new int[10];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(int element) {
        if (contains(element)) {
            return false;
        }

        if (size >= elements.length) {
            resize();
        }

        elements[size] = element;
        size++;
        return true;
    }

    public boolean contains(int element) {
        for (int count = 0; count < size; count++) {
            if (elements[count] == element) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        int[] newArray = new int[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    public boolean remove(int element) {
        for (int index = 0; index < size; index++) {
            if (elements[index] == element) {
                for (int count = index; count < size - 1; count++) {
                    elements[count] = elements[count + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        size = 0;
    }
}
