package things;

import diary.Diaries;
import diary.Diary;
import diary.Entry;

import java.util.Scanner;

public class Main {
    private static final Diaries diaries = new Diaries();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== Diary App =====");
            System.out.println("1. Add diary");
            System.out.println("2. Delete diary");
            System.out.println("3. Open diary");
            System.out.println("4. List all diaries");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1" -> addDiary();
                case "2" -> deleteDiary();
                case "3" -> openDiary();
                case "4" -> listDiaries();
                case "0" -> running = false;
                default  -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addDiary() {
        System.out.print("Username: ");
        String username = input.nextLine().strip();
        System.out.print("Password: ");
        String password = input.nextLine().strip();
        try {
            diaries.add(username, password);
            System.out.println("Diary created.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteDiary() {
        System.out.print("Username: ");
        String username = input.nextLine().strip();
        System.out.print("Password: ");
        String password = input.nextLine().strip();
        try {
            diaries.delete(username, password);
            System.out.println("Diary deleted.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listDiaries() {
        if (diaries.getDiaries().isEmpty()) {
            System.out.println("No diaries.");
            return;
        }
        for (Diary diary : diaries.getDiaries()) {
            System.out.println(diary.getUsername());
        }
    }

    private static void openDiary() {
        System.out.print("Username: ");
        String username = input.nextLine().strip();
        Diary diary = diaries.findByUsername(username);
        if (diary == null) {
            System.out.println("Diary not found.");
            return;
        }
        System.out.print("Password: ");
        String password = input.nextLine().strip();
        try {
            diary.unlockDiary(password);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean isOpen = true;
        while (isOpen) {
            System.out.println("\n======== " + username + "'s Diary =======");
            System.out.println("1. Create entry");
            System.out.println("2. View entries");
            System.out.println("3. Update entry");
            System.out.println("4. Delete entry");
            System.out.println("0. Lock and go back to main menu");
            System.out.print("Choose: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> createEntry(diary);
                case "2" -> viewEntries(diary);
                case "3" -> updateEntry(diary);
                case "4" -> deleteEntry(diary);
                case "0" -> {
                    diary.lockDiary();
                    isOpen = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void createEntry(Diary diary) {
        System.out.print("Title: ");
        String title = input.nextLine().strip();
        System.out.print("Body: ");
        String body = input.nextLine().strip();
        diary.createEntry(title, body);
        System.out.println("Entry created.");
    }

    private static void viewEntries(Diary diary) {
        if (diary.getEntries().isEmpty()) {
            System.out.println("No entries.");
            return;
        }
        for (Entry entry : diary.getEntries()) {
            System.out.println(entry.getId() + ". " + entry.getTitle() + " was created on " + entry.getDateCreated());
            System.out.println("    " + entry.getBody());
        }
    }

    private static void updateEntry(Diary diary) {
        viewEntries(diary);
        System.out.print("Enter the Entry ID to update: ");
        int id = input.nextInt();
        System.out.print("New title: ");
        String title = input.nextLine();
        System.out.print("New body: ");
        String body = input.nextLine();
        try {
            diary.updateEntry(id, title, body);
            System.out.println("Entry updated.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteEntry(Diary diary) {
        viewEntries(diary);
        System.out.print("Entry ID to delete: ");
        int id = input.nextInt();
        try {
            diary.deleteEntry(id);
            System.out.println("Entry deleted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
