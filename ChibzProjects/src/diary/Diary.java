package diary;

import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private boolean isLocked;
    private List<Entry> entries;
    private int entryId = 1;

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLocked = true;
        this.entries = new ArrayList<>();
    }

    public void unlockDiary(String password) {
        if (!this.password.equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }
        this.isLocked = false;
    }

    public void lockDiary() {
        this.isLocked = true;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void createEntry(String title, String body) {
        if (isLocked) throw new IllegalStateException("Diary is locked");
        entries.add(new Entry(entryId++, title, body));
    }

    public void deleteEntry(int id) {
        if (isLocked) throw new IllegalStateException("Diary is locked");
        Entry entry = findEntryById(id);
        if (entry == null) throw new IllegalArgumentException("Entry not found");
        entries.remove(entry);
    }

    public Entry findEntryById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) return entry;
        }
        return null;
    }

    public void updateEntry(int id, String newTitle, String newBody) {
        if (isLocked) throw new IllegalStateException("Diary is locked");
        Entry entry = findEntryById(id);
        if (entry == null) throw new IllegalArgumentException("Entry not found");
        entry.setTitle(newTitle);
        entry.setBody(newBody);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public String getUsername() {
        return username;
    }
}
