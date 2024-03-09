package assignment4.orderinheaven;

import java.util.ArrayList;

/**
 * This class represents a star in the solar system.
 * A star is a type of heavenly body and can have planets.
 */
public class Star extends HeavenlyBody {
  private ArrayList<Planet> planets;

  /**
   * Constructs a new star with the given name and average radius.
   *
   * @param name the name of the star
   * @param avgRadiusInKm the average radius of the star in kilometers
   */
  public Star(String name, int avgRadiusInKm) {
    super(name, avgRadiusInKm);
    this.planets = new ArrayList<>();
  }

  /**
   * Adds a new planet to this star.
   *
   * @param name the name of the planet
   * @param avgRadiusInKm the average radius of the planet in kilometers
   * @param avgOrbitRadiusInKm the average orbit radius of the planet in kilometers
   * @return the new planet
   */
  public Planet addPlanet(String name, int avgRadiusInKm, double avgOrbitRadiusInKm) {
    Planet planet = new Planet(name, avgRadiusInKm, avgOrbitRadiusInKm);
    planets.add(planet);
    return planet;
  }

  /**
   * Returns an array of all the heavenly bodies (planets) of this star.
   *
   * @return an array of all the heavenly bodies (planets) of this star
   */
  public HeavenlyBody[] getHeavenlyBodies() {
    int totalSize = planets.stream().mapToInt(planet -> planet.getHeavenlyBodies().length).sum() + 1;
    HeavenlyBody[] bodies = new HeavenlyBody[totalSize];
    bodies[0] = new Star(this.getName(), this.getAvgRadiusInKm()); // Add a copy of the star itself

    int index = 1;
    for (Planet planet : planets) {
      HeavenlyBody[] planetBodies = planet.getHeavenlyBodies();
      System.arraycopy(planetBodies, 0, bodies, index, planetBodies.length);
      index += planetBodies.length;
    }

    return bodies;
  }

  /**
   * Checks if the given average radius in kilometers is valid for a star.
   * The average radius must be larger than 16700 kilometers.
   *
   * @param avgRadiusInKm the average radius to check
   * @return the valid average radius
   * @throws IllegalArgumentException if the average radius is not larger than 16700 kilometers
   */
  @Override
  protected int checkAvgRadiusInKm(int avgRadiusInKm) {
    if (avgRadiusInKm < 16700) {
      throw new IllegalArgumentException("Average radius must be larger then 16700");
    }
    return avgRadiusInKm;
  }

  /**
   * Returns a string representation of this star.
   * The string representation includes the name and average radius of this star.
   *
   * @return a string representation of this star
   */
  @Override
  public String toString() {
    return "Star: " + getName() + ", average radius " + getAvgRadiusInKm() + "km";
  }
}