package assignment4;

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

  public static void main(String[] args) {
    BoatClubApp app = new BoatClubApp();
    app.start();
  }
}