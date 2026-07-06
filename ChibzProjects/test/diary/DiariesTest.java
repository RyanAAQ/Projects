package diary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiariesTest {

    private Diaries diaries;

    @BeforeEach
    void setUp() {
        diaries = new Diaries();
    }

    @Test
    void addCreatesDiaryWithTheCorrectUsername() {
        diaries.add("Ryan", "pass123");
        assertNotNull(diaries.findByUsername("Ryan"));
    }

    @Test
    void findByUsernameReturnsCorrectDiary() {
        diaries.add("Ryan", "pass123");
        diaries.add("Ariyo", "pass456");
        Diary found = diaries.findByUsername("Ariyo");
        assertNotNull(found);
        assertEquals("Ariyo", found.getUsername());
    }

    @Test
    void findByUsernameThatDoesNotExistReturnsNull() {
        assertNull(diaries.findByUsername("ghost"));
    }

    @Test
    void deleteRemovesDiaryFromList() {
        diaries.add("Ryan", "pass123");
        diaries.delete("Ryan", "pass123");
        assertNull(diaries.findByUsername("Ryan"));
    }

    @Test
    void deleteWithWrongPasswordThrowsException() {
        diaries.add("Ryan", "pass123");
        assertThrows(IllegalArgumentException.class, () -> diaries.delete("Ryan", "nigger"));
    }

    @Test
    void deleteUsernameThatDoesNotExistThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> diaries.delete("Quadri", "pass"));
    }

    @Test
    void multipleDiariesAreTrackedSeparately() {
        diaries.add("Ryan", "pass1");
        diaries.add("Quadri", "pass2");
        assertEquals(2, diaries.getDiaries().size());
    }

    @Test
    void addingDuplicateUsernameThrowsException() {
        diaries.add("Ryan", "pass123");
        assertThrows(IllegalArgumentException.class, () -> diaries.add("Ryan", "differentpass"));
    }

    @Test
    void addingDuplicateUsernameDoesNotAddSecondDiary() {
        diaries.add("Ryan", "pass123");
        try { diaries.add("Ryan", "differentpass"); } catch (IllegalArgumentException ignored) {}
        assertEquals(1, diaries.getDiaries().size());
    }
}
