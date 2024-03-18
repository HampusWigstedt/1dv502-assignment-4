package assignment4.orderinheaven;

import java.util.ArrayList;

/**
 * This class represents a planet in the solar system.
 * A planet is a type of heavenly body and has an average orbit radius in kilometers.
 * A planet can also have moons.
 */
public class Planet extends HeavenlyBody {
  private ArrayList<Moon> moons;
  private double avgOrbitRadiusInKm;

  /**
   * Constructs a new planet with the given name, average radius, and average orbit radius.
   *
   * @param name the name of the planet
   * @param avgRadiusInKm the average radius of the planet in kilometers
   * @param avgOrbitRadiusInKm the average orbit radius of the planet in kilometers
   */
  protected Planet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    super(name, avgRadiusInKm);
    if (avgOrbitRadiusInKm < 18000) {
      throw new IllegalArgumentException("Average orbit radius cannot be negative");
    }
    this.avgOrbitRadiusInKm = avgOrbitRadiusInKm;
    this.moons = new ArrayList<>();
  }

  public ArrayList<Moon> getMoons() {
    return moons;
  }

  /**
   * Adds a new moon to this planet.
   *
   * @param name the name of the moon
   * @param avgRadiusInKm the average radius of the moon in kilometers
   * @param avgOrbitRadiusInKm the average orbit radius of the moon in kilometers
   * @return the new moon
   * @throws IllegalArgumentException if the moon is larger than half the size of the planet
   */
  public Moon addMoon(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    if (avgRadiusInKm > this.getAvgRadiusInKm() / 2) {
      throw new IllegalArgumentException("A planet cannot have a moon that is larger than half its own size");
    }
    Moon moon = new Moon(name, avgRadiusInKm, avgOrbitRadiusInKm);
    moons.add(moon);
    return moon;
  }

  /**
   * Returns the average orbit radius of this planet in kilometers.
   *
   * @return the average orbit radius of this planet in kilometers
   */
  public double getAvgOrbitRadiusInKm() {
    return avgOrbitRadiusInKm;
  }

  /**
   * Returns an array of all the heavenly bodies (moons) of this planet.
   *
   * @return an array of all the heavenly bodies (moons) of this planet
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    HeavenlyBody[] bodies = new HeavenlyBody[moons.size() + 1];
    bodies[0] = new Planet(this.getName(), 
    this.getAvgRadiusInKm(), this.getAvgOrbitRadiusInKm()); // Add a copy of the planet itself

    for (int i = 0; i < moons.size(); i++) {
      Moon moon = moons.get(i);
      bodies[i + 1] = new Moon(moon.getName(), 
        moon.getAvgRadiusInKm(), moon.getAvgOrbitRadiusInKm()); // Add a copy of each moon
    }

    return bodies;
  }

  /**
   * Returns a string representation of this planet.
   * The string representation includes the name, average radius, and average orbit radius of this planet.
   *
   * @return a string representation of this planet
   */
  @Override
  public String toString() {
    return "-" + getName() + ":" + getAvgRadiusInKm() + ":" + getAvgOrbitRadiusInKm();
  }

  /**
   * Checks if the given average radius in kilometers is valid for a planet.
   * The average radius must be between 2000 and 200000 kilometers.
   *
   * @param avgRadiusInKm the average radius to check
   * @return the valid average radius
   * @throws IllegalArgumentException if the average radius is not between 2000 and 200000 kilometers
   */
  @Override
    protected int checkAvgRadiusInKm(int avgRadiusInKm) {
    if (avgRadiusInKm < 2000 || avgRadiusInKm > 200000) {
      throw new IllegalArgumentException("Average radius must be between 2000 and 200000");
    }
    return avgRadiusInKm;
  }
}