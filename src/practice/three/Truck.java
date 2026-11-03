package practice.three;

import java.time.Clock;

public class Truck extends Vehicle {
    private double tonnage;

    public Truck(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, double tonnage, Clock clock) {
        super(plateNumber, manufacturer, manufactureYear, color, owner, clock);
        this.tonnage = tonnage;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tonnage: %.1f ton".formatted(tonnage);
    }
}