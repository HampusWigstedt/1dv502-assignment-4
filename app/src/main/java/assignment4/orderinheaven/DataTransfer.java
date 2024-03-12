package assignment4.orderinheaven;

import java.io.*;
import java.util.*;

public class DataTransfer {

    public static void writeToFile(Star star, String filename) {
        File file = new File(filename);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            out.println(star.toString());
            for (Planet planet : star.getPlanets()) {
                out.println(planet.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
