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


// class Sailboat extends Boat {
//   protected double depth;

//   public Sailboat(String name, double length, double depth) {
//     super(name, length);
//     this.depth = depth;
//   }

//   public double getSailDepth() {
//     return depth;
//   }

//   @Override
//   public String getDetails() {
//     return String.format("BOAT:%s:sailboat:%.0f:%.0f", name, length, depth);
//   }
// }

// class Motorsailer extends Boat {
//   private double depth;
//   private double enginePower;

//   public Motorsailer(String name, double length, double depth, double enginePower) {
//     super(name, length);
//     this.depth = depth;
//     this.enginePower = enginePower;
//   }

//   public double getSailDepth() {
//     return depth;
//   }

//   public double getEnginePower() {
//     return enginePower;
//   }

//   @Override
//   public String getDetails() {
//     return String.format("BOAT:%s:motorsailer:%.0f:%.0f:%.0f", name, length, depth, enginePower);
//   }
// }

// class Motorboat extends Boat {
//   private double enginePower;
  
//   public Motorboat(String name, double length, double enginePower) {
//     super(name, length);
//     this.enginePower = enginePower;
//   }
  
//   public double getEnginePower() {
//     return enginePower;
//   }
  
//   @Override
//   public String getDetails() {
//     return String.format("BOAT:%s:motorboat:%.0f:%.0f", name, length, enginePower);
//   }
// }

// class Canoe extends Boat {
//   public Canoe(String name, double length) {
//     super(name, length);
//   }
  
//   @Override
//   public String getDetails() {
//     return String.format("BOAT:%s:canoe:%.0f", name, length);
//   }
// }

