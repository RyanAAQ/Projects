package set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetTest {

    private Set set;

    @BeforeEach
    public void setUp() {
        set = new Set();
    }

    @Test
    public void newSetIsEmpty() {
        assertTrue(set.isEmpty());
    }

    @Test
    public void newSetSizeIsZero() {
        assertEquals(0, set.size());
    }

    @Test
    public void addingAnElementMakesTheSetNotEmpty() {
        set.add(5);
        assertFalse(set.isEmpty());
    }

    @Test
    public void addingAnElementIncreasesTheSizeOfTheSet() {
        set.add(10);
        assertEquals(1, set.size());
    }

    @Test
    public void addMethodReturnsTrueWhenTheElementIsNew() {
        assertTrue(set.add(7));
    }

    @Test
    public void containsMethodReturnsFalseForElementThatHasNotBeenAdded() {
        assertFalse(set.contains(99));
    }

    @Test
    public void containsMethodReturnsTrueForANewlyAddedElement() {
        set.add(42);
        assertTrue(set.contains(42));
    }

    @Test
    public void addingTheSameElementTwiceDoesNotIncreaseSize() {
        set.add(10);
        set.add(10);
        assertEquals(1, set.size());
    }

    @Test
    public void addMethodReturnsFalseWhenElementAlreadyExists() {
        set.add(7);
        assertFalse(set.add(7));
    }

    @Test
    public void multipleNewElementsAreAllAdded() {
        set.add(1);
        set.add(2);
        set.add(3);
        assertEquals(3, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
    }

    @Test
    public void setGrowsMoreThanInitialSize() {
        for (int count = 0; count < 15; count++) {
            set.add(count);
        }
        assertEquals(15, set.size());
        assertTrue(set.contains(14));
    }

    @Test
    public void addingDuplicatesALotOfTimesDoesNot() {
        for (int count = 0; count < 10; count++) {
            set.add(count);
        }
        for (int count = 0; count < 10; count++) {
            set.add(count);
        }
        assertEquals(10, set.size());
    }

    @Test
    public void removingAnElementThatDoesNotExistReturnsFalse() {
        assertFalse(set.remove(99));
    }

    @Test
    public void removingAnElementThatExistsReturnsTrueAndSizeReduces() {
        set.add(5);
        assertTrue(set.remove(5));
        assertEquals(0, set.size());
    }

    @Test
    public void afterRemovingAnElementTheSetNoLongerContainsThatElement() {
        set.add(5);
        set.remove(5);
        assertFalse(set.contains(5));
    }

    @Test
    public void clearMakesSetEmpty() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.clear();
        assertTrue(set.isEmpty());
    }
}
