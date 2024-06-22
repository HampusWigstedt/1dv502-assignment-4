package assignment4;

import java.util.Random;

/**
 * Member class.
 */
public class Member {
  private String name;
  private String email;
  private String memberId;
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
    return "Member{"
        + "name='" + name + '\''
        + ", email='" + email + '\''
        + ", memberId='" + memberId + '\''
        + '}';
  }
}
