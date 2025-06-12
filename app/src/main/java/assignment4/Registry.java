package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the registry of members for the boat club.
 */
public class Registry {
  private List<Member> members;

  /**
   * Constructs a new Registry instance.
   */
  public Registry() {
    this.members = new ArrayList<>();
  }

  /**
   * Adds a member to the registry.
   *
   * @param member The member to add.
   */
  public void addMember(Member member) {
    for (Member existingMember : members) {
      if (existingMember.getEmail().equals(member.getEmail())) {
        System.out.println("Error: A member with this email already exists.");
        return;
      }
    }
    members.add(member);
  }

  public void removeMember(Member member) {
    members.remove(member);
  }

  /**
   * Returns an unmodifiable list of members.
   *
   * @return An unmodifiable view of the members list.
   */
  public List<Member> getMembers() {
    return Collections.unmodifiableList(new ArrayList<>(members));
  }

  /**
   * Finds a member by their ID.
   *
   * @param memberId The ID of the member to find.
   * @return The member if found, null otherwise.
   */
  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  /**
  * Loads members and their boats from a file.
  *
  * @param filename The name of the file to load from.
  * @throws IOException If an I/O error occurs.
  */
  public void loadFromFile(String filename) throws IOException {
    File file = new File(filename);
    if (!file.exists() && !file.createNewFile()) {
      throw new IOException("Failed to create new file: " + filename);
    }

    try (BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      String line;
      Member currentMember = null;
      while ((line = br.readLine()) != null) {
        if (line.startsWith("MEMBER:")) {
          String[] parts = line.split(":");
          if (parts.length >= 4) { // Ensure there are enough parts for a member
            String email = parts[2]; // Directly assign without redundant check
            currentMember = new Member(parts[1], email, parts[3]);
            addMember(currentMember);
          }
        } else if (line.startsWith("BOAT:") && currentMember != null) {
          String[] parts = line.split(":");
          try {
            if (parts.length >= 4) { // Common validation for all boats
              String name = parts[1];
              // Convert type to lowercase for case-insensitive comparison
              String type = parts[2].toLowerCase();
              double length = Double.parseDouble(parts[3]);
              switch (type) {
                case "sailboat":
                  if (parts.length >= 5) {
                    double depth = Double.parseDouble(parts[4]);
                    currentMember.addBoat(new SailboatBoat(name, length, depth));
                  }
                  break;
                case "motorboat":
                  if (parts.length >= 5) {
                    double enginePower = Double.parseDouble(parts[4]);
                    currentMember.addBoat(new MotorboatBoat(name, length, enginePower));
                  }
                  break;
                case "motorsailer":
                  if (parts.length >= 6) {
                    double depth = Double.parseDouble(parts[4]);
                    double enginePower = Double.parseDouble(parts[5]);
                    currentMember.addBoat(new MotorsailerBoat(name, length, depth, enginePower));
                  }
                  break;
                case "canoe":
                  currentMember.addBoat(new CanoeBoat(name, length));
                  break;
                default:
                  System.err.println("Unsupported boat type: " + type);
              }
            }
          } catch (NumberFormatException e) {
            System.err.println("Error parsing boat details: " + line);
          }
        }
      }
    }
  }

  /**
  * Saves members and their boats to a file.
  *
  * @param filename The name of the file to save to.
  * @throws IOException If an I/O error occurs.
  */
  public void saveToFile(String filename) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
      for (Member member : members) {
        bw.write(String.format("MEMBER:%s:%s:%s", member.getName(), member.getEmail(), member.getMemberId()));
        bw.newLine();
        for (Boat boat : member.getBoats()) {
          // Updated to use getDetails() for saving boat information
          bw.write(boat.getDetails());
          bw.newLine();
        }
      }
    }
  }
}