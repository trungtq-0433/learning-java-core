package practice.three;

import java.time.Clock;

public class Motorbike extends Vehicle {
    private double capacity;

    public Motorbike(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, double capacity, Clock clock) {
        super(plateNumber, manufacturer, manufactureYear, color, owner, clock);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + " | Capacity: %.1f cc".formatted(capacity);
    }
}