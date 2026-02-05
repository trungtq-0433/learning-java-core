package practice.three;

public class Truck extends Vehicle {
    private double tonnage;

    public Truck(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, double tonnage) {
        super(plateNumber, manufacturer, manufactureYear, color, owner);
        this.tonnage = tonnage;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tonnage: %.1f ton".formatted(tonnage);
    }
}