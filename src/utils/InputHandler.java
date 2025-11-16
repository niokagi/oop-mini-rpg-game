package utils;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    // catch input from user
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // min-max input control & constraint
    public int getInt(String prompt, int min, int max) {
        int choice = -1;
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                choice = Integer.parseInt(line);
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid choice. Enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input. Please enter a valid number.");
            }
        }
        return choice;
    }

    // closing scanner
    public void close() {
        scanner.close();
    }

    public void pressEnterToContinue() {
        System.out.println("\n[Press ENTER to continue...]");
        scanner.nextLine();
    }
}