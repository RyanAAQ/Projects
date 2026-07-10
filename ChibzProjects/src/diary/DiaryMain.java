package diary;

import java.util.Scanner;

import static java.lang.System.out;

public class DiaryMain {

    static Diaries diaries = new Diaries();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            print("\n Welcome to the Diary!\n ");
            print("1. Add diary");
            print("2. Remove diary");
            print("3. Open diary");
            print("4. List diaries");
            print("0. Exit app");
            out.print("Choose: ");
            String choice = input.nextLine().strip();

            switch (choice) {
                case "1" -> addDiary();
                case "2" -> removeDiary();
                case "3" -> openDiary();
                case "4" -> listDiaries();
                case "0" -> running = false;
                default -> print("Invalid choice.");
            }

        }
    }

    private static void listDiaries() {
        if (diaries.getDiaries().isEmpty()) {
            System.out.println("No diaries.");
            return;
        }
        for(Diary diary : diaries.getDiaries()) {
            System.out.println(diary.getUsername());
        }
    }

    private static void openDiary() {
        print("Enter your username");
        String username = input.nextLine().strip();
        Diary diary = diaries.findByUsername(username);
        if (diary == null) {
            print("Username not found.");
            return;
        }
        print("Enter your password");
        String password = input.nextLine().strip();
        try {
            diary.unlockDiary(password);
            print("You have successfully unlocked diary.");
        }
        catch (IllegalArgumentException e){
            print(e.getMessage());
        }

        boolean isOpen = true;
        while (isOpen) {
            print(diary.getUsername() + "'s diary");
            print("1. Create Entry");
            print("2. View Entry");
            print("3. Remove Entry");
            print("4. Update Entry");
            print("0. Lock diary and return to main menu");
            print("Choose: ");
            String choice = input.nextLine().strip();

            switch (choice){
                case "1" -> createEntry(diary);
                case "2" -> viewEntries(diary);
                case "3" -> removeEntry(diary);
                case "4" -> updateEntry(diary);
                case "0" -> isOpen = false;
                default -> print("Invalid choice.");
            }
        }

    }

    private static void updateEntry(Diary diary) {
        viewEntries(diary);
        print("Enter Entry ID you want to update");
        int id = input.nextInt();
        try {
            print("Enter Entry Title");
            String title = input.nextLine().strip();
            print("Enter Entry Body");
            String description = input.nextLine().strip();
            diary.updateEntry(id, title, description);
            print("You Have Successfully Updated The Entry.");
        }
        catch (IllegalArgumentException e){
            print(e.getMessage());
        }
    }

    private static void removeEntry(Diary diary) {
        viewEntries(diary);
        print("Enter The Entry ID to delete the Entry");
        int id = input.nextInt();
        try {
            diary.deleteEntry(id);
            print("Entry deleted successfully.");
        }
        catch (IllegalArgumentException e){
            print(e.getMessage());
        }
    }

    private static void viewEntries(Diary diary) {
        if(diary.getEntries().isEmpty()) {
            print("No Entries.");
            return;
        }
        for (Entry entry : diary.getEntries()) {
            print(entry.getId() + "." + entry.getTitle() + " was created on " + entry.getDateCreated());
            print("   " + entry.getBody());
        }
    }

    private static void createEntry(Diary diary) {
        print("Enter the title of the entry");
        String title = input.nextLine().strip();
        print("Body: ");
        String body = input.nextLine().strip();
        diary.createEntry(title, body);
        print("Entry has been created.");
    }

    private static void removeDiary() {
        if (diaries.getDiaries().isEmpty()) {
            System.out.println("No diaries.");
            return;
        }
        print("Enter diary name: ");
        String name = input.nextLine().strip();
        print("Enter password: ");
        String password = input.nextLine().strip();
        try {
            diaries.delete(name, password);
            out.println("Successfully removed diary.");
        }
        catch (IllegalArgumentException e){
            print(e.getMessage());
        }
    }

    private static void addDiary() {
        print("Enter your username: ");
        String username = input.nextLine().strip();
        print("Enter your password: ");
        String password = input.nextLine().strip();
        try {
            diaries.add(username, password);
            print("Successfully added diary.");
        }
        catch (IllegalArgumentException e) {
            print(e.getMessage());
        }
    }

    private static void print(String message) {
        out.println(message);
    }
}
