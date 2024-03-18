package assignment4.orderinheaven;

import java.util.ArrayList;


public class SolarSystem {
    private ArrayList<Star> stars;

    public SolarSystem() {
        this.stars = new ArrayList<>();
    }

    public void addStar(Star star) {
        this.stars.add(star);
    }

    public ArrayList<Star> getStars() {
        return this.stars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Star star : stars) {
            sb.append(star).append("\n");
            for (Planet planet : star.getPlanets()) {
                sb.append("\t").append(planet).append("\n");
                for (Moon moon : planet.getMoons()) {
                    sb.append("\t\t").append(moon).append("\n");
                }
            }
        }
        return sb.toString();
    }
}