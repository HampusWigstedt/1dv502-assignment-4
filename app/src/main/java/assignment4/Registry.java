package assignment4;

import java.io.*;
import java.util.*;

public class Registry {
  private List<Member> members;

  public Registry() {
    this.members = new ArrayList<>();
  }

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

  public List<Member> getMembers() {
    return members;
  }

  public Member findMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        return member;
      }
    }
    return null;
  }

  public void loadFromFile(String filename) throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      file.createNewFile();
      return; // No data to load if file was just created
    }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      Member currentMember = null;
      while ((line = br.readLine()) != null) {
        if (line.startsWith("MEMBER:")) {
          String[] parts = line.split(":");
          String email = parts.length > 2 ? parts[2] : null;
          currentMember = new Member(parts[1], email, parts[3]);
          addMember(currentMember);
        } else if (line.startsWith("BOAT:") && currentMember != null) {
          String[] parts = line.split(":");
          // Add logic to create the correct boat type and add to the current member
          // For example:
          // Boat boat = new Boat(parts[1], parts[2], other parts...);
          // currentMember.addBoat(boat);
        }
      }
    }
  }

  public void saveToFile(String filename) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
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

  // Other methods to list members, add/remove boats, etc.
}
