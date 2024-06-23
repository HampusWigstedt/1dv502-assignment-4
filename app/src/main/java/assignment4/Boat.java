package assignment4;

public class Boat {
    private String name;
    private Type type;
    private double length;

    // Enum to represent boat types
    public enum Type {
        SAILBOAT, MOTORBOAT, MOTORSAILER, CANOE
    }

    // Constructor
    public Boat(String name, Type type, double length) {
        this.name = name;
        this.type = type;
        this.length = length;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getDetails() {
        // Format the boat details as "name:type:length"
        return String.format("%s:%s:%.2f", this.name, this.type.toString().toLowerCase(), this.length);
    }
}