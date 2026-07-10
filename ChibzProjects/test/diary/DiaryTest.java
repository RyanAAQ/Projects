package diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {

    private Diary diary;

    @BeforeEach
    void setUp() {
        diary = new Diary("Ryan", "abcdefg");
    }

    @Test
    void newDiaryIsLocked() {
        assertTrue(diary.isLocked());
    }

    @Test
    void unlockingWithCorrectPasswordUnlocksDiary() {
        diary.unlockDiary("abcdefg");
        assertFalse(diary.isLocked());
    }

    @Test
    void unlockingWithWrongPasswordThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> diary.unlockDiary("wrongpass"));
    }

    @Test
    void lockingADiaryLocksIt() {
        diary.unlockDiary("abcdefg");
        diary.lockDiary();
        assertTrue(diary.isLocked());
    }

    @Test
    void createEntryOnLockedDiaryThrowsException() {
        assertThrows(IllegalStateException.class, () -> diary.createEntry("Title", "Body"));
    }

    @Test
    void createEntryAddsEntryToDiary() {
        diary.unlockDiary("abcdefg");
        diary.createEntry("My First Entry", "Today was great");
        assertEquals(1, diary.getEntries().size());
    }

    @Test
    void entryHasCorrectTitleAndBody() {
        diary.unlockDiary("abcdefg");
        diary.createEntry("Hello", "World");
        Entry entry = diary.getEntries().get(0);
        assertEquals("Hello", entry.getTitle());
        assertEquals("World", entry.getBody());
    }

    @Test
    void findEntryByIdReturnsCorrectEntry() {
        diary.unlockDiary("abcdefg");
        diary.createEntry("First", "Body one");
        diary.createEntry("Second", "Body two");
        Entry found = diary.findEntryById(1);
        assertNotNull(found);
        assertEquals("First", found.getTitle());
    }

    @Test
    void findEntryByIdThatDoesNotExistReturnsNull() {
        diary.unlockDiary("abcdefg");
        assertNull(diary.findEntryById(99));
    }

    @Test
    void deleteEntryRemovesItFromDiary() {
        diary.unlockDiary("abcdefg");
        diary.createEntry("To Delete", "Gone soon");
        int id = diary.getEntries().get(0).getId();
        diary.deleteEntry(id);
        assertEquals(0, diary.getEntries().size());
    }

    @Test
    void deleteEntryOnLockedDiaryThrowsException() {
        assertThrows(IllegalStateException.class, () -> diary.deleteEntry(1));
    }

    @Test
    void deleteEntryThatDoesNotExistThrowsException() {
        diary.unlockDiary("abcdefg");
        assertThrows(IllegalArgumentException.class, () -> diary.deleteEntry(99));
    }

    @Test
    void updateEntryOnLockedDiaryThrowsException() {
        assertThrows(IllegalStateException.class, () -> diary.updateEntry(1, "T", "B"));
    }

    @Test
    void updateEntryThatDoesNotExistThrowsException() {
        diary.unlockDiary("abcdefg");
        assertThrows(IllegalArgumentException.class, () -> diary.updateEntry(99, "T", "B"));
    }

    @Test
    void updateEntryChangesTheEntryInTheDiary() {
        diary.unlockDiary("abcdefg");
        diary.createEntry("Original Title", "Original Body");
        Entry entry = diary.getEntries().get(0);
        entry.setTitle("Nigeerrr Title");
        entry.setBody("Todays date is what");
        assertEquals("Nigeerrr Title", entry.getTitle());
    }

    @Test
    void multiplEntriesAreTrackedCorrectly() {
        diary.unlockDiary("abcdefg");
        diary.createEntry("Entry 1", "Body 1");
        diary.createEntry("Entry 2", "Body 2");
        diary.createEntry("Entry 3", "Body 3");
        assertEquals(3, diary.getEntries().size());
    }
}
