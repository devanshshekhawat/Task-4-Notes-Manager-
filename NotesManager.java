import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Notes Manager =====");
            System.out.println("1. Add a Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Clear All Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    clearNotes();
                    break;
                case 4:
                    System.out.println("Exiting... Have a great day!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Method to add notes to file
    private static void addNote(Scanner scanner) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append mode
            System.out.print("Enter your note: ");
            String note = scanner.nextLine();
            fw.write(note + "\n");
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read and display all notes
    private static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n----- Your Notes -----");
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found yet. Add some first!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to clear all notes
    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME, false)) {
            fw.write(""); // overwrite with empty content
            System.out.println("All notes cleared!");
        } catch (IOException e) {
            System.out.println("Error clearing notes: " + e.getMessage());
        }
    }
}
