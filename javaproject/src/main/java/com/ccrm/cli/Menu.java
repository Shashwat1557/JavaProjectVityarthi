package com.ccrm.cli;

import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    public int mainMenu(){
        System.out.println("\n=== CCRM ===");
        System.out.println("1. Students");
        System.out.println("2. Courses");
        System.out.println("3. Enrollment & Grades");
        System.out.println("4. Transcripts");
        System.out.println("5. Import/Export/Backup");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public String prompt(String label){ System.out.print(label + ": "); return sc.nextLine().trim(); }
    public int promptInt(String label){ System.out.print(label + ": "); return Integer.parseInt(sc.nextLine()); }
}