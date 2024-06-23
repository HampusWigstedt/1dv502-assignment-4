package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Member class.
 */
public class Member {
  private String name;
  private String email;
  private String memberId;
  private List<Boat> boats; // Attribute to store multiple boats
  private static final Random random = new Random();

  /**
   * Constructor for Member class.
   *
   * @param name  the name of the member
   * @param email the email of the member
   */
  public Member(String name, String email) {
    this.name = name;
    this.email = email;
    this.memberId = generateMemberId();
    this.boats = new ArrayList<>(); // Initialize the list of boats
  }

  /**
   * Constructor for loading Member from file.
   *
   * @param name     the name of the member
   * @param email    the email of the member
   * @param memberId the member ID
   */
  public Member(String name, String email, String memberId) {
    this.name = name;
    this.email = email;
    this.memberId = memberId;
    this.boats = new ArrayList<>(); // Initialize the list of boats
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getMemberId() {
    return memberId;
  }

  public void addBoat(Boat boat) {
    this.boats.add(boat);
  }

  // Method to initiate and add a new Boat to the member's list of boats
  public void initiateAndAddBoat(Boat boat) {
    this.boats.add(boat);
  }

  public List<Boat> getBoats() {
    return Collections.unmodifiableList(boats);
  }

  private String generateMemberId() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    StringBuilder buffer = new StringBuilder(targetStringLength);
    while (buffer.length() < targetStringLength) {
      int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
      if (Character.isLetterOrDigit(randomLimitedInt)) {
        buffer.append((char) randomLimitedInt);
      }
    }
    return buffer.toString();
  }

  @Override
  public String toString() {
    StringBuilder boatsDetails = new StringBuilder();
    for (Boat boat : boats) {
      boatsDetails.append(boat.getDetails()).append("\n");
    }
    return "Member{"
        + "name='" + name + '\''
        + ", email='" + email + '\''
        + ", memberId='" + memberId + '\''
        + ", boats=" + boatsDetails + // Display details of each boat
        '}';
  }
}