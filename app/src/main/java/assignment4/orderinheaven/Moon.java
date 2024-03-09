package assignment4.orderinheaven;

/**
 * This class represents a moon in the solar system.
 * A moon is a type of heavenly body and has an average orbit radius in kilometers.
 */
public class Moon extends HeavenlyBody {
  private double avgOrbitRadiusInKm;

  /**
   * Constructs a new moon with the given name, average radius, and average orbit radius.
   *
   * @param name the name of the moon
   * @param avgRadiusInKm the average radius of the moon in kilometers
   * @param avgOrbitRadiusInKm the average orbit radius of the moon in kilometers
   */
  protected Moon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    if (avgOrbitRadiusInKm < 60) {
      throw new IllegalArgumentException("Average orbit radius cannot be negative");
    }
    this.avgOrbitRadiusInKm = avgOrbitRadiusInKm;
  }

  /**
   * Returns the average orbit radius of this moon in kilometers.
   *
   * @return the average orbit radius of this moon in kilometers
   */
  public double getAvgOrbitRadiusInKm() {
    return avgOrbitRadiusInKm;
  }

  /**
   * Checks if the given average radius in kilometers is valid for a moon.
   * The average radius must be between 6 and 10000 kilometers.
   *
   * @param avgRadiusInKm the average radius to check
   * @return the valid average radius
   * @throws IllegalArgumentException if the average radius is not between 6 and 10000 kilometers
   */
  @Override
  protected int checkAvgRadiusInKm(int avgRadiusInKm) {
    if (avgRadiusInKm < 6 || avgRadiusInKm > 10000) {
      throw new IllegalArgumentException("Average radius must be between 6 and 10000");
    }
    return avgRadiusInKm;
  }

  /**
   * Returns a string representation of this moon.
   * The string representation includes the name, average radius, and average orbit radius of this moon.
   *
   * @return a string representation of this moon
   */
  @Override
  public String toString() {
    return "      Moon: " + getName() + ", average radius " + getAvgRadiusInKm() 
      + "km, average orbit radius " + getAvgOrbitRadiusInKm() + "km";
  }
}