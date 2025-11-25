package utils;

import java.util.Scanner;
import utils.Narrator;

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
                    if (Narrator.getLanguage() == utils.Language.ID) {
                        System.out.println("Pilihan tidak valid. Masukkan angka antara " + min + " dan " + max + ".");
                    } else {
                        System.out.println("Invalid choice. Enter a number between " + min + " and " + max + ".");
                    }
                }
            } catch (NumberFormatException e) {
                if (Narrator.getLanguage() == utils.Language.ID) {
                    System.out.println("Input salah. Silakan masukkan angka yang valid.");
                } else {
                    System.out.println("Incorrect input. Please enter a valid number.");
                }
            }
        }
        return choice;
    }

    // closing scanner
    public void close() {
        scanner.close();
    }

    public void pressEnterToContinue() {
        if (Narrator.getLanguage() == utils.Language.ID) {
            System.out.print("\n[Tekan ENTER untuk melanjutkan...]");
        } else {
            System.out.print("\n[Press ENTER to continue...]");
        }
        scanner.nextLine();
    }
}