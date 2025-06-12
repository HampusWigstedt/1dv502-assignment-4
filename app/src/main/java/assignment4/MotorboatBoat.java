package assignment4;

/**
 * Represents a Motorboat with a specific engine power.
 */
public class MotorboatBoat extends Boat {
  private double enginePower;

  /**
   * Constructs a MotorboatBoat with the specified name, length, and engine power.
   *
   * @param name        the name of the motorboat
   * @param length      the length of the motorboat
   * @param enginePower the engine power of the motorboat
   */
  public MotorboatBoat(String name, double length, double enginePower) {
    super(name, length);
    this.enginePower = enginePower;
  }

  /**
   * Returns the engine power of the motorboat.
   *
   * @return the engine power
   */
  public double getEnginePower() {
    return enginePower;
  }

  /**
   * Returns a string representation of the motorboat details.
   *
   * @return a formatted string with motorboat details
   */
  @Override
  public String getDetails() {
    return String.format("BOAT:%s:motorboat:%.0f:%.0f", name, length, enginePower);
  }
}
