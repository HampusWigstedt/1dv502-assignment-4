package assignment4;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the user interface for the Boat Club application.
 * It handles user interactions and commands for managing members and their boats.
 */
public class BoatClubInterface {
  private Registry registry;
  private Scanner scanner;

  /**
   * Constructs a new BoatClubInterface instance with the specified registry.
   *
   * @param registry The registry to be used by this interface.
   */
  public BoatClubInterface(Registry registry) {
    this.registry = registry;
    this.scanner = new Scanner(System.in);
  }

  /**
   * Starts the user interface loop, allowing the user to interact with the registry.
   */
  public void run() {
    loadRegistry();
    boolean running = true;
    while (running) {
      printMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline
      switch (choice) {
        case 1:
          createMember();
          break;
        case 2:
          listMembers();
          break;
        case 3:
          viewMemberDetails();
          break;
        case 4:
          running = false;
          saveRegistry();
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void printMenu() {
    System.out.println("1. Create new member");
    System.out.println("2. List all members");
    System.out.println("3. View member details");
    System.out.println("4. Quit");
  }

  private void createMember() {
    System.out.print("Enter member name: ");
    String name = scanner.nextLine();
    System.out.print("Enter member email (optional): ");
    String email = scanner.nextLine();
    Member member = new Member(name, email);
    registry.addMember(member);
  }

  private void listMembers() {
    for (Member member : registry.getMembers()) {
      System.out.println(member.getName() + " (" + member.getMemberId() + ")");
    }
  }

  private void viewMemberDetails() {
    System.out.print("Enter member ID: ");
    String memberId = scanner.nextLine();
    Member member = registry.findMemberById(memberId);
    if (member != null) {
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      System.out.println("Boats:");
      // Add logic to list boats
      // for (Boat boat : member.getBoats()) {
      //   System.out.println(" - " + boat.getDetails());
      // }
      // Provide options to add/remove boats, delete member, etc.
    } else {
      System.out.println("Member not found.");
    }
  }

  private void loadRegistry() {
    try {
      registry.loadFromFile("registry.data");
    } catch (IOException e) {
      System.out.println("Failed to load registry: " + e.getMessage());
    }
  }

  private void saveRegistry() {
    try {
      registry.saveToFile("registry.data");
    } catch (IOException e) {
      System.out.println("Failed to save registry: " + e.getMessage());
    }
  }
}