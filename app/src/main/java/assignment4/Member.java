package assignment4;

import java.util.Random;

/**
 * Member class.
 */
public class Member {
  private String name;
  private String email;
  private int memberId;
  private static final Random random = new Random();

  /**
   * Constructor for Member class.
   *
   * @param name  the name of the member
   * @param email the email of the member
   */
  public Member(String name, String email) {
    this.setName(name);
    this.setEmail(email);
    this.setMemberId();
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getMemberId() {
    return memberId;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setEmail(String email) {
    this.email = email;
  }

  private void setMemberId() {
    int min = 100000;
    int max = 999999;
    int memberId = random.nextInt((max - min) + 1) + min;
    this.memberId = memberId;
  }

  @Override
  public String toString() {
    return "Member{"
        + "name='" + name + '\''
        + ", email='" + email + '\''
        + ", memberId=" + memberId
        + '}';
  }
}