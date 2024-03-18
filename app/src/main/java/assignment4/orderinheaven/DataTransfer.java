package assignment4.orderinheaven;

import java.io.*;
import java.util.*;

public class DataTransfer {

    public static void writeToFile(ArrayList<SolarSystem> solarSystems, String filename) {
        File file = new File(filename);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            for (SolarSystem solarSystem : solarSystems) {
                for (Star star : solarSystem.getStars()) {
                    out.println(star.toString());
                    for (Planet planet : star.getPlanets()) {
                        out.println("\t" + planet.toString());
                        for (Moon moon : planet.getMoons()) {
                            out.println("\t\t" + moon.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SolarSystem> readFromFile(String filename) {
        ArrayList<SolarSystem> solarSystems = new ArrayList<>();
        File file = new File(filename);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    // Code to read from the file and add the data to solarSystems
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return solarSystems;
    }
}
