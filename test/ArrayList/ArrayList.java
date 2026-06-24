package ArrayList;

public class ArrayList {

    private final int [] elements = new int[100];
    private int size = 0;

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, int element) {
        for(int count = size; count > index; count--){
            elements[count] = elements[count - 1];
        }
        elements[index] = element;

        size++;
    }

    public void add(int element){
        elements[size] = element;
        size++;
    }
    public int size() {
        return size;
    }

    public int get(int i) {
        return elements[i];
    }

    public int getFirst() {
        return elements[0];
    }
    public int getLast() {
        return elements[size-1];
    }

    public int remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The Specified index is out of bounds for the list");
        }
        int removed = elements[index];

        for(int count = index; count < size-1; count++) {
            elements[count] = elements[count+1];
        }
        size--;

        return removed;
    }
    public void clear() {
        size = 0;
    }

    public void addFirst(int element){
        for(int count = size; count > 0; count--){
            elements[count] = elements[count - 1];
        }
        elements[0] = element;

        size++;
    }
}
