package assignment4.orderinheaven;

import java.util.Scanner;

public class Consoleui {

    private Scanner scanner;

    public Consoleui() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("Please select an option:");
            System.out.println("1. Show solar system data");
            System.out.println("2. Option 2");
            System.out.println("3. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Handle option 1
                    System.out.println("Here is the solar system data:");
                    DataTransfer.readFromFile("solarsystem.data");
                    break;
                case "2":
                    // Handle option 2
                    System.out.println("You selected option 2");
                    break;
                case "3":
                    // Exit the program
                    System.out.println("Exiting the program");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

}
