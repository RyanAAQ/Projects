package ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListTest {
    private ArrayList list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList();
    }

    @Test
    public void listIsEmptyWhenNothingIsAdded() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void oneElementIsAddedToTheListWIthASpecifiedIndex() {
        list.add(0, 98);
        assertEquals(1, list.size());
    }

    @Test
    public void anElementIsAddedWithoutASpecifiedIndexSoItGoesToTheEndOfTheList(){
        list.add(0, 98);
        list.add(1, 87);
        list.add(2, 89);
        list.add(3, 90);
        list.add(34);
        assertEquals(34, list.get(4));
    }

    @Test
    public void theSizeOfTheListIsAlwaysAccurate(){
        assertEquals(0, list.size());
        list.add(98);
        assertEquals(1, list.size());
    }

    @Test
    public void testGet() {
        list.add(0, 98);
        list.add(1, 99);
        list.add(2, 100);
        assertEquals(100, list.get(2));
    }

    @Test
    public void testGetFirst() {
        list.add(0, 98);
        list.add(1, 99);
        list.add(2, 100);
        assertEquals(98, list.getFirst());
    }

    @Test
    public void testGetLast() {
        list.add(0, 98);
        list.add(1, 99);
        list.add(2, 100);
        assertEquals(100, list.getLast());
    }

    @Test
    public void anElementIsRemovedFromTheListAndItIsReturned() {
        list.add(0, 98);
        list.add(1, 99);
        list.add(2, 100);
        assertEquals(98, list.remove(0));
    }

    @Test
    public void elementsAreAddedIntoTheTheListAndTheListIsEmptyAfterItIsCleared() {
        list.add(0, 98);
        list.add(1, 99);
        list.add(2, 100);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void addAnElementToTheFirstIndex() {
        list.add(5);
        list.add(67);
        list.addFirst(9);
        assertEquals(9, list.get(0));
    }

    @Test
    public void anElementIsAddedInTheMiddleAndTheRestIsShifted() {
        list.add(98);
        list.add(99);
        list.add(100);
        list.add(2, 50);
        assertEquals(98, list.get(0));
        assertEquals(99, list.get(1));
        assertEquals(50, list.get(2));
        assertEquals(100, list.get(3));
        assertEquals(4, list.size());
    }
}