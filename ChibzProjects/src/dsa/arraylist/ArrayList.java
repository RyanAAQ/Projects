package dsa.arraylist;

public class ArrayList {

    private int [] elements = new int[5];
    private int size = 0;

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, int element) {

        if(size == elements.length){
            resize();
        }

        for(int count = size; count > index; count--){
            elements[count] = elements[count - 1];
        }
        elements[index] = element;

        size++;
    }

    private void resize() {
        int [] newElements = new int[elements.length * 2];

        for(int count = 0; count < elements.length; count++){
            newElements[count] = elements[count];
        }
        this.elements = newElements;
    }

    public void add(int element){
        elements[size] = element;
        size++;
    }
    public int size() {
        return size;
    }

    public int get(int index) {
        return elements[index];
    }

    public int getFirst() {
        return elements[0];
    }
    public int getLast() {
        return elements[size-1];
    }

    public int remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("The Specified index is out of bounds for the list");
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
