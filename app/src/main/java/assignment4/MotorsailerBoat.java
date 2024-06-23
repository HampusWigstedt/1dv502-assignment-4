package assignment4;

/**
 * Represents a Motorsailer boat with specific depth and engine power.
 */
public class MotorsailerBoat extends Boat {
  private double depth;
  private double enginePower;

  /**
   * Constructs a MotorsailerBoat with the specified name, length, depth, and engine power.
   *
   * @param name        the name of the motorsailer
   * @param length      the length of the motorsailer
   * @param depth       the depth of the sail
   * @param enginePower the engine power of the motorsailer
   */
  public MotorsailerBoat(String name, double length, double depth, double enginePower) {
    super(name, length);
    this.depth = depth;
    this.enginePower = enginePower;
  }

  /**
   * Returns the depth of the sail.
   *
   * @return the sail depth
   */
  public double getSailDepth() {
    return depth;
  }

  /**
   * Returns the engine power of the motorsailer.
   *
   * @return the engine power
   */
  public double getEnginePower() {
    return enginePower;
  }

  /**
   * Returns a string representation of the motorsailer details.
   *
   * @return a formatted string with motorsailer details
   */
  @Override
  public String getDetails() {
    return String.format("BOAT:%s:motorsailer:%.0f:%.0f:%.0f", name, length, depth, enginePower);
  }
}