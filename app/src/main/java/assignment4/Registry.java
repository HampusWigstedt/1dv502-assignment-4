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
          String email = parts.length > 2 ? parts[2] : null;
          currentMember = new Member(parts[1], email, parts[3]);
          addMember(currentMember);
        } else if (line.startsWith("BOAT:") && currentMember != null) {
          // Add logic to create the correct boat type and add to the current member
          // For example:
          // Boat boat = new Boat(parts[1], parts[2], other parts...);
          // currentMember.addBoat(boat);
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
        // For each boat, add logic to write boat details
        // for (Boat boat : member.getBoats()) {
        //   bw.write("BOAT:" + boat.getDetails());
        //   bw.newLine();
        // }
      }
    }
  }
}