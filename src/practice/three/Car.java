package practice.three;

import java.time.Clock;

public final class Car extends Vehicle {
    private final int seats;
    private final String engineType;

    public Car(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, int seats, String engineType, Clock clock) {
        super(plateNumber, manufacturer, manufactureYear, color, owner, clock);
        this.seats = requirePositive(seats, "Seats");
        this.engineType = requireNotBlank(engineType, "Engine type");
    }

    @Override
    public String toString() {
        return super.toString() + " | Seats: %d | Engine type: %s".formatted(seats, engineType);
    }
}
