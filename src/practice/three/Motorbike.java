package practice.three;

import java.time.Clock;

public final class Motorbike extends Vehicle {
    private final double capacity;

    public Motorbike(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, double capacity, Clock clock) {
        super(plateNumber, manufacturer, manufactureYear, color, owner, clock);
        this.capacity = requirePositive(capacity, "Capacity");
    }

    @Override
    public String toString() {
        return super.toString() + " | Capacity: %.1f cc".formatted(capacity);
    }
}
