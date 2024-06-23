package assignment4;

/**
 * Represents a Sailboat with a specific depth.
 */
public class SailboatBoat extends Boat {
  protected double depth;

  /**
   * Constructs a SailboatBoat with the specified name, length, and depth.
   *
   * @param name   the name of the sailboat
   * @param length the length of the sailboat
   * @param depth  the depth of the sailboat
   */
  public SailboatBoat(String name, double length, double depth) {
    super(name, length);
    this.depth = depth;
  }

  /**
   * Returns the depth of the sailboat.
   *
   * @return the depth of the sailboat
   */
  public double getSailDepth() {
    return depth;
  }

  /**
   * Returns a string representation of the sailboat details.
   *
   * @return a formatted string with sailboat details
   */
  @Override
  public String getDetails() {
    return String.format("BOAT:%s:sailboat:%.0f:%.0f", name, length, depth);
  }
}
