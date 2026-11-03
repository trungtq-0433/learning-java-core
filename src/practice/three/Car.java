package practice.three;

import java.time.Clock;

public class Car extends Vehicle {
    private int seats;
    private String engineType;

    public Car(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, int seats, String engineType, Clock clock) {
        super(plateNumber, manufacturer, manufactureYear, color, owner, clock);
        this.seats = seats;
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Seats: %d | Engine type: %s".formatted(seats, engineType);
    }
}
