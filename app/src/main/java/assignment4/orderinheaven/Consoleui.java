package assignment4.orderinheaven;

import java.util.Scanner;
import java.util.ArrayList;

public class Consoleui {

    private Scanner scanner;
    private ArrayList<SolarSystem> solarSystems;

    public Consoleui() {
        scanner = new Scanner(System.in);
        solarSystems = DataTransfer.readFromFile("solarsystem.data");
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("Please select an option:");
            System.out.println("1. Show solar system data");
            System.out.println("2. Manage solar systems");
            System.out.println("3. Delete a member of the solar system");
            System.out.println("4. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Show solar system data
                    if (solarSystems.isEmpty()) {
                        System.out.println("There are no stars yet.");
                    } else {
                        for (SolarSystem solarSystem : solarSystems) {
                            System.out.println(solarSystem);
                        }
                    }
                    break;
                case "2":
                    // Manage solar systems
                    manageSolarSystems();
                    break;
                case "3":
                    // Delete a member of the solar system

                    break;
                case "4":
                    // Exit the program
                    System.out.println("Exiting the program");
                    DataTransfer.writeToFile(solarSystems, "solarsystem.data");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void manageSolarSystems() {
        boolean managing = true;
        while (managing) {
            System.out.println("Please select an option:");
            System.out.println("1. Create a star");
            System.out.println("2. Manage stars");
            System.out.println("3. Go back");
    
            String input = scanner.nextLine();
    
            switch (input) {
                case "1":
                    // Create a star
                    createStar();
                    break;
                case "2":
                    // Manage stars
                    
                    break;
                case "3":
                    // Go back to the main menu
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void createStar() {
        System.out.println("Enter the name of the star:");
        String name = scanner.nextLine();
    
        System.out.println("Enter the average radius of the star in kilometers:");
        int avgRadiusInKm = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
    
        Star newStar = new Star(name, avgRadiusInKm);
    
        SolarSystem newSolarSystem = new SolarSystem();
        newSolarSystem.addStar(newStar);
        solarSystems.add(newSolarSystem);
    
        System.out.println("Star created successfully.");
    }
}