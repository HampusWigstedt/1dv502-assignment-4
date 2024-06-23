package assignment4;

/**
 * This class serves as the entry point for the Boat Club application.
 * It initializes the application's components and starts the user interface.
 */
public class BoatClubApp {
  private Registry registry;
  private BoatClubInterface boatClubInterface;

  public BoatClubApp() {
    this.registry = new Registry();
    this.boatClubInterface = new BoatClubInterface(registry);
  }

  public void start() {
    boatClubInterface.run();
  }

  // public static void main(String[] args) {
  //   BoatClubApp app = new BoatClubApp();
  //   app.start();
  // }
}