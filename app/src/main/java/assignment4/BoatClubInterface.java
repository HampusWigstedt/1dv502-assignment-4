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
    System.out.println("1. Create new member");
    System.out.println("2. List all members");
    System.out.println("3. Quit");
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
  Boat.Type boatType;
  try {
      boatType = Boat.Type.valueOf(type.toUpperCase());
  } catch (IllegalArgumentException e) {
      System.out.println("Invalid boat type. Please try again.");
      return;
  }
  // Assuming different information is needed based on the boat type,
  // you might ask for additional details here. For simplicity, let's assume
  // each boat type requires a length.
  System.out.print("Enter boat length: ");
  double length = scanner.nextDouble();
  scanner.nextLine(); // consume newline

  // Create a new Boat object and add it to the member
  Boat boat = new Boat(name, boatType, length);
  member.addBoat(boat);
  System.out.println("Boat added successfully.");
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
      System.out.println("1. View member details");
      System.out.println("2. Edit member");
      System.out.println("3. Delete member");
      System.out.println("4. Add new boat");
      System.out.println("5. Return to list members");
      
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline
      
      switch (choice) {
          case 1:
              viewMemberDetails(memberId);
              break;
          case 2:
              // Implement editMember method
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

  // Modify viewMemberDetails to accept memberId as parameter
  private void viewMemberDetails(String memberId) {
    Member member = registry.findMemberById(memberId);
    if (member != null) {
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        System.out.println("Boats:");
        for (Boat boat : member.getBoats()) { // Assuming Member class has a getBoats method returning a list of boats
            System.out.println("Boat Name: " + boat.getName() + ", Type: " + boat.getType() + ", Length: " + boat.getLength());
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