package assignment4;

/**
 * Represents a Canoe boat with a specific length.
 */
public class CanoeBoat extends Boat {
  /**
   * Constructs a CanoeBoat with the specified name and length.
   *
   * @param name   the name of the canoe
   * @param length the length of the canoe
   */
  public CanoeBoat(String name, double length) {
    super(name, length);
  }

  /**
   * Returns a string representation of the canoe details.
   *
   * @return a formatted string with canoe details
   */
  @Override
  public String getDetails() {
    return String.format("BOAT:%s:canoe:%.0f", name, length);
  }
}
