package assignment4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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
    this.registry = new Registry();
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
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
          running = false;
          saveRegistry();
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void printMenu() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("1. Create new member");
    System.out.println("2. List all members");
    System.out.println("3. Quit");
    System.out.println("---------------------------------------------------------------");
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
    System.out.println("---------------------------------------------------------------");
    int index = 1;
    Map<Integer, String> memberMap = new HashMap<>();
    for (Member member : registry.getMembers()) {
      System.out.println(index + ". " + member.getName() + " (" + member.getMemberId() + ")");
      memberMap.put(index, member.getMemberId());
      index++;
    }
    System.out.println(index + ". Return to main menu");
    
    System.out.print("Select a member by number or return to main menu: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume newline
    
    if (choice == index) {
      return; // Return to main menu
    } else if (memberMap.containsKey(choice)) {
      String memberId = memberMap.get(choice);
      memberMenu(memberId); // Handle member-specific actions in a new method
    } else {
      System.out.println("Invalid choice. Please try again.");
    }
  }

  // Step 1: Implement the addNewBoat method
  private void addNewBoat(String memberId) {
    Member member = registry.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    System.out.print("Enter boat name: ");
    String name = scanner.nextLine();
    System.out.print("Enter boat type (sailboat, motorboat, motorsailer, canoe): ");
    String type = scanner.nextLine();
    System.out.print("Enter boat length: ");
    double length = scanner.nextDouble();
    scanner.nextLine(); // consume newline

    Boat boat = null;
    switch (type.toLowerCase()) {
      case "sailboat":
        System.out.print("Enter sail depth: ");
        double depth = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        boat = new SailboatBoat(name, length, depth);
        break;
      case "motorboat":
        System.out.print("Enter engine power (hp): ");
        double enginePower = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        boat = new MotorboatBoat(name, length, enginePower);
        break;
      case "motorsailer":
        System.out.print("Enter sail depth: ");
        depth = scanner.nextDouble();
        System.out.print("Enter engine power (hp): ");
        enginePower = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        boat = new MotorsailerBoat(name, length, depth, enginePower);
        break;
      case "canoe":
        boat = new CanoeBoat(name, length);
        break;
      default:
        System.out.println("Invalid boat type. Please try again.");
        return;
    }

    if (boat != null) {
      member.addBoat(boat);
      System.out.println("Boat added successfully.");
    }
  }

  // Step 2: Modify the memberMenu method to include an option to add a new boat
  private void memberMenu(String memberId) {
    Member member = registry.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    boolean running = true;
    while (running) {
      System.out.println("---------------------------------------------------------------");
      System.out.println("1. View member details");
      System.out.println("2. Boat Details / Delete boat");
      System.out.println("3. Delete member");
      System.out.println("4. Add new boat");
      System.out.println("5. Return to list members");
      System.out.println("---------------------------------------------------------------");
      
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline
      
      switch (choice) {
        case 1:
          viewMemberDetails(memberId);
          break;
        case 2:
          deleteBoat(memberId);
          break;
        case 3:
          deleteMember(member); // Call deleteMember with the Member object
          break;
        case 4:
          addNewBoat(memberId); // Call addNewBoat with the memberId
          break;
        case 5:
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void deleteMember(Member member) {
    System.out.println("Are you sure you want to delete " + member.getName() + "? (yes/no)");
    String confirmation = scanner.nextLine();
    if ("yes".equalsIgnoreCase(confirmation)) {
      registry.removeMember(member); // Use the removeMember method from Registry
      System.out.println("Member deleted successfully.");
    } else {
      System.out.println("Member deletion cancelled.");
    }
  }

  private void deleteBoat(String memberId) {
    Member member = registry.findMemberById(memberId);
    if (member == null) {
      System.out.println("Member not found.");
      return;
    }
    if (member.getBoats().isEmpty()) {
      System.out.println("This member has no boats.");
      return;
    }
    System.out.println("Select a boat to view details:");
    int index = 1;
    for (Boat boat : member.getBoats()) {
      System.out.println(index++ + ". " + boat.getName() + " (" + boat.getLength() + " meters)");
    }
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume newline
    if (choice < 1 || choice > member.getBoats().size()) {
      System.out.println("Invalid choice. Please try again.");
      return;
    }
    Boat selectedBoat = member.getBoats().get(choice - 1);
    // Show boat details
    System.out.println("Details of selected boat:");
    System.out.println("Name: " + selectedBoat.getName());
    System.out.println("Length: " + selectedBoat.getLength() + " meters");
    System.out.println("Do you want to delete this boat or go back? (delete/back)");
    String action = scanner.nextLine();
    if ("delete".equalsIgnoreCase(action)) {
      member.removeBoat(selectedBoat);
      System.out.println("Boat deleted successfully.");
    } else if ("back".equalsIgnoreCase(action)) {
      System.out.println("Going back to the previous menu.");
    } else {
      System.out.println("Invalid input. Going back to the previous menu.");
    }
  }

  // Modify viewMemberDetails to accept memberId as parameter
  private void viewMemberDetails(String memberId) {
    Member member = registry.findMemberById(memberId);
    if (member != null) {
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      for (Boat boat : member.getBoats()) {
        String boatDetails = "Boat Name: " + boat.getName() + ", Length: " + boat.getLength();
        if (boat instanceof SailboatBoat) {
          boatDetails += ", Type: Sailboat, Sail Depth: " + ((SailboatBoat) boat).getSailDepth();
        } else if (boat instanceof MotorboatBoat) {
          boatDetails += ", Type: Motorboat, Engine Power: " + ((MotorboatBoat) boat).getEnginePower();
        } else if (boat instanceof MotorsailerBoat) {
          MotorsailerBoat motorsailer = (MotorsailerBoat) boat;
          boatDetails += ", Type: Motorsailer, Sail Depth: " + motorsailer.getSailDepth()
            + ", Engine Power: " + motorsailer.getEnginePower();
        } else if (boat instanceof CanoeBoat) {
          boatDetails += ", Type: Canoe";
        } else {
          boatDetails += ", Type: Unknown";
        }
        System.out.println(boatDetails);
      }
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