package assignment4.orderinheaven;

import java.io.Serializable;

/**
 * This abstract class represents a heavenly body in the solar system.
 * A heavenly body has a name and an average radius in kilometers.
 */
public abstract class HeavenlyBody implements Comparable<HeavenlyBody>, Serializable {
  private String name;
  private int avgRadiusInKm;

  /**
   * Constructs a new heavenly body with the given name and average radius.
   *
   * @param name the name of the heavenly body
   * @param avgRadiusInKm the average radius of the heavenly body in kilometers
   * @throws IllegalArgumentException if the name is null or empty, or if the average radius is invalid
   */
  protected HeavenlyBody(String name, int avgRadiusInKm) {
    this.setName(name);
    this.setAvgRadiusInKm(avgRadiusInKm);
  }

  /**
   * Returns the name of this heavenly body.
   *
   * @return the name of this heavenly body
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this heavenly body.
   *
   * @param newName the new name of this heavenly body
   * @throws IllegalArgumentException if the new name is null or empty
   */
  private void setName(String newName) {
    if (newName == null || newName.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = newName;
  }

  /**
   * Returns the average radius of this heavenly body in kilometers.
   *
   * @return the average radius of this heavenly body in kilometers
   */
  public int getAvgRadiusInKm() {
    return avgRadiusInKm;
  }

  /**
   * Sets the average radius of this heavenly body in kilometers.
   *
   * @param newAvgRadiusInKm the new average radius of this heavenly body in kilometers
   * @throws IllegalArgumentException if the new average radius is invalid
   */
  private void setAvgRadiusInKm(int newAvgRadiusInKm) {
    int radius = this.checkAvgRadiusInKm(newAvgRadiusInKm);
    this.avgRadiusInKm = radius;
  }

  /**
   * Checks if the given average radius in kilometers is valid.
   *
   * @param avgRadiusInKm the average radius to check
   * @return the valid average radius
   * @throws IllegalArgumentException if the average radius is invalid
   */
  protected abstract int checkAvgRadiusInKm(int avgRadiusInKm);

  /**
   * Returns a string representation of this heavenly body.
   * The string representation includes the name and average radius of this heavenly body.
   *
   * @return a string representation of this heavenly body
   */
  @Override
  public String toString() {
    return "Name: " + name + ", Radius: " + avgRadiusInKm + " km";
  }

  @Override
  public int compareTo(HeavenlyBody other) {
    return Double.compare(this.getAvgRadiusInKm(), other.getAvgRadiusInKm());
  }

  @Override
  protected final void finalize() throws Throwable {
    // Empty finalize method
  }

}