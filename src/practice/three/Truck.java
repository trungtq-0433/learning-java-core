package practice.three;

import java.time.Clock;

public final class Truck extends Vehicle {
    private final double tonnage;

    public Truck(String plateNumber, Manufacturer manufacturer, int manufactureYear, String color, Owner owner, double tonnage, Clock clock) {
        super(plateNumber, manufacturer, manufactureYear, color, owner, clock);
        this.tonnage = requirePositive(tonnage, "Tonnage");
    }

    @Override
    public String toString() {
        return super.toString() + " | Tonnage: %.1f ton".formatted(tonnage);
    }
}
