package assignment4;

abstract class Boat {
  protected String name;
  protected double length;

  public Boat(String name, double length) {
    this.name = name;
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public double getLength() {
    return length;
  }

  public abstract String getDetails();
}
